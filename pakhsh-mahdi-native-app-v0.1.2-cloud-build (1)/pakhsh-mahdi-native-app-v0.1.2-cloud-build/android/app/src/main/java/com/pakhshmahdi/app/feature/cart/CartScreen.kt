package com.pakhshmahdi.app.feature.cart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.pakhshmahdi.app.data.cart.CartStore
import com.pakhshmahdi.app.ui.components.toman
import com.pakhshmahdi.app.ui.theme.PMPrimary

@Composable
fun CartScreen() {
    Column(Modifier.fillMaxSize().padding(top = 48.dp)) {
        Text("سبد خرید", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Black, modifier = Modifier.padding(16.dp))
        if (CartStore.lines.isEmpty()) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text("سبد خرید شما خالی است") }
        } else {
            LazyColumn(Modifier.weight(1f), contentPadding = PaddingValues(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(CartStore.lines, key = { it.product.id }) { line ->
                    Surface(tonalElevation = 1.dp, shape = MaterialTheme.shapes.large) {
                        Row(Modifier.fillMaxWidth().padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                            Column(Modifier.weight(1f)) {
                                Text(line.product.name, fontWeight = FontWeight.Bold)
                                Text(toman(line.product.price), color = PMPrimary)
                            }
                            OutlinedButton(onClick = { CartStore.decrement(line.product.id) }) { Text("−") }
                            Text(" ${line.quantity} ", modifier = Modifier.padding(horizontal = 6.dp))
                            OutlinedButton(onClick = { CartStore.increment(line.product.id) }) { Text("+") }
                            IconButton(onClick = { CartStore.remove(line.product.id) }) { Icon(Icons.Outlined.DeleteOutline, null) }
                        }
                    }
                }
            }
            Button(onClick = {}, modifier = Modifier.fillMaxWidth().padding(16.dp).height(56.dp), colors = ButtonDefaults.buttonColors(containerColor = PMPrimary)) { Text("ادامه و ثبت سفارش") }
        }
    }
}
