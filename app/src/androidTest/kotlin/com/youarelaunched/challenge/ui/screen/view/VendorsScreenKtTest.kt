package com.youarelaunched.challenge.ui.screen.view

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.youarelaunched.challenge.data.repository.model.Vendor
import com.youarelaunched.challenge.ui.theme.VendorAppTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class VendorsScreenKtTest{
    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()


    private lateinit var viewModel: VendorsVMAbstract

    @Before
    fun setup(){
        viewModel = FakeVendorsVM.provideFakeVendorVM()

    }

    @Test
    fun shouldNoResultIfListEmpty(){

        composeRule.mainClock.autoAdvance = false
        val uiState = FakeVendorsVM.uiState.value
        val vendorSearch: List<Vendor> = emptyList()
        composeRule.setContent {
            VendorAppTheme {
                VendorsScreen(
                    viewModel = viewModel,
                    uiState = uiState,
                    vendorSearch = vendorSearch,
                    isSearching = false)
            }

        }
        Thread.sleep(2000)
    }

    @Test
    fun shouldResultIfListNotEmpty(){
        viewModel.getVendors()
        composeRule.mainClock.autoAdvance = false
        val uiState = FakeVendorsVM.uiState.value
        val vendorSearch: List<Vendor> = emptyList()
        composeRule.setContent {
            VendorAppTheme {
                VendorsScreen(
                    viewModel = viewModel,
                    uiState = uiState,
                    vendorSearch = vendorSearch,
                    isSearching = false)
            }

        }
        Thread.sleep(2000)
    }
}