package cl.alvaro.transbankapp.moviedetail

import cl.alvaro.transbankapp.base.ViewPresenter

interface MovieDetailView: ViewPresenter {
    fun loadDetail()
}