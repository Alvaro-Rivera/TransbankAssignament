package cl.alvaro.transbankapp.movielist

import cl.alvaro.transbankapp.base.Presenter
import cl.alvaro.transbankapp.models.ui.UIMovieResult

interface MovieListPresenter: Presenter<MovieListView> {
    fun getMoviesList()
    fun getMovieListData():MutableList<UIMovieResult>
}