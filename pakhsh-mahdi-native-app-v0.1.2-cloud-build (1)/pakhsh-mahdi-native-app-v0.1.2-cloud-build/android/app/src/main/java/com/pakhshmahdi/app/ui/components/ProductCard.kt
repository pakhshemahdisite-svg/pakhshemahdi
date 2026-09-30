package com.pakhshmahdi.app.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddShoppingCart
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.pakhshmahdi.app.data.model.Product
import com.pakhshmahdi.app.ui.theme.PMPrimary

@Composable
fun ProductCard(product: Product, onOpen: () -> Unit, onAdd: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.clickable(onClick = onOpen),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(Modifier.padding(10.dp)) {
            Box {
                ProductImage(product.image, Modifier.fillMaxWidth().height(150.dp).clip(RoundedCornerShape(14.dp)))
                IconButton(onClick = {}, modifier = Modifier.align(Alignment.TopStart)) {
                    Icon(Icons.Outlined.FavoriteBorder, null)
                }
            }
            Spacer(Modifier.height(10.dp))
            Text(product.name, fontWeight = FontWeight.SemiBold, maxLines = 2, overflow = TextOverflow.Ellipsis)
            Spacer(Modifier.height(6.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(toman(product.price), fontWeight = FontWeight.Bold, color = PMPrimary, modifier = Modifier.weight(1f))
                FilledIconButton(onClick = onAdd, colors = IconButtonDefaults.filledIconButtonColors(containerColor = PMPrimary)) {
                    Icon(Icons.Outlined.AddShoppingCart, null)
                }
            }
        }
    }
}
