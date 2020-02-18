package id.jason.submission2.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.jason.submission2.R
import id.jason.submission2.model.ShowsDetail
import id.jason.submission2.BuildConfig
import id.jason.submission2.view.IFavouriteListener
import kotlinx.android.synthetic.main.item_favourite.view.*
import id.jason.submission2.view.ShowDetailActivity

class FavouriteViewHolderAdapter(
    private var mContext: Context,
    private var favoriteList: List<ShowsDetail>,
    private var favoriteListener: IFavouriteListener
) : RecyclerView.Adapter<FavouriteViewHolderAdapter.FavouriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_favourite, parent, false)
        return FavouriteViewHolder(view)
    }

    override fun getItemCount(): Int = favoriteList.size


    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        holder.setView(favoriteList[position])
    }

    inner class FavouriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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

                favourite_remove.setOnClickListener {
                    favoriteListener.onFavoriteDeleteClick(shows.id!!)
                    Toast.makeText(mContext, context.getString(R.string.favourite_remove),Toast.LENGTH_LONG).show()
                }
                itemView.setOnClickListener {
                    val intentToDetail = Intent(itemView.context, ShowDetailActivity::class.java)
                    intentToDetail.putExtra(ShowDetailActivity.EXTRA_SHOWS, shows)
                    itemView.context.startActivity(intentToDetail)
                }
            }
        }
    }
}