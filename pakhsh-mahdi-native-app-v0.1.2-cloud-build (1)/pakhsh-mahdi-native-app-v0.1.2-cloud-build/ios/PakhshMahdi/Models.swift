import Foundation

struct Category: Codable, Identifiable, Hashable {
    let id: Int
    let name: String
    var slug: String = ""
    var image: String? = nil
    var count: Int = 0
}

struct Product: Codable, Identifiable, Hashable {
    let id: Int
    let name: String
    var slug: String = ""
    let price: String
    var regularPrice: String = ""
    var salePrice: String = ""
    var image: String? = nil
    var gallery: [String] = []
    var shortDescription: String = ""
    var stockStatus: String = "instock"
    var averageRating: String = "0"
    var ratingCount: Int = 0
    var categories: [Category] = []

    enum CodingKeys: String, CodingKey {
        case id, name, slug, price, image, gallery, categories
        case regularPrice = "regularPrice"
        case salePrice = "salePrice"
        case shortDescription = "shortDescription"
        case stockStatus = "stockStatus"
        case averageRating = "averageRating"
        case ratingCount = "ratingCount"
    }
}

struct HomePayload: Codable {
    var categories: [Category] = []
    var latestProducts: [Product] = []
    var featuredProducts: [Product] = []
}

struct ProductsPayload: Codable {
    var items: [Product] = []
    var page: Int = 1
    var total: Int = 0
    var totalPages: Int = 1
}
