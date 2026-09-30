package com.pakhshmahdi.app.feature.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pakhshmahdi.app.data.model.Product
import com.pakhshmahdi.app.data.repository.CatalogRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductDetailViewModel : ViewModel() {
    private val repository = CatalogRepository()
    private val _product = MutableStateFlow<Product?>(null)
    val product: StateFlow<Product?> = _product
    fun load(id: Long) { if (_product.value?.id == id) return; viewModelScope.launch { _product.value = repository.product(id) } }
}
