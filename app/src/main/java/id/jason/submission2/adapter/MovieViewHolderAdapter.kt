package id.jason.submission2.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.jason.submission2.BuildConfig
import id.jason.submission2.R
import id.jason.submission2.model.ShowsDetail
import id.jason.submission2.view.ShowDetailActivity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_list.*

class MovieViewHolderAdapter :
    RecyclerView.Adapter<MovieViewHolderAdapter.MovieViewHolder>() {
    private val mData = ArrayList<ShowsDetail>()

    fun setData(items: ArrayList<ShowsDetail>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = mData.size


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.setView(mData[position])
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        LayoutContainer {
        override val containerView: View?
            get() = itemView

        fun setView(shows: ShowsDetail) {
            Glide.with(itemView.context)
                .load(BuildConfig.BASE_IMAGE_URL + shows.showPoster)
                .into(list_image)
            list_title.text = shows.showTitle
            list_date.text = shows.showReleaseDate
            list_rating.text = shows.showVote.toString()
            list_summary.text = shows.showDesc

            itemView.setOnClickListener {
                val intentToDetail = Intent(itemView.context, ShowDetailActivity::class.java)
                intentToDetail.putExtra(ShowDetailActivity.EXTRA_SHOWS, shows)
                itemView.context.startActivity(intentToDetail)
            }
        }
    }
}