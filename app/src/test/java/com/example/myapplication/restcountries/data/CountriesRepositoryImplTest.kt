package com.example.myapplication.restcountries.data

import com.example.core.network_wrapper.AppResult
import com.example.myapplication.restcountries.core.network.network_wrapper.ApiResult
import com.example.myapplication.restcountries.core.network.utils.NetworkHandler
import com.example.myapplication.restcountries.data.local.CountriesLocalDataSourceTest
import com.example.myapplication.restcountries.data.mapper.toEntity
import com.example.myapplication.restcountries.data.remote.CountriesRemoteDataSourceTest
import io.mockk.MockKAnnotations
import io.mockk.every
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import kotlin.test.*
import kotlin.test.Test

class CountriesRepositoryImplTest {


    private val testScope = TestScope(UnconfinedTestDispatcher())
    private lateinit var testLocalDataSource: CountriesLocalDataSourceTest
    private lateinit var repository: CountriesRepositoryImpl
    private lateinit var testRemoteDataSource: CountriesRemoteDataSourceTest


    @MockK
    private lateinit var networkHandler: NetworkHandler


    @Before
    fun setup() {
        MockKAnnotations.init(this)
        testLocalDataSource = CountriesLocalDataSourceTest()
        testRemoteDataSource = CountriesRemoteDataSourceTest()
        repository =
            CountriesRepositoryImpl(
                localDataSource = testLocalDataSource,
                remoteDataSource = testRemoteDataSource,
                networkHandler = networkHandler
            )
    }

    @Test
    fun `getAllCountryFromNetwork returns success when network is available and remote call succeeds`() =
        testScope.runTest {
            every { networkHandler.hasNetworkConnection() } returns true
            val resourcesFromNetwork =
                (testRemoteDataSource.getCountries() as ApiResult.Success).data.toEntity()

            testLocalDataSource.insertAllCountries(resourcesFromNetwork)
            val countriesFromDatabase = testLocalDataSource.getAllCountries().first()
            assertEquals(resourcesFromNetwork, countriesFromDatabase)
        }

}