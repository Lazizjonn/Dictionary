package uz.gita.dictionary_slp.presenter.ui.screen

import android.database.Cursor
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.dictionary_slp.R
import uz.gita.dictionary_slp.databinding.ScreenFavouriteBinding
import uz.gita.dictionary_slp.presenter.ui.adapter.DictionaryAdapter
import uz.gita.dictionary_slp.presenter.viewmodel.FavouriteViewModel
import uz.gita.dictionary_slp.presenter.viewmodel.impl.FavouriteViewModelImpl
import uz.gita.dictionary_slp.utils.hideKeyboard

@AndroidEntryPoint
class FavouriteScreen : Fragment(R.layout.screen_favourite) {
    private val viewModel: FavouriteViewModel by viewModels<FavouriteViewModelImpl>()
    private val binding by viewBinding(ScreenFavouriteBinding::bind)
    private val adapter by lazy { DictionaryAdapter() }
    private val handler by lazy { Handler(Looper.getMainLooper()) }
    private var querySt: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setAdapter()
        search()
        viewModel.load()
        viewModel.dictionaryLiveData.observe(viewLifecycleOwner, dictionaryObserver)
        viewModel.dictionaryBySearchLiveData.observe(viewLifecycleOwner, dictionaryBySearchObserver)
    }

    override fun onResume() {
        super.onResume()
        viewModel.load()
        if (querySt != null) {
            viewModel.search(querySt!!)
        }
    }

    private val dictionaryObserver = Observer<Cursor> {
        if (it.count == 0) {
            binding.dictionaryList.isVisible = false
            binding.animView.isVisible = true
        } else {
            binding.dictionaryList.isVisible = true
            binding.animView.isVisible = false
            adapter.cursor = it
            adapter.query = querySt
            adapter.notifyDataSetChanged()
        }
    }
    private val dictionaryBySearchObserver = Observer<Cursor> {
        if (it.count == 0) {
            binding.dictionaryList.isVisible = false
            binding.animView.isVisible = true
        } else {
            binding.dictionaryList.isVisible = true
            binding.animView.isVisible = false
            adapter.cursor = it
            adapter.query = querySt
            adapter.notifyDataSetChanged()
        }
    }

    private fun setAdapter() {
        binding.dictionaryList.adapter = adapter
        binding.dictionaryList.layoutManager = LinearLayoutManager(requireContext())
        adapter.setIsRememberListener { isRemember, id ->
            viewModel.updateIsRemember(isRemember, id)
            if (querySt != null) viewModel.search(querySt!!)
        }
    }

    private fun search() {
        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                handler.removeCallbacksAndMessages(null)
                querySt = text
                viewModel.search("$text%")
                return true
            }

            override fun onQueryTextChange(text: String?): Boolean {
                handler.removeCallbacksAndMessages(null)
                handler.postDelayed({
                    querySt = text
                    viewModel.search("$text%")
                }, 500)
                return true
            }
        })
        val cancelButton: ImageView = binding.searchView.findViewById(androidx.appcompat.R.id.search_close_btn)
        cancelButton.setOnClickListener {
            hideKeyboard()
            viewModel.load()
            adapter.query = null
            querySt = null
            binding.searchView.setQuery("", false)
        }
    }
}