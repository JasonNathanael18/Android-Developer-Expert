package id.jason.submission2.view


import android.content.res.TypedArray
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import id.jason.submission2.R
import id.jason.submission2.model.Shows
import kotlinx.android.synthetic.main.fragment_tv_show.*
import id.jason.submission2.adapter.TvViewHolderAdapter
/**
 * A simple [Fragment] subclass.
 */
class TvShowFragment : Fragment() {

    private lateinit var tvTitleData: Array<String>
    private lateinit var tvDateData: Array<String>
    private lateinit var tvDescData: Array<String>
    private lateinit var tvRatingData: Array<String>
    private lateinit var tvPhotoData: TypedArray
    private var tv = arrayListOf<Shows>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareData()
        addData()
        initRecyclerView()
    }

    private fun prepareData() {
        tvTitleData = resources.getStringArray(R.array.tv_title_list_data)
        tvDateData = resources.getStringArray(R.array.tv_date_list_data)
        tvDescData = resources.getStringArray(R.array.tv_desc_list_data)
        tvRatingData = resources.getStringArray(R.array.tv_rating_list_data)
        tvPhotoData = resources.obtainTypedArray(R.array.tv_photo_list_data)
    }

    private fun addData() {
        for (position in tvTitleData.indices) {
            val shows = Shows(
                tvTitleData[position],
                tvDateData[position],
                tvDescData[position],
                tvRatingData[position],
                tvPhotoData.getResourceId(position, -1)
            )
            tv.add(shows)
        }
    }

    private fun initRecyclerView(){
        rv_tv_list.layoutManager = LinearLayoutManager(context)
        val listHeroAdapter = TvViewHolderAdapter(tv)
        rv_tv_list.adapter = listHeroAdapter
    }

}
