package com.example.weathby

import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.weathby.home.CityCard
import com.example.weathby.resource.Resource
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class HomeFragmentTest {
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val activityTestResult = ActivityScenarioRule(MainActivity::class.java)

    @BindValue
    val viewModel: MainViewModel = mockk(relaxed = true)

    private var fakeCityCard = MutableLiveData<Resource<CityCard>>()

    @Before
    fun setUp() {
        hiltRule.inject()
        every { viewModel.getCity } returns fakeCityCard
    }

    @Test
    fun shouldShowEmptyCityCardWhenDataIsSet() {
//        fakeCityCard.postValue(Resource.Success())
//        onView(withId())

    }
}
