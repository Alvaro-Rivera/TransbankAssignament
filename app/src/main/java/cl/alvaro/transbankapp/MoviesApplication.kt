package cl.alvaro.transbankapp

import android.app.Application
import cl.alvaro.transbankapp.di.DaggerMoviesAppComponent
import cl.alvaro.transbankapp.di.MoviesAppComponent

class MoviesApplication: Application() {

    private lateinit var movieAppComponent: MoviesAppComponent

    override fun onCreate() {
        super.onCreate()
        movieAppComponent = DaggerMoviesAppComponent.builder().build()
    }

    fun getAppDIComponent(): MoviesAppComponent = movieAppComponent
}