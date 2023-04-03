package com.youarelaunched.challenge.ui.screen.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.youarelaunched.challenge.data.repository.model.Vendor
import com.youarelaunched.challenge.ui.screen.state.VendorsScreenUiState
import com.youarelaunched.challenge.ui.screen.view.components.ChatsumerSnackbar
import com.youarelaunched.challenge.ui.screen.view.components.EmptyResultOfSearch
import com.youarelaunched.challenge.ui.screen.view.components.SearchAppBar
import com.youarelaunched.challenge.ui.screen.view.components.VendorItem
import com.youarelaunched.challenge.ui.theme.VendorAppTheme

@Composable
fun VendorsRoute(
    viewModel: VendorsVM
) {
    val uiState by viewModel.uiState.collectAsState()
    val vendorSearch by viewModel.vendorSearch.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()
    VendorsScreen(
        uiState = uiState,
        viewModel = viewModel,
        vendorSearch = vendorSearch?: emptyList(),
        isSearching = isSearching
    )
}

@Composable
fun VendorsScreen(
    viewModel: VendorsVMAbstract,
    uiState: VendorsScreenUiState,
    vendorSearch: List<Vendor>,
    isSearching: Boolean
) {


    Scaffold(
        topBar = {
            SearchAppBar(text = "Search...",
                onTextChange = viewModel::searchVendors,
                onCloseClicked = viewModel::closeSearch,
                onSearchClicked = viewModel::searchVendors
                )
        },
        modifier = Modifier.fillMaxSize(),
        backgroundColor = VendorAppTheme.colors.background,
        snackbarHost = { ChatsumerSnackbar(it) }
    ) { paddings ->
        if (!uiState.vendors.isNullOrEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .padding(paddings)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(
                    vertical = 24.dp,
                    horizontal = 16.dp
                )
            ) {
                if (isSearching){
                  items(vendorSearch){
                          vendor ->
                      VendorItem(
                          vendor = vendor
                      )
                  }
                }else{
                    items(uiState.vendors) { vendor ->
                        VendorItem(
                            vendor = vendor
                        )
                    }
                }


            }
        }else{
            Column(modifier = Modifier
                .padding(paddings)
                .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
                EmptyResultOfSearch()
            }
        }
    }
}