package cl.alvaro.transbankapp.di

import cl.alvaro.transbankapp.datasource.MovieAPIInterface
import cl.alvaro.transbankapp.datasource.MovieAPIInterfaceImpl
import cl.alvaro.transbankapp.moviedetail.MovieDetailPresenter
import cl.alvaro.transbankapp.moviedetail.MovieDetailPresenterImpl
import cl.alvaro.transbankapp.movielist.MovieListPresenter
import cl.alvaro.transbankapp.movielist.MovieListPresenterImpl
import cl.alvaro.transbankapp.repository.MovieRepository
import cl.alvaro.transbankapp.repository.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MoviesAppModule {

    @Provides
    @Singleton
    fun providesMoviesRepository(repository: MovieRepositoryImpl): MovieRepository = repository

    @Provides
    @Singleton
    fun providesMovieAPIInterface(movieAPIInterface: MovieAPIInterfaceImpl): MovieAPIInterface = movieAPIInterface

    @Provides
    fun providesMovieListPresenter(movieListPresenter: MovieListPresenterImpl): MovieListPresenter = movieListPresenter

    @Provides
    fun providesMovieDetailPresenter(movieDetailPresenter: MovieDetailPresenterImpl): MovieDetailPresenter = movieDetailPresenter
}