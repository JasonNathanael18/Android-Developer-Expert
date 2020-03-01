package com.jason.favouriteapp.view

import android.database.Cursor
import android.os.Binder.clearCallingIdentity
import android.os.Binder.restoreCallingIdentity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jason.favouriteapp.R
import com.jason.favouriteapp.ShowsDetail
import com.jason.favouriteapp.adapter.FavouriteMovieViewHolderAdapter
import id.jason.submission2.database.DatabaseContract.ShowColumns.Companion.CONTENT_URI
import id.jason.submission2.database.DatabaseContract.ShowColumns.Companion.ID
import id.jason.submission2.database.DatabaseContract.ShowColumns.Companion.SHOW_DESC
import id.jason.submission2.database.DatabaseContract.ShowColumns.Companion.SHOW_POSTER
import id.jason.submission2.database.DatabaseContract.ShowColumns.Companion.SHOW_RELEASE_DATE
import id.jason.submission2.database.DatabaseContract.ShowColumns.Companion.SHOW_TITLE
import id.jason.submission2.database.DatabaseContract.ShowColumns.Companion.SHOW_VOTE
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var favouriteList: MutableList<ShowsDetail> = arrayListOf()
    private var cursor: Cursor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Favourite List"

        initCursor()
        initData()
        initRecyler(favouriteList)
    }

    private fun initRecyler(favoriteList: List<ShowsDetail>) {
        val adapter = FavouriteMovieViewHolderAdapter(favoriteList)
        rv_favourite.setHasFixedSize(true)
        rv_favourite.layoutManager = LinearLayoutManager(this)
        rv_favourite.adapter = adapter
    }

    private fun initCursor() {
        if (cursor != null) {
            cursor!!.close()
        }
        val tokenId = clearCallingIdentity()
        val projection = arrayOf("showTitle", "showDesc", "showPoster", "showReleaseDate")
        cursor = this.contentResolver.query(CONTENT_URI, projection, null, null, null)
        restoreCallingIdentity(tokenId)
    }

    private fun initData() {
        for (i in 0 until (cursor?.count!!)) {
            cursor?.moveToPosition(i)

            val id = cursor?.getString(cursor!!.getColumnIndex(ID))?.toInt()
            val title = cursor?.getString(cursor!!.getColumnIndex(SHOW_TITLE))
            val posterPath = cursor?.getString(cursor!!.getColumnIndex(SHOW_POSTER))
            val date = cursor?.getString(cursor!!.getColumnIndex(SHOW_RELEASE_DATE))
            val summary = cursor?.getString(cursor!!.getColumnIndex(SHOW_DESC))
            val rating = cursor?.getString(cursor!!.getColumnIndex(SHOW_VOTE))?.toFloat()

            favouriteList.add(ShowsDetail(id,title,summary,rating,posterPath,date))
        }
    }
}
