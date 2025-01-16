package com.example.myapplication.restcountries.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.myapplication.restcountries.core.db.CountriesDb
import com.example.myapplication.restcountries.data.local.entites.CountriesEntity
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class CountriesDaoTest {
    private lateinit var db: CountriesDb
    private lateinit var dao: CountriesDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            CountriesDb::class.java,
        ).build()
        dao = db.userCommunityDao()
    }

    @After
    fun closeDb() = db.close()

    @Test
    fun insert_and_retrieve_a_country() = runTest {
        val country = CountriesEntity(
            capital = listOf("Berlin"),
            capitalInfo = listOf("10.20","5.69"),
            continents = listOf("Europe"),
            flags = "https://upload.wikimedia.org/wikipedia/en/b/ba/Flag_of_Germany.svg",
            name = "Germany",
            population = 83783942,
            startOfWeek = "Monday",
            timezones = listOf("UTC+1", "UTC+2 (during DST)")
        )

        dao.insertAllCountries(listOf(country))

        val recentUsers = dao.getAllCountries().first()
        assertEquals(recentUsers, listOf(country))
    }

    @Test
    fun insert_and_get_details_country() = runTest {
        val country = CountriesEntity(
            capital = listOf("Berlin"),
            capitalInfo = listOf("10.20","5.69"),
            continents = listOf("Europe"),
            flags = "https://upload.wikimedia.org/wikipedia/en/b/ba/Flag_of_Germany.svg",
            name = "Germany",
            population = 83783942,
            startOfWeek = "Monday",
            timezones = listOf("UTC+1", "UTC+2 (during DST)")
        )


        dao.insertAllCountries(listOf(country))

        val userDetails = dao.getCountryDetails(countryName =  country.name).first()
        assertEquals(userDetails, country)
    }

}