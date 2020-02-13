package id.jason.submission2.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.jason.submission2.R
import id.jason.submission2.adapter.TvViewHolderAdapter
import id.jason.submission2.model.ShowsDetail
import id.jason.submission2.viewModel.TvViewModel
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_tv_show.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class TvShowFragment : Fragment() {

    private lateinit var viewModel: TvViewModel
    private lateinit var adapter: TvViewHolderAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(TvViewModel::class.java)
        val language = if (Locale.getDefault().language == "in") "id" else Locale.getDefault().language
        viewModel.setData(language)
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
            pb_tv.visibility = View.VISIBLE
            rv_tv_list.visibility = View.GONE
        } else {
            pb_tv.visibility = View.GONE
            rv_tv_list.visibility = View.VISIBLE
        }
    }

    private fun initRecyclerView(){
        adapter = TvViewHolderAdapter()
        adapter.notifyDataSetChanged()
        rv_tv_list.layoutManager = LinearLayoutManager(context)
        rv_tv_list.adapter = adapter
    }
}
