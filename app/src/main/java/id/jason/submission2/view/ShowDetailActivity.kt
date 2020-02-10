package id.jason.submission2.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import id.jason.submission2.R
import id.jason.submission2.model.Shows
import kotlinx.android.synthetic.main.activity_show_detail.*

class ShowDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_SHOWS = "extra_show"
    }

    private lateinit var showDetail: Shows

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_detail)
        supportActionBar!!.title = resources.getString(R.string.actionbar_detail_title)
        showDetail = intent.getParcelableExtra(EXTRA_SHOWS) as Shows
        setData()
    }

    private fun setData(){
        detail_title.text = showDetail.showTitle
        detail_date.text = showDetail.showDate
        detail_rating.text = showDetail.showRating
        detail_desc.text = showDetail.showDesc
        Glide.with(this)
            .load(showDetail.showPhoto)
            .into(detail_image)
    }
}
