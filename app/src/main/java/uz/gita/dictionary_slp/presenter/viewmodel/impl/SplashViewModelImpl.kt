package uz.gita.dictionary_slp.presenter.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.dictionary_slp.presenter.viewmodel.SplashViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor() : ViewModel(), SplashViewModel {
    override val openHostFragmentLiveData = MutableLiveData<Unit>()
    init {
        viewModelScope.launch {
            delay(200)
            openHostFragmentLiveData.value = Unit
        }
    }
}