package id.jason.submission2.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.jason.submission2.R
import id.jason.submission2.model.ShowsDetail
import id.jason.submission2.BuildConfig
import kotlinx.android.synthetic.main.item_list.view.*
import id.jason.submission2.view.ShowDetailActivity

class SearchViewHolderAdapter: RecyclerView.Adapter<SearchViewHolderAdapter.SearchViewHolder>() {

    private val mData = ArrayList<ShowsDetail>()

    fun setData(items: ArrayList<ShowsDetail>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return SearchViewHolder(view)
    }

    override fun getItemCount(): Int = mData.size


    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.setView(mData[position])
    }

    inner class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setView(shows: ShowsDetail) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(BuildConfig.BASE_IMAGE_URL + shows.showPoster)
                    .into(list_image)
                list_title.text =
                    if (shows.showTitle.isNullOrEmpty()) shows.showName else shows.showTitle
                list_date.text =
                    if (shows.showReleaseDate.isNullOrEmpty()) shows.showFirstAirDate else shows.showReleaseDate
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
}