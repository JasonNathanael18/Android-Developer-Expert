package id.jason.submission2.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import id.jason.submission2.R
import id.jason.submission2.helper.Constants
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
        if (savedInstanceState == null) {
            showDetail = intent.getParcelableExtra(EXTRA_SHOWS) as ShowsDetail
            setData(showDetail)
        } else {
            showDetail = savedInstanceState.getParcelable(Constants.State.HOME_DETAIL_DATA)!!
            setData(showDetail)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setData(data: ShowsDetail){
        detail_title.text = if (data.showTitle.isEmpty()) data.showName else data.showTitle
        detail_date.text = if (data.showReleaseDate.isEmpty()) data.showFirstAirDate else data.showReleaseDate
        detail_rating.text = data.showVote.toString()
        detail_desc.text = data.showDesc
        Glide.with(this)
            .load(baseURL+data.showPoster)
            .into(detail_image)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(Constants.State.HOME_DETAIL_DATA,showDetail)
        super.onSaveInstanceState(outState)
    }
}
