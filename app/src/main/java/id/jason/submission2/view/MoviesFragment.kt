package id.jason.submission2.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.jason.submission2.adapter.MovieViewHolderAdapter
import id.jason.submission2.R
import id.jason.submission2.model.ShowsDetail
import id.jason.submission2.viewModel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movies.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class MoviesFragment : Fragment() {

    private lateinit var viewModel: MovieViewModel
    private lateinit var adapter: MovieViewHolderAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MovieViewModel::class.java)
        val language = if (Locale.getDefault().language == "in") "id" else Locale.getDefault().language
        val error = resources.getString(R.string.actionbar_title)
        viewModel.setData(language,context,error)
        showLoading(true)

        viewModel.getData().observe(viewLifecycleOwner, Observer {
                t ->
            t?.results?.let { showData(it) }
        })
    }

    private fun showData(data: List<ShowsDetail>) {
        adapter.setData(data as ArrayList<ShowsDetail>)
        showLoading(false)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            pb_movie.visibility = View.VISIBLE
            rv_movie_list.visibility = View.GONE
        } else {
            pb_movie.visibility = View.GONE
            rv_movie_list.visibility = View.VISIBLE
        }
    }

    private fun initRecyclerView(){
        adapter = MovieViewHolderAdapter()
        adapter.notifyDataSetChanged()
        rv_movie_list.layoutManager = LinearLayoutManager(context)
        rv_movie_list.adapter = adapter
    }
}
