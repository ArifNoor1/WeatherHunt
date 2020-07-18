package com.devadilarif.weatherhunt.viewmodels

import org.junit.runner.RunWith
import com.devadilarif.weatherhunt.repo.CovidRepository
import com.devadilarif.weatherhunt.repo.NewsRepository
import com.devadilarif.weatherhunt.repo.WeatherRepository
import com.devadilarif.weatherhunt.repo.local.model.COVID19
import com.devadilarif.weatherhunt.repo.local.model.Covid19Response
import com.devadilarif.weatherhunt.repo.remote.NetworkService
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.AdditionalAnswers.returnsElementsOf
import org.mockito.AdditionalAnswers.returnsFirstArg
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
@RunWith(MockitoJUnitRunner::class)
class HomeFragmentViewModelTest {

    @Mock
    private lateinit var covidRepository: CovidRepository

    @Mock
    private lateinit var newsRepository : NewsRepository

    @Mock
    private lateinit var weatherRepository: WeatherRepository

    @Mock
    private lateinit var networkService : NetworkService

    private lateinit var viewModel : HomeFragmentViewModel

    @Before
    fun setup(){
        viewModel = HomeFragmentViewModel(weatherRepository,newsRepository,covidRepository)
    }

    //    doNothing().when(mock).someVoidMethod();
    @Test
    fun givenServerResponse200ForCovid_whenRequestingData_shouldUpdateUI(){

    }

    @Test
    fun givenServerResponse200ForWeather_whenRequestingData_shouldUpdateUI(){

    }

    @Test
    fun givenServerResponse200ForNews_whenRequestingData_shouldUpdateUI(){

    }

    @Test
    fun givenServerResponse200ForNews_whenRefresh_shouldUpdateNewsAdapter(){

    }

    @Test
    fun givenServerResponse200ForCOVID_whenRefresh_shouldUpdateCovidData(){

    }

    @Test
    fun givenServerResponse200ForWeather_whenRefresh_shouldUpdateWeatherAdapter(){

    }

    @Test
    fun givenServerResponse404ForNews_whenRefresh_shouldShowSnackBar(){

    }

    @Test
    fun givenServerResponse404ForCovid_whenRefresh_shouldShowSnackBar(){

    }

    @Test
    fun givenServerResponse404ForWeather_whenRefresh_shouldShowSnackBar(){

    }

    @After
    fun cleanup(){

    }
}