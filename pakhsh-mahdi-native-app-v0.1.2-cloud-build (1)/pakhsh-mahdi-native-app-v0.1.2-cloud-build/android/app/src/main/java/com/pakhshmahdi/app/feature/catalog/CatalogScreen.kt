package com.pakhshmahdi.app.feature.catalog

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FilterList
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pakhshmahdi.app.data.cart.CartStore
import com.pakhshmahdi.app.ui.components.ProductCard

@Composable
fun CatalogScreen(onProduct: (Long) -> Unit) {
    val vm: CatalogViewModel = viewModel()
    val state by vm.state.collectAsState()
    Column(Modifier.fillMaxSize().padding(top = 42.dp)) {
        Text("لوازم آشپزخانه", style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(horizontal = 16.dp))
        Text("محصولات عمده پخش مهدی", color = MaterialTheme.colorScheme.onSurface.copy(alpha=.55f), modifier = Modifier.padding(horizontal = 16.dp))
        Spacer(Modifier.height(14.dp))
        Row(Modifier.padding(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedButton(onClick = {}, shape = RoundedCornerShape(14.dp)) { Icon(Icons.Outlined.FilterList, null); Spacer(Modifier.width(6.dp)); Text("فیلترها") }
            OutlinedButton(onClick = {}, shape = RoundedCornerShape(14.dp)) { Icon(Icons.Outlined.Search, null); Spacer(Modifier.width(6.dp)); Text("جستجو") }
        }
        Spacer(Modifier.height(8.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(12.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(state.products, key = { it.id }) { product ->
                ProductCard(product, onOpen = { onProduct(product.id) }, onAdd = { CartStore.add(product) })
            }
        }
    }
}
