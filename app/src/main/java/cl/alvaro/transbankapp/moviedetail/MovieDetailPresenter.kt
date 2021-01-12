package cl.alvaro.transbankapp.moviedetail

import cl.alvaro.transbankapp.base.Presenter
import cl.alvaro.transbankapp.models.ui.UIMovieDetail

interface MovieDetailPresenter: Presenter<MovieDetailView> {
    fun getMovieDetail(movieId:Int)
    fun getDetail():UIMovieDetail
}