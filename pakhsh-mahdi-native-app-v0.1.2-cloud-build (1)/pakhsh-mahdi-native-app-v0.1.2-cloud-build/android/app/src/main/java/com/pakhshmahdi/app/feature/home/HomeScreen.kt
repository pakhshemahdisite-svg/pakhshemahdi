package com.pakhshmahdi.app.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pakhshmahdi.app.data.cart.CartStore
import com.pakhshmahdi.app.ui.components.ProductCard
import com.pakhshmahdi.app.ui.theme.PMPrimary
import com.pakhshmahdi.app.ui.theme.PMSecondary
import com.pakhshmahdi.app.ui.theme.PMWhite

@Composable
fun HomeScreen(onProduct: (Long) -> Unit, onCatalog: () -> Unit) {
    val vm: HomeViewModel = viewModel()
    val state by vm.state.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        item {
            Box(
                Modifier.fillMaxWidth().background(Brush.verticalGradient(listOf(PMPrimary, PMSecondary)))
                    .padding(start = 18.dp, end = 18.dp, top = 52.dp, bottom = 24.dp)
            ) {
                Column {
                    Text("پخش مهدی", style = MaterialTheme.typography.headlineMedium, color = PMWhite, fontWeight = FontWeight.Black)
                    Text("تأمین‌کننده لوازم خانه و آشپزخانه", color = PMWhite.copy(alpha = .75f))
                    Spacer(Modifier.height(18.dp))
                    Surface(shape = RoundedCornerShape(16.dp), color = PMWhite, modifier = Modifier.fillMaxWidth().clickable(onClick = onCatalog)) {
                        Row(Modifier.padding(horizontal = 16.dp, vertical = 14.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Outlined.Search, null, tint = PMPrimary)
                            Spacer(Modifier.width(10.dp))
                            Text("جستجوی محصولات، برند یا دسته‌بندی...", color = MaterialTheme.colorScheme.onSurface.copy(alpha=.55f))
                        }
                    }
                }
            }
        }
        item {
            Box(Modifier.padding(16.dp).fillMaxWidth().height(190.dp).background(PMWhite, RoundedCornerShape(24.dp))) {
                Column(Modifier.padding(22.dp).align(Alignment.CenterEnd)) {
                    Text("کیفیت در هر آشپزخانه", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Black, color = PMPrimary)
                    Spacer(Modifier.height(8.dp))
                    Text("انواع لوازم خانه و آشپزخانه با قیمت عمده")
                    Spacer(Modifier.height(14.dp))
                    Button(onClick = onCatalog, colors = ButtonDefaults.buttonColors(containerColor = PMPrimary)) { Text("مشاهده محصولات") }
                }
            }
        }
        item { SectionTitle("دسته‌بندی‌ها", "مشاهده همه", onCatalog) }
        item {
            LazyRow(contentPadding = PaddingValues(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                items(state.data.categories) { category ->
                    Surface(shape = RoundedCornerShape(16.dp), color = PMWhite, modifier = Modifier.width(120.dp).clickable(onClick = onCatalog)) {
                        Column(Modifier.padding(14.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("◉", color = PMPrimary, style = MaterialTheme.typography.headlineMedium)
                            Spacer(Modifier.height(6.dp))
                            Text(category.name, style = MaterialTheme.typography.labelMedium, maxLines = 2)
                        }
                    }
                }
            }
        }
        item { SectionTitle("جدیدترین محصولات", "مشاهده همه", onCatalog) }
        item {
            LazyRow(contentPadding = PaddingValues(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                items(state.data.latestProducts) { product ->
                    ProductCard(product, onOpen = { onProduct(product.id) }, onAdd = { CartStore.add(product) }, modifier = Modifier.width(220.dp))
                }
            }
        }
    }
}

@Composable
private fun SectionTitle(title: String, action: String, onClick: () -> Unit) {
    Row(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 16.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Black, modifier = Modifier.weight(1f))
        TextButton(onClick = onClick) { Text(action) }
    }
}
