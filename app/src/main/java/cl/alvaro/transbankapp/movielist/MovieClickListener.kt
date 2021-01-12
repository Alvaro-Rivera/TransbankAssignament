package cl.alvaro.transbankapp.movielist

import cl.alvaro.transbankapp.models.ui.UIMovieResult

interface MovieClickListener {
    fun onItemClick(result: UIMovieResult)
}