package com.jocode.item

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.jocode.domain.GetItemDescriptionUseCase
import com.jocode.model.search.ProductDescription
import com.jocode.models.ItemDetail
import com.jocode.navigation.Screens
import io.mockk.coEvery
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ItemDetailViewModelTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: ItemDetailViewModel
    private var useCase: GetItemDescriptionUseCase = mockk(relaxed = true)

    private var savedStateHandle = SavedStateHandle(
        mapOf(
            Screens.Detail.ITEM_ID_NAV_KEY to "MLA1234"
        )
    )


    @Before
    fun setUp() {
        coEvery { useCase.invoke(any()) } returns Result.success(
            ProductDescription(description = "description")
        )
        viewModel = ItemDetailViewModel(useCase, savedStateHandle)
    }

    @Test
    fun `test initial state in screen with empty keyHandle`() = runBlocking {
        viewModel = ItemDetailViewModel(useCase, mockk())
        viewModel.uiState.test {
            val state = awaitItem()
            assertThat(state).isInstanceOf(ItemDetailUiState.LoadFailed::class.java)
        }
    }

    @Test
    fun `test initial state in screen with keyHandle`() = runBlocking {
        viewModel.uiState.test {
            val initialState = awaitItem()
            assertThat(initialState).isInstanceOf(ItemDetailUiState.Loading::class.java)
        }
    }

    @Test
    fun `test initial state in screen with keyHandle and success`() = runBlocking {
        viewModel.uiState.test {
            val initialState = awaitItem()
            assertThat(initialState).isInstanceOf(ItemDetailUiState.Loading::class.java)

            viewModel.onItemDetailReceived(itemDetail)

            val stateLoaded = awaitItem()
            assertThat(stateLoaded).isInstanceOf(ItemDetailUiState.Success::class.java)
        }
    }

    @Test
    fun `test initial state in screen with keyHandle and error`() = runBlocking {
        coEvery { useCase.invoke(any()) } returns Result.failure(
            Exception("error")
        )
        viewModel = ItemDetailViewModel(useCase, savedStateHandle)
        viewModel.uiState.test {
            val stateLoaded = awaitItem()
            assertThat(stateLoaded).isInstanceOf(ItemDetailUiState.LoadFailed::class.java)
        }
    }

    private val itemDetail = ItemDetail(
        id = "MLA1234",
        title = "title",
        price = 100.0,
        thumbnail = "thumbnail",
        availableQuantity = 1,
        soldQuantity = 1,
        condition = "new",
        currencyId = "COP"
    )

}