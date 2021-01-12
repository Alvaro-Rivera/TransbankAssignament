package cl.alvaro.transbankapp.moviedetail

import cl.alvaro.transbankapp.base.BasePresenter
import cl.alvaro.transbankapp.models.ui.UIMovieDetail
import cl.alvaro.transbankapp.repository.MovieRepository
import cl.alvaro.transbankapp.repository.MovieRepositoryImpl.Companion.NOT_AVAILABLE
import cl.alvaro.transbankapp.repository.RepositoryOperationCallback
import java.lang.Exception
import javax.inject.Inject

class MovieDetailPresenterImpl @Inject constructor(val repository: MovieRepository)
    : MovieDetailPresenter, RepositoryOperationCallback, BasePresenter<MovieDetailView> () {

    private var movieDetailUI = UIMovieDetail(NOT_AVAILABLE,
        "2",
        "1/1/2020",
        "poster.jpg",
        NOT_AVAILABLE,
        NOT_AVAILABLE,
        NOT_AVAILABLE)

    override fun getMovieDetail(movieId: Int) {
        view?.hideError()
        view?.showLoading()
        repository.addListener(this)
        repository.fetchMovieDetailToDisplay(movieId)
    }

    override fun getDetail(): UIMovieDetail = movieDetailUI

    override fun onMovieDetailSuccess(detail: UIMovieDetail) {
        movieDetailUI = detail
        view?.stopLoading()
        view?.loadDetail()
    }
    override fun onError(error: Exception) {
        view?.stopLoading()
        view?.showError("Oops, unexpected error, try again later")
    }
}