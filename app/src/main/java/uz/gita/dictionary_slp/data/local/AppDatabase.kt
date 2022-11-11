package uz.gita.dictionary_slp.data.local

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDatabase @Inject constructor(@ApplicationContext context: Context) : DBHelper(context, "new.db", 1) {

    fun getAllWordsHomeScreen(): Cursor {
        val query = "SELECT * from mydictionary"
        return database().rawQuery(query, null)
    }
    fun getAllWordsHomeScreenByQuery(word: String): Cursor {
        val query = "SELECT * FROM mydictionary WHERE mydictionary.word LIKE '$word%'"
        return database().rawQuery(query, null)
    }
    fun updateIsRememberHomeScreen(isRemember: Int, id: Int) {
        val database = this.writableDatabase
        val cv = ContentValues()
        cv.put("isRemember", isRemember)
        database.update("mydictionary", cv, "id = $id", null)
    }

    fun getAllWordsFavoriteScreen(): Cursor {
        val query = "SELECT * from mydictionary WHERE mydictionary.isRemember = 1"
        return database().rawQuery(query, null)
    }
    fun getAllWordsFavoriteScreenByQuery(word: String): Cursor {
        val query = "SELECT * FROM mydictionary WHERE mydictionary.word LIKE '$word%' AND mydictionary.isRemember = 1"
        return database().rawQuery(query, null)
    }
    fun updateIsRememberFavouriteScreen(isRemember: Int, id: Int) {
        val database = this.writableDatabase
        val cv = ContentValues()
        cv.put("isRemember", isRemember)
        database.update("mydictionary", cv, "id = $id", null)
    }
}