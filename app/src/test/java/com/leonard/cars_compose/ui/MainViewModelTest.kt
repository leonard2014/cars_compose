package com.leonard.cars_compose.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.leonard.cars_compose.domain.GetCarsUseCase
import com.leonard.cars_compose.domain.model.Car
import com.leonard.cars_compose.ui.model.toUIModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import kotlinx.coroutines.test.*
import com.google.common.truth.Truth.assertThat
import com.leonard.cars_compose.ui.model.UIState

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @MockK
    private lateinit var getCarsUseCase: GetCarsUseCase

    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `when cars list not empty should emit content`() {
        val car1 =
            Car(year = 2019, manufacturer = "ABC", make = "make1", price = 17500, image = "url1")
        val car2 =
            Car(year = 2021, manufacturer = "DEF", make = "make2", price = 20000, image = null)

        val carsList = listOf(car1, car2)

        val expected = UIState.Content(carsList.toUIModel())

        coroutineTestRule.runBlockingTest {
            coEvery { getCarsUseCase.getCars() } returns Result.success(carsList)

            viewModel = MainViewModel(getCarsUseCase)

            assertThat((viewModel.carsList.value as? UIState.Content)?.list).isEqualTo(expected.list)
        }
    }

    @Test
    fun `when get cars returns empty list should emit empty state`() {
        coroutineTestRule.runBlockingTest {
            coEvery { getCarsUseCase.getCars() } returns Result.success(emptyList())

            viewModel = MainViewModel(getCarsUseCase)

            assertThat(viewModel.carsList.value).isEqualTo(UIState.Empty)
        }
    }

    @Test
    fun `when get cars fails should emit error state`() {
        coroutineTestRule.runBlockingTest {
            coEvery { getCarsUseCase.getCars() } returns Result.failure(Exception())

            viewModel = MainViewModel(getCarsUseCase)

            assertThat(viewModel.carsList.value).isEqualTo(UIState.Error)
        }
    }
}

@ExperimentalCoroutinesApi
class CoroutineTestRule(val dispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()) :
    TestWatcher() {

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
    }

    fun runBlockingTest(block: suspend TestCoroutineScope.() -> Unit) =
        dispatcher.runBlockingTest { block() }
}