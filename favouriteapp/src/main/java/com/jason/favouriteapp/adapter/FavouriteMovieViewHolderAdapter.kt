package com.jason.favouriteapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jason.favouriteapp.R
import com.jason.favouriteapp.ShowsDetail
import kotlinx.android.synthetic.main.item_favourite.view.*

class FavouriteMovieViewHolderAdapter(private var favoriteList: List<ShowsDetail>) :
    RecyclerView.Adapter<FavouriteMovieViewHolderAdapter.FavouriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_favourite, parent, false)
        return FavouriteViewHolder(view)
    }

    override fun getItemCount(): Int = favoriteList.size

    override fun onBindViewHolder(
        holder: FavouriteViewHolder,
        position: Int
    ) {
        holder.setView(favoriteList[position])
    }

    inner class FavouriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setView(shows: ShowsDetail) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w185" + shows.showPoster)
                    .into(list_image)
                list_title.text =
                    shows.showTitle
                list_date.text =
                    shows.showReleaseDate
                list_rating.text = shows.showVote.toString()
                list_summary.text = shows.showDesc
            }
        }
    }
}