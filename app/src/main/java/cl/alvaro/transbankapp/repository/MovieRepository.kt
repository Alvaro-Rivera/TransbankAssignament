package cl.alvaro.transbankapp.repository

interface MovieRepository {
    fun addListener(listener:RepositoryOperationCallback)
    fun removeListener()
    fun fetchMovieList()
    fun fetchMovieDetailToDisplay(movieId: Int)
}