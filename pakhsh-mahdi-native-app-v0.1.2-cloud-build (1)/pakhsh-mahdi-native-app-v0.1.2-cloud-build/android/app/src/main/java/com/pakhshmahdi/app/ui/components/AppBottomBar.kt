package com.pakhshmahdi.app.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun AppBottomBar(
    selected: String,
    onHome: () -> Unit,
    onCatalog: () -> Unit,
    onCart: () -> Unit
) {
    NavigationBar {
        NavigationBarItem(
            selected = selected == "home",
            onClick = onHome,
            icon = { Icon(Icons.Outlined.Home, contentDescription = null) },
            label = { Text("خانه") }
        )
        NavigationBarItem(
            selected = selected == "catalog",
            onClick = onCatalog,
            icon = { Icon(Icons.Outlined.GridView, contentDescription = null) },
            label = { Text("دسته‌بندی") }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(Icons.Outlined.FavoriteBorder, contentDescription = null) },
            label = { Text("علاقه‌مندی") }
        )
        NavigationBarItem(
            selected = selected == "cart",
            onClick = onCart,
            icon = { Icon(Icons.Outlined.ShoppingCart, contentDescription = null) },
            label = { Text("سبد خرید") }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(Icons.Outlined.PersonOutline, contentDescription = null) },
            label = { Text("پروفایل") }
        )
    }
}
