package com.youarelaunched.challenge.ui.screen.view

import com.youarelaunched.challenge.data.repository.VendorsRepository
import com.youarelaunched.challenge.data.repository.model.Vendor
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class VendorsVMTest{
    private lateinit var viewModel: VendorsVM

    private val repository = mock<VendorsRepository>()

    @Before
    fun setup(){
        Dispatchers.setMain(UnconfinedTestDispatcher())
        viewModel = VendorsVM(repository = repository)
    }

    @Test
    fun shouldLoadedSuccessfully() = runTest{
        val list = listOf(Vendor(1,
            "name",
            "photo",
            "",true,
        null,
            ""
            ))
        Mockito.`when`(repository.getVendors()).thenReturn(list)
        viewModel.getVendors()
        assertEquals(list, viewModel.uiState.value.vendors)
    }

    @Test
    fun loadedWithError() = runTest{
        Mockito.`when`(repository.getVendors()).thenReturn(null)
        viewModel.getVendors()
        assertEquals(null, viewModel.uiState.value.vendors)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

}
//Data was loaded successfully and the list contains at least one item
//Data loaded with error