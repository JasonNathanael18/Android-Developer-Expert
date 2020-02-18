package id.jason.submission2.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import id.jason.submission2.R
import id.jason.submission2.helper.Constants
import id.jason.submission2.model.ShowsDetail
import kotlinx.android.synthetic.main.activity_show_detail.*
import id.jason.submission2.BuildConfig
import id.jason.submission2.viewModel.FavouriteViewModel

class ShowDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_SHOWS = "extra_show"
    }

    private lateinit var showDetail: ShowsDetail
    private var viewModel: FavouriteViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_detail)
        supportActionBar?.title = resources.getString(R.string.actionbar_detail_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        viewModel = ViewModelProvider(this).get(FavouriteViewModel::class.java)
        if (savedInstanceState == null) {
            showDetail = intent.getParcelableExtra(EXTRA_SHOWS) as ShowsDetail
            setData(showDetail)
        } else {
            showDetail = savedInstanceState.getParcelable(Constants.State.HOME_DETAIL_DATA)!!
            setData(showDetail)
        }
        setClickListener()

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setData(data: ShowsDetail){
        detail_title.text = if (data.showTitle.isNullOrEmpty()) data.showName else data.showTitle
        detail_date.text = if (data.showReleaseDate.isNullOrEmpty()) data.showFirstAirDate else data.showReleaseDate
        detail_rating.text = data.showVote.toString()
        detail_desc.text = data.showDesc
        Glide.with(this)
            .load(BuildConfig.BASE_IMAGE_URL+data.showPoster)
            .into(detail_image)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(Constants.State.HOME_DETAIL_DATA,showDetail)
        super.onSaveInstanceState(outState)
    }

    private fun setClickListener(){
        btn_favourite.setOnClickListener {
            val titleOrName = if (showDetail.showTitle.isNullOrEmpty()) showDetail.showName else showDetail.showTitle
            Toast.makeText(this, getString(R.string.add_to_favorite, titleOrName), Toast.LENGTH_LONG).show()
            viewModel?.insert(showDetail)
        }
    }
}
