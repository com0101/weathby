package com.example.weathby

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.weathby.home.CityCard
import com.example.weathby.resource.Resource
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {
    private val getCityOb: Observer<Resource<CityCard>> = mockk(relaxed = true)
    private val isCardInsertOb: Observer<Boolean> = mockk(relaxed = true)
    private val repository: WeatherRepository = mockk(relaxed = true)
    private lateinit var viewModel: MainViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        viewModel = MainViewModel(repository)
        viewModel.getCity.observeForever(getCityOb)
        viewModel.isCardInserted.observeForever(isCardInsertOb)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getCurrentForecast() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)
        val mockkCity = mockk<CityCard>(relaxed = true).apply {
            every { temp } returns "35"
        }
//        coEvery { repository.getForecast(any()) } returns Resource.Success(mockkCity)

        try {
            viewModel.getForecast("test")
            verify { isCardInsertOb.onChanged(any()) }
            assertEquals("35", (viewModel.getCity.value as Resource.Success).data?.temp)
        } finally {
            Dispatchers.resetMain()
        }
    }
}