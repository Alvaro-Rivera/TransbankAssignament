package cl.alvaro.transbankapp.moviedetail

import cl.alvaro.transbankapp.anyMockito
import cl.alvaro.transbankapp.repository.MovieRepository
import cl.alvaro.transbankapp.repository.MovieRepositoryImpl
import cl.alvaro.transbankapp.repository.RepositoryOperationCallback
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieDetailPresenterImplTest {
    @Mock
    private lateinit var repository:MovieRepository
    @Mock
    private lateinit var view: MovieDetailView
    private lateinit var presenter:MovieDetailPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        presenter = MovieDetailPresenterImpl(repository)
        presenter.attachView(view)
    }

    @After
    fun tearDown() {
        presenter.removeView()
    }

    @Test
    fun getMovieDetail() {
        presenter.getMovieDetail(45434)
        Mockito.verify(view, Mockito.times(1)).showLoading()
        Mockito.verify(view, Mockito.times(1)).hideError()
        Mockito.verify(repository, Mockito.times(1))
            .addListener(anyMockito(RepositoryOperationCallback::class.java))
        Mockito.verify(repository, Mockito.times(1))
            .fetchMovieDetailToDisplay(Mockito.anyInt())
    }

    @Test
    fun getDetail() {
        assertEquals(MovieRepositoryImpl.NOT_AVAILABLE, presenter.getDetail().movieTitle)
    }
}