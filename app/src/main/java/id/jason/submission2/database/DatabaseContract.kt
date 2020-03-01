package id.jason.submission2.database

import android.net.Uri
import android.provider.BaseColumns

object DatabaseContract {

    const val AUTHORITY = "id.jason.submission2"
    const val SCHEME = "content"

    class NoteColumns : BaseColumns {

        companion object {
            const val TABLE_NAME = "show_table"
            const val ID = "id"
            const val SHOW_TITLE = "showTitle"
            const val SHOW_NAME = "showName"
            const val SHOW_DESC = "showDesc"
            const val SHOW_VOTE = "showVote"
            const val SHOW_POSTER = "showPoster"
            const val SHOW_RELEASE_DATE = "showReleaseDate"
            const val SHOW_FIRST_AIR_DATE = "showFirstAirDate"

            val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build()
        }

    }
}