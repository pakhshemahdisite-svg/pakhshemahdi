<?php
/**
 * Plugin Name: Pakhsh Mahdi App Core
 * Description: Secure read-only mobile REST bridge for the Pakhsh Mahdi Android/iOS apps.
 * Version: 0.1.0
 * Author: Pakhsh Mahdi
 * Requires at least: 6.5
 * Requires PHP: 8.1
 */

defined('ABSPATH') || exit;

final class PM_App_Core {
    private const NS = 'pm-app/v1';

    public static function boot(): void {
        add_action('rest_api_init', [self::class, 'routes']);
    }

    public static function routes(): void {
        register_rest_route(self::NS, '/home', [
            'methods' => WP_REST_Server::READABLE,
            'callback' => [self::class, 'home'],
            'permission_callback' => '__return_true',
        ]);

        register_rest_route(self::NS, '/products', [
            'methods' => WP_REST_Server::READABLE,
            'callback' => [self::class, 'products'],
            'permission_callback' => '__return_true',
            'args' => [
                'page' => ['sanitize_callback' => 'absint', 'default' => 1],
                'per_page' => ['sanitize_callback' => 'absint', 'default' => 20],
                'category' => ['sanitize_callback' => 'absint'],
                'search' => ['sanitize_callback' => 'sanitize_text_field'],
            ],
        ]);

        register_rest_route(self::NS, '/products/(?P<id>\d+)', [
            'methods' => WP_REST_Server::READABLE,
            'callback' => [self::class, 'product'],
            'permission_callback' => '__return_true',
            'args' => ['id' => ['sanitize_callback' => 'absint']],
        ]);
    }

    private static function ensure_wc() {
        if (!class_exists('WooCommerce') || !function_exists('wc_get_products')) {
            return new WP_Error('pm_wc_missing', 'WooCommerce is required.', ['status' => 503]);
        }
        return true;
    }

    public static function home(WP_REST_Request $request) {
        $ok = self::ensure_wc(); if (is_wp_error($ok)) return $ok;
        $terms = get_terms(['taxonomy' => 'product_cat', 'hide_empty' => true, 'number' => 12]);
        $categories = is_wp_error($terms) ? [] : array_map([self::class, 'map_category'], $terms);
        $latest = wc_get_products(['status' => 'publish', 'limit' => 10, 'orderby' => 'date', 'order' => 'DESC']);
        $featured = wc_get_products(['status' => 'publish', 'limit' => 8, 'featured' => true]);

        return rest_ensure_response([
            'categories' => array_values($categories),
            'latestProducts' => array_map([self::class, 'map_product'], $latest),
            'featuredProducts' => array_map([self::class, 'map_product'], $featured),
        ]);
    }

    public static function products(WP_REST_Request $request) {
        $ok = self::ensure_wc(); if (is_wp_error($ok)) return $ok;
        $page = max(1, (int) $request['page']);
        $per_page = min(50, max(1, (int) $request['per_page']));
        $args = ['status' => 'publish', 'limit' => $per_page, 'page' => $page, 'paginate' => true, 'orderby' => 'date', 'order' => 'DESC'];
        if ($request['search']) $args['s'] = $request['search'];
        if ($request['category']) {
            $term = get_term((int) $request['category'], 'product_cat');
            if ($term && !is_wp_error($term)) $args['category'] = [$term->slug];
        }
        $result = wc_get_products($args);
        return rest_ensure_response([
            'items' => array_map([self::class, 'map_product'], $result->products),
            'page' => $page,
            'total' => (int) $result->total,
            'totalPages' => (int) $result->max_num_pages,
        ]);
    }

    public static function product(WP_REST_Request $request) {
        $ok = self::ensure_wc(); if (is_wp_error($ok)) return $ok;
        $product = wc_get_product((int) $request['id']);
        if (!$product || $product->get_status() !== 'publish') return new WP_Error('pm_not_found', 'Product not found.', ['status' => 404]);
        return rest_ensure_response(self::map_product($product));
    }

    public static function map_category($term): array {
        $image = '';
        $thumb_id = (int) get_term_meta($term->term_id, 'thumbnail_id', true);
        if ($thumb_id) $image = (string) wp_get_attachment_image_url($thumb_id, 'medium');
        return ['id' => (int) $term->term_id, 'name' => html_entity_decode($term->name), 'slug' => $term->slug, 'image' => $image ?: null, 'count' => (int) $term->count];
    }

    public static function map_product($product): array {
        $image = $product->get_image_id() ? wp_get_attachment_image_url($product->get_image_id(), 'large') : null;
        $gallery = array_values(array_filter(array_map(static fn($id) => wp_get_attachment_image_url($id, 'large'), $product->get_gallery_image_ids())));
        $cats = [];
        foreach ($product->get_category_ids() as $cat_id) {
            $term = get_term($cat_id, 'product_cat');
            if ($term && !is_wp_error($term)) $cats[] = self::map_category($term);
        }
        return [
            'id' => (int) $product->get_id(),
            'name' => html_entity_decode($product->get_name()),
            'slug' => $product->get_slug(),
            'price' => (string) $product->get_price(),
            'regularPrice' => (string) $product->get_regular_price(),
            'salePrice' => (string) $product->get_sale_price(),
            'image' => $image ?: null,
            'gallery' => $gallery,
            'shortDescription' => wp_strip_all_tags($product->get_short_description()),
            'stockStatus' => $product->get_stock_status(),
            'averageRating' => (string) $product->get_average_rating(),
            'ratingCount' => (int) $product->get_rating_count(),
            'categories' => $cats,
        ];
    }
}

PM_App_Core::boot();
