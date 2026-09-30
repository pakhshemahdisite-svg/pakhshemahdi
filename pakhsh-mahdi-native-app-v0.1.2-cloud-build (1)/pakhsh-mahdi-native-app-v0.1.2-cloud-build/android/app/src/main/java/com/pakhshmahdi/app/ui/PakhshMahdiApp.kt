package com.pakhshmahdi.app.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pakhshmahdi.app.feature.cart.CartScreen
import com.pakhshmahdi.app.feature.catalog.CatalogScreen
import com.pakhshmahdi.app.feature.home.HomeScreen
import com.pakhshmahdi.app.feature.product.ProductDetailScreen
import com.pakhshmahdi.app.ui.components.AppBottomBar

@Composable
fun PakhshMahdiApp() {
    val nav = rememberNavController()
    val backStackEntry by nav.currentBackStackEntryAsState()
    val current = backStackEntry?.destination?.route ?: "home"
    Scaffold(
        bottomBar = {
            if (!current.startsWith("product/")) {
                AppBottomBar(
                    selected = current,
                    onHome = { nav.navigate("home") { launchSingleTop = true } },
                    onCatalog = { nav.navigate("catalog") { launchSingleTop = true } },
                    onCart = { nav.navigate("cart") { launchSingleTop = true } }
                )
            }
        }
    ) { padding ->
        NavHost(navController = nav, startDestination = "home", modifier = Modifier.padding(padding)) {
            composable("home") { HomeScreen(onProduct = { nav.navigate("product/$it") }, onCatalog = { nav.navigate("catalog") }) }
            composable("catalog") { CatalogScreen(onProduct = { nav.navigate("product/$it") }) }
            composable("cart") { CartScreen() }
            composable("product/{id}", arguments = listOf(navArgument("id") { type = NavType.LongType })) { backStack ->
                ProductDetailScreen(backStack.arguments?.getLong("id") ?: 0L, onBack = { nav.popBackStack() })
            }
        }
    }
}
