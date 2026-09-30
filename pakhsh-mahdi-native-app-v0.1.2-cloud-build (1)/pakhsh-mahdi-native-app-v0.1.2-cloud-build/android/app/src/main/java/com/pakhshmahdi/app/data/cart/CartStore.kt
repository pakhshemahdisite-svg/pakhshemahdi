package com.pakhshmahdi.app.data.cart

import androidx.compose.runtime.mutableStateListOf
import com.pakhshmahdi.app.data.model.Product

data class CartLine(val product: Product, val quantity: Int = 1)

object CartStore {
    val lines = mutableStateListOf<CartLine>()

    fun add(product: Product) {
        val index = lines.indexOfFirst { it.product.id == product.id }
        if (index >= 0) lines[index] = lines[index].copy(quantity = lines[index].quantity + 1)
        else lines.add(CartLine(product))
    }

    fun increment(id: Long) {
        val index = lines.indexOfFirst { it.product.id == id }
        if (index >= 0) lines[index] = lines[index].copy(quantity = lines[index].quantity + 1)
    }

    fun decrement(id: Long) {
        val index = lines.indexOfFirst { it.product.id == id }
        if (index < 0) return
        val line = lines[index]
        if (line.quantity <= 1) lines.removeAt(index) else lines[index] = line.copy(quantity = line.quantity - 1)
    }

    fun remove(id: Long) = lines.removeAll { it.product.id == id }
}
