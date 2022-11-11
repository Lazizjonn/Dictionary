package uz.gita.dictionary_slp.presenter.viewmodel

import android.database.Cursor
import androidx.lifecycle.LiveData

interface HomeViewModel {
    val dictionaryLiveData: LiveData<Cursor>
    val dictionaryBySearchLiveData : LiveData<Cursor>

    fun load ()
    fun search (st: String)
    fun updateIsRemember(isRemember : Int, id: Int)
}