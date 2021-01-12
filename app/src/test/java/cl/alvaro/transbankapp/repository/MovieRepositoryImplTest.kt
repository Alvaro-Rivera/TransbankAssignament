package cl.alvaro.transbankapp.repository

import cl.alvaro.transbankapp.anyMockito
import cl.alvaro.transbankapp.datasource.MovieAPIInterface
import cl.alvaro.transbankapp.datasource.MovieDbAPI
import cl.alvaro.transbankapp.gsonReader
import cl.alvaro.transbankapp.models.configuration.ConfigurationMovieDb
import cl.alvaro.transbankapp.models.datasources.MovieCredits
import cl.alvaro.transbankapp.models.datasources.MovieDetail
import cl.alvaro.transbankapp.models.datasources.Results
import cl.alvaro.transbankapp.models.ui.UIMovieDetail
import com.google.gson.Gson
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Test

import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.lang.Exception

@RunWith(MockitoJUnitRunner::class)
class MovieRepositoryImplTest {
    @Mock
    private lateinit var listener:RepositoryOperationCallback
    @Mock
    private lateinit var movieAPIInterface: MovieAPIInterface
    @Mock
    private lateinit var movieDbAPI: MovieDbAPI
    private lateinit var movieList:Results
    private lateinit var castAndCrew: MovieCredits
    private lateinit var movieDetail: MovieDetail
    private lateinit var movieDbConfig: ConfigurationMovieDb
    private lateinit var repository: MovieRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setMainThreadSchedulerHandler { Schedulers.trampoline() }
        createDataForTests()
        repository = MovieRepositoryImpl(movieAPIInterface)
        repository.addListener(listener)
        Mockito.`when`(movieAPIInterface.getAPI()).thenReturn(movieDbAPI)
        Mockito
            .`when`(movieDbAPI.getMovieDBConfiguration(Mockito.anyString()))
            .thenReturn(Single.just(movieDbConfig))
    }

    @Test
    fun fetchMovieList() {
        Mockito.`when`(movieDbAPI.getPopularMovies(Mockito.anyString(), Mockito.anyInt(), Mockito.anyString()))
            .thenReturn(Single.just(movieList))
        repository.fetchMovieList()
        Mockito.verify(listener, Mockito.times(1)).onMoviesSuccess(Mockito.anyList())
    }

    @Test
    fun fetchMovieListError() {
        Mockito.`when`(movieDbAPI.getPopularMovies(Mockito.anyString(), Mockito.anyInt(), Mockito.anyString()))
            .thenReturn(Single.error(Exception("Error!")))
        repository.fetchMovieList()
        Mockito.verify(listener, Mockito.times(1)).onError(anyMockito(Exception::class.java))
    }

    @Test
    fun fetchMovieDetailToDisplay() {
        Mockito.`when`(movieDbAPI.getMovieCastAndCrew(Mockito.anyInt(),Mockito.anyString(),Mockito.anyString()))
            .thenReturn(Single.just(castAndCrew))
        Mockito.`when`(movieDbAPI.getMovieDetails(Mockito.anyInt(), Mockito.anyString(), Mockito.anyString(),Mockito.anyInt()))
            .thenReturn(Single.just(movieDetail))
        repository.fetchMovieDetailToDisplay(32323)
        Mockito.verify(listener, Mockito.times(1)).onMovieDetailSuccess(anyMockito(UIMovieDetail::class.java))

    }

    @Test
    fun fetchMovieDetailToDisplayError() {
        Mockito.`when`(movieDbAPI.getMovieDetails(Mockito.anyInt(), Mockito.anyString(), Mockito.anyString(),Mockito.anyInt()))
            .thenReturn(Single.error(Exception("Error!")))
        repository.fetchMovieDetailToDisplay(32323)
        Mockito.verify(listener, Mockito.times(1)).onError(anyMockito(Exception::class.java))

    }

    private fun createDataForTests() {
        val gson = Gson()
        movieList = gson.fromJson(gsonReader("movies.json"), Results::class.java)
        castAndCrew = gson.fromJson(gsonReader("CastCrewData.json"), MovieCredits::class.java)
        movieDetail = gson.fromJson(gsonReader("movieDetail.json"), MovieDetail::class.java)
        movieDbConfig = gson.fromJson(gsonReader("config.json"), ConfigurationMovieDb::class.java)
    }

    @After
    fun tearDown() {
        repository.removeListener()
    }
}