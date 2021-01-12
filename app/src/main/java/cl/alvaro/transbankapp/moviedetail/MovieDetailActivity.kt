package cl.alvaro.transbankapp.moviedetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import cl.alvaro.transbankapp.MoviesApplication
import cl.alvaro.transbankapp.R
import cl.alvaro.transbankapp.databinding.ActivityMovieDetailBinding
import cl.alvaro.transbankapp.datasource.AppConstants.MOVIE_ID
import com.bumptech.glide.Glide
import javax.inject.Inject

class MovieDetailActivity : AppCompatActivity(), MovieDetailView {

    private lateinit var binding: ActivityMovieDetailBinding
    @Inject
    lateinit var presenter: MovieDetailPresenter
    private lateinit var poster: ImageView
    private lateinit var title: TextView
    private lateinit var year: TextView
    private lateinit var rating: TextView
    private lateinit var director: TextView
    private lateinit var actors:TextView
    private lateinit var overview: TextView
    private lateinit var loading: ProgressBar
    private lateinit var errorText: TextView
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        poster = binding.moviePoster
        title = binding.movieTitle
        year = binding.movieYear
        rating = binding.movieRating
        director = binding.movieDirector
        actors = binding.movieActors
        overview = binding.movieSynopsis
        loading = binding.progress
        errorText = binding.movieDetailError
        toolbar = binding.toolbar
        toolbar.title = "Movie Detail"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (application as MoviesApplication).getAppDIComponent().inject(this)
        presenter.attachView(this)
        val idMovie = intent.extras?.getInt(MOVIE_ID)?: 0
        presenter.getMovieDetail(idMovie)
    }

    override fun onBackPressed() {
        finish()
    }

    override fun loadDetail() {
        val detail = presenter.getDetail()
        title.text = detail.movieTitle
        year.text = detail.moviePremierDate
        rating.text = detail.movieRating
        director.text = detail.movieDirector
        actors.text = detail.movieActors
        overview.text = detail.movieOverview
        Glide.with(this)
            .load(detail.moviePosterUrl)
            .placeholder(R.drawable.ic_movie_open_outline)
            .into(poster)
    }

    override fun showLoading() {
        loading.visibility = VISIBLE
    }

    override fun stopLoading() {
        loading.visibility = GONE
    }

    override fun showError(errorMessage: String) {
        errorText.text = errorMessage
        errorText.visibility = VISIBLE
    }

    override fun hideError() {
        errorText.visibility = GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.removeView()
    }
}