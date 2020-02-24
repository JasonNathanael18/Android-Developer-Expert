package id.jason.submission2.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import com.google.android.material.tabs.TabLayout
import id.jason.submission2.R
import id.jason.submission2.adapter.TabPagingAdapter
import id.jason.submission2.helper.Constants
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var tabPosition:Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = resources.getString(R.string.actionbar_title)

        val tabPagingAdapter = TabPagingAdapter(this, supportFragmentManager)
        vp_home.adapter = tabPagingAdapter
        tab_home.setupWithViewPager(vp_home)

        supportActionBar?.elevation = 0f

        tab_home.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {}

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabSelected(tab: TabLayout.Tab?) { tabPosition=tab?.position }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val searchItem = menu.findItem(R.id.app_bar_search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                goToSearch(query)
                searchView.clearFocus()
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_change_settings) {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }
        else if (item.itemId == R.id.go_to_favourite) {
            val mIntent = Intent(this,FavouriteListActivity::class.java)
            startActivity(mIntent)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun goToSearch(query: String?) {
        val intent = Intent(this, ShowSearchActivity::class.java)
        intent.putExtra(Constants.IntentBundle.SEARCH_QUERY, query)
        intent.putExtra(Constants.IntentBundle.SEARCH_CATEGORY,tabPosition)
        startActivity(intent)
    }

}
