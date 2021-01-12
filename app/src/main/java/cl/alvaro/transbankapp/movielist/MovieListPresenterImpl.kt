package cl.alvaro.transbankapp.movielist

import cl.alvaro.transbankapp.base.BasePresenter
import cl.alvaro.transbankapp.models.ui.UIMovieResult
import cl.alvaro.transbankapp.repository.MovieRepository
import cl.alvaro.transbankapp.repository.RepositoryOperationCallback
import java.lang.Exception
import javax.inject.Inject

class MovieListPresenterImpl @Inject constructor(var repository: MovieRepository)
    : MovieListPresenter, RepositoryOperationCallback, BasePresenter<MovieListView> () {

    private val movieList = mutableListOf<UIMovieResult>()

    override fun removeView() {
        super.removeView()
        repository.removeListener()
    }

    override fun getMoviesList() {
        view?.showLoading()
        view?.hideError()
        repository.addListener(this)
        repository.fetchMovieList()
    }

    override fun getMovieListData(): MutableList<UIMovieResult> = movieList.toMutableList()

    override fun onMoviesSuccess(movies: List<UIMovieResult>) {
        movieList.addAll(movies)
        view?.stopLoading()
        view?.updateList()
    }

    override fun onError(error: Exception) {
        view?.stopLoading()
        view?.showError("Oops, unexpected error, try again later")
    }

}