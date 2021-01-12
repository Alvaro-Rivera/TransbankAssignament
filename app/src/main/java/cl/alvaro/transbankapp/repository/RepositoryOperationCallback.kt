package cl.alvaro.transbankapp.repository

import cl.alvaro.transbankapp.models.ui.UIMovieDetail
import cl.alvaro.transbankapp.models.ui.UIMovieResult
import java.lang.Exception

interface RepositoryOperationCallback {
    fun onMoviesSuccess(movies: List<UIMovieResult>) {
        //empty implementation, override to use it
    }

    fun onMovieDetailSuccess(detail: UIMovieDetail) {
        //empty implementation, override to use it
    }

    fun onError(error: Exception)
}