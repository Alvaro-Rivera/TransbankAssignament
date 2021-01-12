package cl.alvaro.transbankapp.movielist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cl.alvaro.transbankapp.MoviesApplication
import cl.alvaro.transbankapp.databinding.ActivityMovieListBinding
import cl.alvaro.transbankapp.datasource.AppConstants.MOVIE_ID
import cl.alvaro.transbankapp.models.ui.UIMovieResult
import cl.alvaro.transbankapp.moviedetail.MovieDetailActivity
import javax.inject.Inject

class MovieListActivity : AppCompatActivity(), MovieClickListener, MovieListView {

    @Inject
    lateinit var presenter: MovieListPresenter
    private lateinit var binding:ActivityMovieListBinding
    lateinit var list:RecyclerView
    lateinit var loading:ProgressBar
    lateinit var errorMessage: TextView

    private val adapter:MovieListAdapter by lazy {
        MovieListAdapter(presenter.getMovieListData(), this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        (application as MoviesApplication).getAppDIComponent().inject(this)
        presenter.attachView(this)
        list = binding.movieList
        loading = binding.progressBar
        errorMessage = binding.movieError
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter
        presenter.getMoviesList()
    }

    override fun onItemClick(result: UIMovieResult) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(MOVIE_ID, result.movieId)
        startActivity(intent)
    }

    override fun updateList() {
        adapter.updateListData(presenter.getMovieListData())
    }

    override fun showLoading() {
        loading.visibility = VISIBLE
    }

    override fun stopLoading() {
        loading.visibility = GONE
    }

    override fun showError(errorMessage: String) {
        this.errorMessage.visibility = VISIBLE
    }

    override fun hideError() {
        errorMessage.visibility = GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.removeView()
        adapter.removeListener()
    }
}