package com.pakhshmahdi.app.feature.product

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pakhshmahdi.app.data.cart.CartStore
import com.pakhshmahdi.app.ui.components.ProductImage
import com.pakhshmahdi.app.ui.components.toman
import com.pakhshmahdi.app.ui.theme.PMPrimary
import com.pakhshmahdi.app.ui.theme.PMSuccess

@Composable
fun ProductDetailScreen(id: Long, onBack: () -> Unit) {
    val vm: ProductDetailViewModel = viewModel()
    val product by vm.product.collectAsState()
    LaunchedEffect(id) { vm.load(id) }
    val p = product ?: run {
        Box(Modifier.fillMaxSize()) { CircularProgressIndicator(Modifier.padding(32.dp)) }
        return
    }

    Column(Modifier.fillMaxSize().padding(top = 38.dp)) {
        Row(Modifier.fillMaxWidth().padding(horizontal = 8.dp)) {
            IconButton(onClick = onBack) { Icon(Icons.Outlined.ArrowBack, null) }
            Spacer(Modifier.weight(1f))
            IconButton(onClick = {}) { Icon(Icons.Outlined.FavoriteBorder, null) }
        }
        Column(Modifier.weight(1f).verticalScroll(rememberScrollState()).padding(16.dp)) {
            ProductImage(p.image, Modifier.fillMaxWidth().height(330.dp).clip(RoundedCornerShape(24.dp)))
            Spacer(Modifier.height(20.dp))
            Text(p.name, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Black)
            Spacer(Modifier.height(8.dp))
            Text(if (p.shortDescription.isBlank()) "محصول باکیفیت مناسب فروش عمده و استفاده خانگی." else p.shortDescription)
            Spacer(Modifier.height(18.dp))
            Text(if (p.isInStock) "● موجود در انبار" else "ناموجود", color = if (p.isInStock) PMSuccess else MaterialTheme.colorScheme.error)
            Spacer(Modifier.height(18.dp))
            Text(toman(p.price), style = MaterialTheme.typography.headlineSmall, color = PMPrimary, fontWeight = FontWeight.Black)
        }
        Button(
            onClick = { CartStore.add(p) },
            modifier = Modifier.fillMaxWidth().padding(16.dp).height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PMPrimary)
        ) { Text("افزودن به سبد خرید") }
    }
}
