package cl.alvaro.transbankapp.repository

import cl.alvaro.transbankapp.datasource.AppConstants.API_KEY
import cl.alvaro.transbankapp.extensions.formatStringDate
import cl.alvaro.transbankapp.extensions.getCastString
import cl.alvaro.transbankapp.extensions.getDirectorsString
import cl.alvaro.transbankapp.models.configuration.ConfigurationMovieDb
import cl.alvaro.transbankapp.models.datasources.MovieCredits
import cl.alvaro.transbankapp.models.datasources.MovieDetail
import cl.alvaro.transbankapp.models.datasources.MovieResult
import cl.alvaro.transbankapp.models.datasources.Results
import cl.alvaro.transbankapp.models.ui.UIMovieDetail
import cl.alvaro.transbankapp.models.ui.UIMovieResult
import cl.alvaro.transbankapp.base.RxBase
import cl.alvaro.transbankapp.datasource.MovieAPIInterface
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.lang.Exception
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private var movieAPIInterface: MovieAPIInterface)
    : MovieRepository, RxBase() {
    private var movieDbConfig: ConfigurationMovieDb? = null
    private var listener:RepositoryOperationCallback? = null

    override fun addListener(listener: RepositoryOperationCallback) {
        this.listener = listener
    }

    override fun removeListener() {
        unSubscribeAll()
    }

    override fun fetchMovieList() {
        if (movieDbConfig == null) {
            getMovieDbConfig()
        } else {
            getPopularMovies()
        }
    }

    private fun getPopularMovies() {
        addSubscription(
            movieAPIInterface
                .getAPI()
                .getPopularMovies(
                    API_KEY,
                    1,
                    language
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Results>() {
                    override fun onSuccess(results: Results) {
                        results.results?.let {
                            handleMovieResultData(it)
                        }
                    }

                    override fun onError(e: Throwable) {
                        listener?.onError(Exception(e))
                    }

                })
        )
    }

    private fun getMovieDbConfig() {
        addSubscription(
            movieAPIInterface
                .getAPI()
                .getMovieDBConfiguration(API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ConfigurationMovieDb>() {
                    override fun onSuccess(configuration: ConfigurationMovieDb) {
                        movieDbConfig = configuration
                        getPopularMovies()
                    }

                    override fun onError(e: Throwable) {
                        listener?.onError(Exception(e))
                    }

                })
        )
    }

    override fun fetchMovieDetailToDisplay(movieId: Int) {
        addSubscription(movieAPIInterface
            .getAPI()
            .getMovieDetails(movieId, API_KEY, language, 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: DisposableSingleObserver<MovieDetail> () {
                override fun onSuccess(movieDetail: MovieDetail) {
                    getMovieCastAndCrew(movieId, movieDetail)
                }

                override fun onError(e: Throwable) {
                    listener?.onError(Exception(e))
                }

            }))
    }

    private fun getMovieCastAndCrew(movieId: Int, movieDetail: MovieDetail) {
        addSubscription(movieAPIInterface
            .getAPI()
            .getMovieCastAndCrew(movieId, API_KEY, language)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object :DisposableSingleObserver<MovieCredits> () {
                override fun onSuccess(movieCredits: MovieCredits) {
                    handleMovieDetailResult(movieDetail, movieCredits)
                }

                override fun onError(e: Throwable) {
                    listener?.onError(Exception(e))
                }

            }))
    }

    private fun handleMovieResultData(results:List<MovieResult> ) {
        addSubscription(Single
            .fromCallable { processMovieResults(results) }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object :DisposableSingleObserver<List<UIMovieResult>> () {
                override fun onSuccess(movieResults: List<UIMovieResult>) {
                    listener?.onMoviesSuccess(movieResults)
                }

                override fun onError(e: Throwable) {
                    listener?.onError(Exception(e))
                }

            }))
    }

    private fun processMovieResults(results: List<MovieResult>):List<UIMovieResult> {
        val uiResults = mutableListOf<UIMovieResult>()
        results.forEach {
            val id = it.id?: 0
            val title = it.originalTitle?: NOT_AVAILABLE
            val posterUrl = it.posterPath?.let { path -> createUrlForPoster(path) }?:""
            val overview = it.overview?: NOT_AVAILABLE
            uiResults.add(UIMovieResult(id, title, posterUrl, overview))
        }
        return uiResults
    }

    private fun handleMovieDetailResult(detail: MovieDetail, castAndCrew: MovieCredits) {
        addSubscription(Single
            .fromCallable { handleMovieDetail(detail, castAndCrew) }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: DisposableSingleObserver<UIMovieDetail> () {
                override fun onSuccess(movieDetail: UIMovieDetail) {
                    listener?.onMovieDetailSuccess(movieDetail)
                }

                override fun onError(e: Throwable) {
                    listener?.onError(Exception(e))
                }

            }))
    }

    private fun handleMovieDetail(detail: MovieDetail, castAndCrew: MovieCredits):UIMovieDetail {
        val title = detail.originalTitle?: NOT_AVAILABLE
        val rating = "Rating: ${detail.voteAverage?.let { "$it" }?: NOT_AVAILABLE_SHORT}"
        val releaseDate = "Release Date: ${detail.releaseDate?.formatStringDate()?: NOT_AVAILABLE_SHORT}"
        val posterUrl = detail.posterPath?.let { createUrlForPoster(it)}?: ""
        val director = "Director: ${castAndCrew.crew?.getDirectorsString()?: NOT_AVAILABLE}"
        val actors = "Actors: ${castAndCrew.cast?.getCastString()?: NOT_AVAILABLE}"
        val overview = "Overview: ${detail.overview?: NOT_AVAILABLE}"
        return UIMovieDetail(title, rating, releaseDate, posterUrl, director, actors, overview)
    }

    private fun createUrlForPoster(path:String): String {
        val securePath = movieDbConfig?.images?.secureBaseUrl?: ""
        val size = movieDbConfig?.images?.posterSizes?.firstOrNull { it == "w500" || it == "w780"}?:""
        return if (securePath.isNotBlank() && size. isNotBlank()) "${securePath}/${size}/${path}"
                  else ""
    }

    companion object {
        const val language = "en-US"
        const val NOT_AVAILABLE = "Not Available"
        const val NOT_AVAILABLE_SHORT = "N/A"
    }

}