package com.youarelaunched.challenge.ui.screen.view

import com.youarelaunched.challenge.data.repository.model.Vendor
import com.youarelaunched.challenge.ui.screen.state.VendorsScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


object FakeVendorsVM {
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
    fun provideFakeVendorVM() = object : VendorsVMAbstract{



        override fun searchVendors(text: String) {

        }

        override fun closeSearch(text: String) {
        }

        override fun getVendors() {
            _uiState.update {
                it.copy(
                    vendors = listOf(
                        Vendor(1,
                        "Florists + Fashion",
                        "https://cdn-staging.chatsumer.app/eyJidWNrZXQiOiJjaGF0c3VtZXItZ2VuZXJhbC1zdGFnaW5nLXN0b3JhZ2UiLCJrZXkiOiIxMy84ZjMzZTgyNy0yNzIxLTQ3ZjctYjViNS0zM2Q5Y2E2MTM1OGQuanBlZyJ9",
                        "",true,
                        emptyList(),
                        ""
                    )
                    )
                )
            }
        }

    }


}