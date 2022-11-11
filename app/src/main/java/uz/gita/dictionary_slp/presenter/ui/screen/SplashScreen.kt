package uz.gita.dictionary_slp.presenter.ui.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.dictionary_slp.R
import uz.gita.dictionary_slp.presenter.viewmodel.SplashViewModel
import uz.gita.dictionary_slp.presenter.viewmodel.impl.SplashViewModelImpl

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashScreen : Fragment(R.layout.screen_splash) {
    private val viewModel: SplashViewModel by viewModels<SplashViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.openHostFragmentLiveData.observe(this, openHostFragmentObserver)
    }

    private val openHostFragmentObserver = Observer<Unit> {
        findNavController().navigate(SplashScreenDirections.actionSplashScreenToHostFragment())
    }

}