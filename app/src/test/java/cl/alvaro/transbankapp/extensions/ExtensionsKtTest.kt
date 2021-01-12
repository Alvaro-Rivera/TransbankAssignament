package cl.alvaro.transbankapp.extensions

import cl.alvaro.transbankapp.gsonReader
import cl.alvaro.transbankapp.models.datasources.MovieCredits
import com.google.gson.Gson
import org.junit.Test

import org.junit.Assert.*
import java.io.InputStreamReader

class ExtensionsKtTest {

    @Test
    fun formatStringDate() {
        val correctDate = "2017-05-13"
        val noDate = ""
        val resultDate = correctDate.formatStringDate()
        val noDateResult = noDate.formatStringDate()
        assertEquals("13/05/2017", resultDate)
        assertEquals("N/A", noDateResult)
    }

    @Test
    fun getDirectorsString() {
        val gson = Gson()
        val inputStreamReader = gsonReader("CastCrewData.json")
        val castAndCrew = gson.fromJson(inputStreamReader, MovieCredits::class.java)
        assertEquals("Patty Jenkins", castAndCrew.crew?.getDirectorsString())
    }

    @Test
    fun getCastString() {
        val gson = Gson()
        val inputStreamReader = gsonReader("CastCrewData.json")
        val castAndCrew = gson.fromJson(inputStreamReader, MovieCredits::class.java)
        assertTrue(castAndCrew.cast?.getCastString()?.contains("Gal Gadot") == true)
    }
}