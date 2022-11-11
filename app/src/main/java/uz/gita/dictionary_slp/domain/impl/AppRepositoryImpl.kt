package uz.gita.dictionary_slp.domain.impl

import android.database.Cursor
import uz.gita.dictionary_slp.data.local.AppDatabase
import uz.gita.dictionary_slp.domain.AppRepository
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val database: AppDatabase
) : AppRepository {
    override fun getAllWordsHomeScreen(): Cursor = database.getAllWordsHomeScreen()
    override fun getAllWordsHomeScreenByQuery(st: String): Cursor = database.getAllWordsHomeScreenByQuery(st)
    override fun updateIsRememberHomeScreen(isRemember: Int, id: Int) = database.updateIsRememberHomeScreen(isRemember, id)

    override fun getAllWordsFavoriteScreen(): Cursor = database.getAllWordsFavoriteScreen()
    override fun getAllWordsFavoriteScreenByQuery(st: String): Cursor= database.getAllWordsFavoriteScreenByQuery(st)
    override fun updateIsRememberFavouriteScreen(isRemember: Int, id: Int)= database.updateIsRememberFavouriteScreen(isRemember, id)
}