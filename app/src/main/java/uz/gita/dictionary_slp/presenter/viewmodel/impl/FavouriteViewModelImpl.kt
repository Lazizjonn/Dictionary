package uz.gita.dictionary_slp.presenter.viewmodel.impl

import android.database.Cursor
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.dictionary_slp.domain.AppRepository
import uz.gita.dictionary_slp.presenter.viewmodel.FavouriteViewModel
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModelImpl @Inject constructor(
    private val repository: AppRepository
) : ViewModel(), FavouriteViewModel {
    override val dictionaryLiveData = MutableLiveData<Cursor>()
    override val dictionaryBySearchLiveData = MutableLiveData<Cursor>()

    override fun load() {
        dictionaryLiveData.value = repository.getAllWordsFavoriteScreen()
    }
    override fun search(st: String) {
        dictionaryBySearchLiveData.value = repository.getAllWordsFavoriteScreenByQuery(st)
    }
    override fun updateIsRemember(isRemember: Int, id:Int) {
        repository.updateIsRememberFavouriteScreen(isRemember, id)
        load()
    }
}