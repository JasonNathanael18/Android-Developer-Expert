package id.jason.submission2.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.jason.submission2.R
import id.jason.submission2.adapter.FavouriteTabPagingAdapter
import kotlinx.android.synthetic.main.activity_favourite_list.*

class FavouriteListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourite_list)

        supportActionBar?.title = resources.getString(R.string.actionbar_favourite_title)

        val eventsPagerAdapter = FavouriteTabPagingAdapter(this, supportFragmentManager)
        vp_favourite.adapter = eventsPagerAdapter
        tab_favourite.setupWithViewPager(vp_favourite)
        supportActionBar?.elevation = 0f
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
