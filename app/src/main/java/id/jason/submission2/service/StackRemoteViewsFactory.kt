package id.jason.submission2.service

import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import androidx.core.os.bundleOf
import id.jason.submission2.R
import id.jason.submission2.widget.FavouriteAppWidget

class StackRemoteViewsFactory(private val mContext: Context) : RemoteViewsService.RemoteViewsFactory {
    private val mWidgetItems = ArrayList<String>()
    override fun onCreate() {
    }
    override fun onDestroy() {
    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getItemId(p0: Int): Long = 0

    override fun onDataSetChanged() {
        mWidgetItems.add("coba1")
        mWidgetItems.add("coba2")
        mWidgetItems.add("coba3")
        mWidgetItems.add("coba4")
        mWidgetItems.add("coba5")
        mWidgetItems.add("coba6")
        mWidgetItems.add("coba7")
        mWidgetItems.add("coba8")
    }

    override fun hasStableIds(): Boolean = false

    override fun getViewAt(pos: Int): RemoteViews {
        val rv = RemoteViews(mContext.packageName, R.layout.widget_item)
        rv.setTextViewText(R.id.list_title,mWidgetItems[pos])

        val extras = bundleOf(
            FavouriteAppWidget.EXTRA_ITEM to pos
        )
        val fillInIntent = Intent()
        fillInIntent.putExtras(extras)
        rv.setOnClickFillInIntent(R.id.list_title, fillInIntent)
        return rv
    }

    override fun getCount(): Int = mWidgetItems.size

    override fun getViewTypeCount(): Int = 1


}