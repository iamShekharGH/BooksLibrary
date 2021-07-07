package com.iamshekhargh.bookslibrary.ui.viewAllBooks

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.iamshekhargh.bookslibrary.R
import com.iamshekhargh.bookslibrary.databinding.FragmentViewAllBooksBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

/**
 * Created by <<-- iamShekharGH -->>
 * on 08 July 2021, Thursday
 * at 12:30 AM
 */
@AndroidEntryPoint
class FragmentBooks : Fragment(R.layout.fragment_view_all_books) {
    val viewModel: FragmentBooksViewModel by viewModels()
    lateinit var binding: FragmentViewAllBooksBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentViewAllBooksBinding.bind(view)

        binding.apply {

        }

        listenToEvents()
    }

    private fun listenToEvents() {
        lifecycleScope.launchWhenStarted {
            viewModel.events.collect { event ->
                when (event) {
                    is FragmentEvents.ShowSnackbar -> {
                        showSnackbar(event.text)
                    }
                }
            }
        }
    }

    private fun showSnackbar(text: String) {
        Snackbar.make(requireView(), text, Snackbar.LENGTH_SHORT).show()
    }
}