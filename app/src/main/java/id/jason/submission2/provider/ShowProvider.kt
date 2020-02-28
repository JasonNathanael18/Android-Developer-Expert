package id.jason.submission2.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import id.jason.submission2.database.DatabaseContract.AUTHORITY
import id.jason.submission2.database.DatabaseContract.NoteColumns.Companion.TABLE_NAME
import id.jason.submission2.database.ShowDatabase

class ShowProvider : ContentProvider() {

    companion object {
        private const val NOTE = 1
        private const val NOTE_ID = 2
        private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH)
        private lateinit var showDatabase: ShowDatabase

        init {
            // content://com.dicoding.picodiploma.mynotesapp/note
            sUriMatcher.addURI(AUTHORITY, TABLE_NAME, NOTE)
            // content://com.dicoding.picodiploma.mynotesapp/note/id
            sUriMatcher.addURI(
                AUTHORITY,
                "$TABLE_NAME/#",
                NOTE_ID
            )
        }
    }

    override fun onCreate(): Boolean {
        showDatabase = ShowDatabase.getDatabase(context!!)
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        return null
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        return 0
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }
}
