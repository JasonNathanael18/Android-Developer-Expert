package id.jason.submission2.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetManager.ACTION_APPWIDGET_UPDATE
import android.appwidget.AppWidgetManager.getInstance
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.Toast
import androidx.core.net.toUri
import id.jason.submission2.R
import id.jason.submission2.service.StackWidgetService

/**
 * Implementation of App Widget functionality.
 */
class FavouriteAppWidget : AppWidgetProvider() {

    companion object {
        private const val TOAST_ACTION = "com.dicoding.picodiploma.TOAST_ACTION"
        const val EXTRA_ITEM = "com.dicoding.picodiploma.EXTRA_ITEM"
        //pindahkan fungsi ini ke companion object, karena kita akan memanggil fungsi ini dari luar kelas
        private fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
            val intent = Intent(context, StackWidgetService::class.java)
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
            intent.data = intent.toUri(Intent.URI_INTENT_SCHEME).toUri()

            val views = RemoteViews(context.packageName, R.layout.favourite_app_widget)
            views.setRemoteAdapter(R.id.stack_view, intent)
            views.setEmptyView(R.id.stack_view, R.id.empty_view)

            val toastIntent = Intent(context, FavouriteAppWidget::class.java)
            toastIntent.action = TOAST_ACTION
            toastIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
            intent.data = intent.toUri(Intent.URI_INTENT_SCHEME).toUri()

            val toastPendingIntent = PendingIntent.getBroadcast(context, 0, toastIntent, PendingIntent.FLAG_UPDATE_CURRENT)
            views.setPendingIntentTemplate(R.id.stack_view, toastPendingIntent)
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != null) {
            if (intent.action == TOAST_ACTION) {
                val viewIndex = intent.getIntExtra(EXTRA_ITEM, 0)
                Toast.makeText(context, "Touched view $viewIndex", Toast.LENGTH_SHORT).show()
            }
            else if(intent.action == ACTION_APPWIDGET_UPDATE){
                //onUpdate(context,intent.get,intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS))
                println("tes")
                val appWidgetManager: AppWidgetManager = getInstance(context)
                val thisWidget: ComponentName = ComponentName(context,FavouriteAppWidget::class.java)
                val appWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget)
                appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds,R.id.stack_view)
            }
        }
        super.onReceive(context, intent)
    }
}