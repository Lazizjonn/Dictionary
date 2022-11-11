package uz.gita.dictionary_slp.domain

import android.database.Cursor

interface AppRepository {
    fun getAllWordsHomeScreen(): Cursor
    fun getAllWordsHomeScreenByQuery(st: String): Cursor
    fun updateIsRememberHomeScreen(isRemember: Int, id: Int)

    fun getAllWordsFavoriteScreen(): Cursor
    fun getAllWordsFavoriteScreenByQuery(st: String): Cursor
    fun updateIsRememberFavouriteScreen(isRemember: Int, id: Int)
}