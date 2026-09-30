package com.pakhshmahdi.app.feature.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pakhshmahdi.app.data.model.Product
import com.pakhshmahdi.app.data.repository.CatalogRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class CatalogState(val loading: Boolean = true, val products: List<Product> = emptyList())

class CatalogViewModel : ViewModel() {
    private val repository = CatalogRepository()
    private val _state = MutableStateFlow(CatalogState())
    val state: StateFlow<CatalogState> = _state
    init { viewModelScope.launch { _state.value = CatalogState(false, repository.products()) } }
}
