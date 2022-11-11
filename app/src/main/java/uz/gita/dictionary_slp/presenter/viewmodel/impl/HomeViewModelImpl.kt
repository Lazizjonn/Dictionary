package uz.gita.dictionary_slp.presenter.viewmodel.impl

import android.database.Cursor
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.dictionary_slp.domain.AppRepository
import uz.gita.dictionary_slp.presenter.viewmodel.HomeViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    private val repository: AppRepository
) : ViewModel(), HomeViewModel {
    override val dictionaryLiveData = MutableLiveData<Cursor>()
    override val dictionaryBySearchLiveData = MutableLiveData<Cursor>()

    override fun load() {
        dictionaryLiveData.value = repository.getAllWordsHomeScreen()
    }
    override fun search(st: String) {
        dictionaryBySearchLiveData.value = repository.getAllWordsHomeScreenByQuery(st)
    }
    override fun updateIsRemember(isRemember: Int, id:Int) {
        repository.updateIsRememberHomeScreen(isRemember, id)
        load()
    }
}