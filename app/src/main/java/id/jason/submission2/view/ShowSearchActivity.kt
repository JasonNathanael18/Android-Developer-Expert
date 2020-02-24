package id.jason.submission2.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.jason.submission2.R
import id.jason.submission2.adapter.SearchViewHolderAdapter
import id.jason.submission2.helper.Constants
import id.jason.submission2.model.ShowsDetail
import id.jason.submission2.viewModel.ShowSearchViewModel
import kotlinx.android.synthetic.main.activity_show_search.*
import java.util.Locale

class ShowSearchActivity : AppCompatActivity() {

    private var showSearch: String=""
    private var showCategory: Int = 0
    private var language: String=""
    private lateinit var adapter: SearchViewHolderAdapter
    private lateinit var viewModel: ShowSearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_search)
        supportActionBar?.elevation = 0f
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        viewModel = ViewModelProvider(this).get(ShowSearchViewModel::class.java)
        language = if (Locale.getDefault().language == "in") "id" else Locale.getDefault().language
        getBundle()
        initRecyclerView()
        performQueryData(showSearch,showCategory)

        viewModel.getDataSearchEvent().observe(this, Observer {
                t ->
            if (t?.results.isNullOrEmpty()) {
                showLoading(false)
                showEmpty(true)
            } else {
                showData(t?.results)
            }
        })
    }

    private fun showData(data: List<ShowsDetail>?) {

        if (data.isNullOrEmpty()) {
            showEmpty(true)
            showLoading(false)
            rv_search.visibility = View.GONE
        }
        else{
            showEmpty(false)
            adapter.setData(data as ArrayList<ShowsDetail>)
            showLoading(false)
        }
    }

    private fun showEmpty(state: Boolean) {
        if(state){
            empty_search.visibility = View.VISIBLE
            rv_search.visibility = View.GONE
        }
        else empty_search.visibility = View.GONE
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            pb_search.visibility = View.VISIBLE
            rv_search.visibility = View.GONE
        } else {
            pb_search.visibility = View.GONE
            rv_search.visibility = View.VISIBLE
        }
    }

    private fun performQueryData(showSearch: String, showCategory: Int) {
        when(showCategory){
            0 -> viewModel.setDataSearchMovie(showSearch,language)
            1 -> viewModel.setDataSearchTvShow(showSearch,language)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun initRecyclerView(){
        adapter = SearchViewHolderAdapter()
        adapter.notifyDataSetChanged()
        rv_search.layoutManager = LinearLayoutManager(this)
        rv_search.adapter = adapter
    }

    private fun getBundle() {
        showSearch = intent.getStringExtra(Constants.IntentBundle.SEARCH_QUERY) ?: ""
        showCategory = intent.getIntExtra(Constants.IntentBundle.SEARCH_CATEGORY,0)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val searchItem = menu.findItem(R.id.app_bar_search)
        val searchView = searchItem?.actionView as SearchView
        searchView.setQuery(showSearch,false)
        searchView.isIconifiedByDefault = true
        searchView.isIconified = false
        searchView.isFocusable = true
        searchView.clearFocus()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                showLoading(true)
                showEmpty(false)
                performQueryData(query,showCategory)
                searchView.clearFocus()
                return true
            }
        })
        return true
    }
}
