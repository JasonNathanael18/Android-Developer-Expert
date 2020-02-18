package id.jason.submission2.view

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import id.jason.submission2.adapter.FavouriteViewHolderAdapter

import id.jason.submission2.R
import id.jason.submission2.model.ShowsDetail
import id.jason.submission2.viewModel.FavouriteViewModel
import kotlinx.android.synthetic.main.favourite_fragment.*

class FavouriteFragment : Fragment(), IFavouriteListener {
    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"

        fun newInstance(index: Int): FavouriteFragment {
            val fragment = FavouriteFragment()
            val bundle = Bundle()
            bundle.putInt(ARG_SECTION_NUMBER, index)
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var viewModel: FavouriteViewModel
    private var index: Int = 0
    private lateinit var mContext: Context


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favourite_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        index = arguments?.getInt(ARG_SECTION_NUMBER, 0) as Int
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FavouriteViewModel::class.java)
        if (arguments != null) {
            when (index) {
                1 -> {
                    viewModel.allMovieShow.observe(viewLifecycleOwner, Observer { favoriteList ->
                        if (favoriteList.isNullOrEmpty()) {
                            favourite_empty.visibility = View.VISIBLE
                            rv_favourite.visibility = View.GONE
                        } else {
                            initRecyler(favoriteList)
                            favourite_empty.visibility = View.GONE
                            rv_favourite.visibility = View.VISIBLE
                        }
                    })
                }
                2 -> {
                    viewModel.allTvShow.observe(viewLifecycleOwner, Observer { favoriteList ->
                        if (favoriteList.isNullOrEmpty()) {
                            favourite_empty.visibility = View.VISIBLE
                            rv_favourite.visibility = View.GONE
                        } else {
                            initRecyler(favoriteList)
                            favourite_empty.visibility = View.GONE
                            rv_favourite.visibility = View.VISIBLE
                        }
                    })
                }
            }
        }
    }

    private fun initRecyler(favoriteList: List<ShowsDetail>) {
        val adapter = FavouriteViewHolderAdapter(mContext, favoriteList,this)
        rv_favourite.setHasFixedSize(true)
        rv_favourite.layoutManager = LinearLayoutManager(mContext)
        rv_favourite.adapter = adapter
    }

    override fun onFavoriteDeleteClick(showId: Int) {
        viewModel.deleteShowById(showId)
    }
}
