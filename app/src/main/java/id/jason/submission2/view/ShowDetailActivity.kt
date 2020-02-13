package id.jason.submission2.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import id.jason.submission2.R
import id.jason.submission2.model.ShowsDetail
import kotlinx.android.synthetic.main.activity_show_detail.*

class ShowDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_SHOWS = "extra_show"
        const val baseURL = "https://image.tmdb.org/t/p/w185"
    }

    private lateinit var showDetail: ShowsDetail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_detail)
        supportActionBar?.title = resources.getString(R.string.actionbar_detail_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        showDetail = intent.getParcelableExtra(EXTRA_SHOWS) as ShowsDetail
        setData()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setData(){
        detail_title.text = if (showDetail.showTitle.isNullOrEmpty()) showDetail.showName else showDetail.showTitle
        detail_date.text = if (showDetail.showReleaseDate.isNullOrEmpty()) showDetail.showFirstAirDate else showDetail.showReleaseDate
        detail_rating.text = showDetail.showVote.toString()
        detail_desc.text = showDetail.showDesc ?: ""
        Glide.with(this)
            .load(baseURL+showDetail.showPoster)
            .into(detail_image)
    }
}
