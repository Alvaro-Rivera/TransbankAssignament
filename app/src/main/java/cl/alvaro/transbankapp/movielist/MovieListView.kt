package cl.alvaro.transbankapp.movielist

import cl.alvaro.transbankapp.base.ViewPresenter

interface MovieListView: ViewPresenter {
    fun updateList()
}