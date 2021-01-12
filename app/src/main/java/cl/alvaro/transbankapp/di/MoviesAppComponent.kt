package cl.alvaro.transbankapp.di

import cl.alvaro.transbankapp.moviedetail.MovieDetailActivity
import cl.alvaro.transbankapp.movielist.MovieListActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MoviesAppModule::class])
interface MoviesAppComponent {
    fun inject(activity:MovieListActivity)
    fun inject(activity:MovieDetailActivity)
}