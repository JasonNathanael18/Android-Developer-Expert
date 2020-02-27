package id.jason.submission2.service

import android.app.Application
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import androidx.core.os.bundleOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.jason.submission2.R
import id.jason.submission2.database.ShowDatabase
import id.jason.submission2.model.ShowsDetail
import id.jason.submission2.model.dao.FavouriteDao
import id.jason.submission2.model.dao.ShowDao
import id.jason.submission2.model.repository.FavouriteRepository
import id.jason.submission2.model.repository.ShowRepository
import id.jason.submission2.widget.FavouriteAppWidget

class StackRemoteViewsFactory(private val mContext: Context) :
    RemoteViewsService.RemoteViewsFactory, AndroidViewModel(mContext as Application) {
    private val mWidgetItems: List<ShowsDetail> = arrayListOf()
    private var tempList: List<ShowsDetail> = arrayListOf()
    private var favouriteRepository: FavouriteRepository? = null
    private var favouriteDao: FavouriteDao? = null

    override fun onCreate() {

    }

    override fun onDestroy() {
    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getItemId(p0: Int): Long = 0

    override fun onDataSetChanged() {
        favouriteDao = ShowDatabase.getDatabase(mContext).showFavourite()
        favouriteRepository = FavouriteRepository(favouriteDao!!)
        tempList = favouriteRepository!!.allFavouriteList
    }

    override fun hasStableIds(): Boolean = false

    override fun getViewAt(pos: Int): RemoteViews {
        val rv = RemoteViews(mContext.packageName, R.layout.widget_item)
        if(!tempList.isNullOrEmpty()){
            rv.setTextViewText(
                R.id.list_title,
                if (tempList[pos].showTitle.isNullOrEmpty()) tempList[pos].showName else tempList[pos].showTitle
            )
            rv.setTextViewText(R.id.list_rating, tempList[pos].showVote.toString())
            rv.setTextViewText(
                R.id.list_date,
                if (tempList[pos].showReleaseDate.isNullOrEmpty()) tempList[pos].showFirstAirDate else tempList[pos].showReleaseDate
            )

            val extras = bundleOf(
                FavouriteAppWidget.EXTRA_ITEM to pos
            )

            val fillInIntent = Intent()
            fillInIntent.putExtras(extras)
            rv.setOnClickFillInIntent(R.id.list_title, fillInIntent)
        }
        return rv
    }

    override fun getCount(): Int = tempList.size

    override fun getViewTypeCount(): Int = 1
}