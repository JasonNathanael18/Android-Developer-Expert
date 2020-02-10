package id.jason.submission2.view


import android.content.res.TypedArray
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import id.jason.submission2.model.Shows
import id.jason.submission2.adapter.MovieViewHolderAdapter
import id.jason.submission2.R
import kotlinx.android.synthetic.main.fragment_movies.*

/**
 * A simple [Fragment] subclass.
 */
class MoviesFragment : Fragment() {

    private lateinit var movieTitleData: Array<String>
    private lateinit var movieDateData: Array<String>
    private lateinit var movieDescData: Array<String>
    private lateinit var movieRatingData: Array<String>
    private lateinit var moviePhotoData: TypedArray
    private var movies = arrayListOf<Shows>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareData()
        addData()
        initRecyclerView()
    }

    private fun prepareData() {
        movieTitleData = resources.getStringArray(R.array.movie_title_list_data)
        movieDateData = resources.getStringArray(R.array.movie_date_list_data)
        movieDescData = resources.getStringArray(R.array.movie_desc_list_data)
        movieRatingData = resources.getStringArray(R.array.movie_rating_list_data)
        moviePhotoData = resources.obtainTypedArray(R.array.movie_photo_list_data)
    }

    private fun addData() {
        for (position in movieTitleData.indices) {
            val shows = Shows(
                movieTitleData[position],
                movieDateData[position],
                movieDescData[position],
                movieRatingData[position],
                moviePhotoData.getResourceId(position, -1)
            )
            movies.add(shows)
        }
    }

    private fun initRecyclerView(){
        rv_movie_list.layoutManager = LinearLayoutManager(context)
        val listHeroAdapter = MovieViewHolderAdapter(movies)
        rv_movie_list.adapter = listHeroAdapter
    }
}
