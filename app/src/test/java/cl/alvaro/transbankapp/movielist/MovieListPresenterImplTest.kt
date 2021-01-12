package cl.alvaro.transbankapp.movielist

import cl.alvaro.transbankapp.anyMockito
import cl.alvaro.transbankapp.repository.MovieRepository
import cl.alvaro.transbankapp.repository.RepositoryOperationCallback
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieListPresenterImplTest {

    @Mock
    private lateinit var repository:MovieRepository
    @Mock
    private lateinit var view: MovieListView
    private lateinit var presenter: MovieListPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        presenter = MovieListPresenterImpl(repository)
        presenter.attachView(view)
    }

    @After
    fun tearDown() {
        presenter.removeView()
    }

    @Test
    fun getMoviesList() {
        presenter.getMoviesList()
        Mockito.verify(view, Mockito.times(1)).showLoading()
        Mockito.verify(view, Mockito.times(1)).hideError()
        Mockito.verify(repository, Mockito.times(1))
            .addListener(anyMockito(RepositoryOperationCallback::class.java))
        Mockito.verify(repository, Mockito.times(1)).fetchMovieList()
    }

    @Test
    fun getMovieListData() {
        assertTrue(presenter.getMovieListData().isEmpty())
    }
}