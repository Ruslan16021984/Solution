package com.youarelaunched.challenge.ui.screen.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.youarelaunched.challenge.data.repository.VendorsRepository
import com.youarelaunched.challenge.ui.screen.state.VendorsScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

interface VendorsVMAbstract {

    fun searchVendors(text: String)
    fun closeSearch(text: String)
    fun getVendors()
}

@HiltViewModel
class VendorsVM @Inject constructor(
    private val repository: VendorsRepository
) : ViewModel(), VendorsVMAbstract {

    private val _searchText = MutableStateFlow("")
    private val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _uiState = MutableStateFlow(
        VendorsScreenUiState(
            vendors = null
        )
    )
    val uiState = _uiState.asStateFlow()

    init {
        getVendors()
    }

    @OptIn(FlowPreview::class)
    val vendorSearch = searchText
        .debounce(500)
        .combine(_uiState) { text, list ->
            if (text.isBlank()) {
                list.vendors
            } else {
                list.vendors?.filter {
                    it.doesMatchSearchQuery(text)
                }
            }


        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _uiState.value.vendors
        )

    override fun searchVendors(text: String) {
        _searchText.value = text
        _isSearching.update { true }
    }

    override fun closeSearch(text: String) {
        _searchText.value = text
        _isSearching.value = false
    }

    override fun getVendors() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    vendors = repository.getVendors()
                )
            }
        }
    }
}