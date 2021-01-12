package cl.alvaro.transbankapp.datasource

import cl.alvaro.transbankapp.datasource.AppConstants.CAST_CREW
import cl.alvaro.transbankapp.datasource.AppConstants.CONFIGURATION
import cl.alvaro.transbankapp.datasource.AppConstants.DETAIL
import cl.alvaro.transbankapp.datasource.AppConstants.POPULAR
import cl.alvaro.transbankapp.models.configuration.ConfigurationMovieDb
import cl.alvaro.transbankapp.models.datasources.MovieCredits
import cl.alvaro.transbankapp.models.datasources.MovieDetail
import cl.alvaro.transbankapp.models.datasources.Results
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDbAPI {
    @GET(POPULAR)
    fun getPopularMovies(@Query("api_key") apiKey:String,
                         @Query("page") page:Int,
                         @Query("language") language:String):Single<Results>

    @GET(DETAIL)
    fun getMovieDetails(@Path("movie_id") movieId:Int,
                        @Query("api_key") apiKey:String,
                        @Query("language") language:String,
                        @Query("append_to_response") appendToResponse:Int):Single<MovieDetail>

    @GET(CAST_CREW)
    fun getMovieCastAndCrew(@Path("movie_id") movieId:Int,
                            @Query("api_key") apiKey:String,
                            @Query("language") language:String):Single<MovieCredits>

    @GET(CONFIGURATION)
    fun getMovieDBConfiguration(@Query("api_key") apiKey:String):Single<ConfigurationMovieDb>
}