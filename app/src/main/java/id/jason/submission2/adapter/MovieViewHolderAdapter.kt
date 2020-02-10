package id.jason.submission2.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.jason.submission2.R
import id.jason.submission2.model.Shows
import id.jason.submission2.view.ShowDetailActivity
import kotlinx.android.synthetic.main.item_list.view.*

class MovieViewHolderAdapter(private val listShows: ArrayList<Shows>) :
    RecyclerView.Adapter<MovieViewHolderAdapter.MoviewViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return MoviewViewHolder(view)
    }

    override fun getItemCount(): Int = listShows.size


    override fun onBindViewHolder(holder: MoviewViewHolder, position: Int) {
        holder.setView(listShows[position])
    }

    inner class MoviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setView(shows: Shows) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(shows.showPhoto)
                    .into(list_image)
                list_title.text = shows.showTitle
                list_date.text = shows.showDate
                list_rating.text = shows.showRating
                list_summary.text = shows.showDesc

                itemView.setOnClickListener {
                    val movieSend = Shows(
                        shows.showTitle,
                        shows.showDate,
                        shows.showDesc,
                        shows.showRating,
                        shows.showPhoto
                    )
                    val intentToDetail = Intent(itemView.context, ShowDetailActivity::class.java)
                    intentToDetail.putExtra(ShowDetailActivity.EXTRA_SHOWS, movieSend)
                    itemView.context.startActivity(intentToDetail)
                }
            }
        }
    }
}