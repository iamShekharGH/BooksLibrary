package com.iamshekhargh.bookslibrary.ui.booksChoose

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.iamshekhargh.bookslibrary.R
import com.iamshekhargh.bookslibrary.databinding.FragmentBooksChooseBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

/**
 * Created by <<-- iamShekharGH -->>
 * on 08 July 2021, Thursday
 * at 1:00 AM
 */
@AndroidEntryPoint
class FragmentBooksChoose : Fragment(R.layout.fragment_books_choose) {
    val viewModel: FragmentChooseViewModel by viewModels()

    lateinit var binding: FragmentBooksChooseBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.setTitle("Please Choose.")

        binding = FragmentBooksChooseBinding.bind(view)

        binding.apply {
            fragmentBooksChooseAdd.setOnClickListener {
                viewModel.addProductClicked()
            }

            fragmentBooksChooseSync.setOnClickListener {
                viewModel.syncProductClicked()
            }
            fragmentBooksChooseSkip.setOnClickListener {
                viewModel.skipClicked()
            }
        }

        listenToEvents()
    }

    private fun listenToEvents() {
        lifecycleScope.launchWhenStarted {
            viewModel.events.collect { event ->
                when (event) {
                    is Events.ShowSnackbar -> {
                        showSnackbar(event.text)
                    }
                    Events.OpenAddProductsFragment -> {
                        val action =
                            FragmentBooksChooseDirections.actionFragmentBooksChooseToFragmentAddProduct()
                        findNavController().navigate(action)
                    }
                    Events.OpenProductListFragment -> {
                        val action =
                            FragmentBooksChooseDirections.actionFragmentBooksChooseToFragmentBooks()
                        findNavController().navigate(action)
                    }
                    is Events.ProgressBarShow -> {
                        progressBarShow(event.show)
                    }
                }
            }
        }
    }

    private fun showSnackbar(text: String) {
        Snackbar.make(requireView(), text, Snackbar.LENGTH_SHORT).show()
    }

    private fun progressBarShow(b: Boolean) {
        binding.apply {
            fragmentBooksChooseProgressbar.isVisible = b
        }
    }
}