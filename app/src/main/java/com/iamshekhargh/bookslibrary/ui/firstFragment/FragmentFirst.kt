package com.iamshekhargh.bookslibrary.ui.firstFragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.iamshekhargh.bookslibrary.R
import com.iamshekhargh.bookslibrary.databinding.FragmentFirstBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

/**
 * Created by <<-- iamShekharGH -->>
 * on 05 July 2021, Monday
 * at 10:48 PM
 */
@AndroidEntryPoint
class FragmentFirst : Fragment(R.layout.fragment_first) {

    val viewModel: FragmentFirstViewModel by viewModels()
    lateinit var binding: FragmentFirstBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.setTitle("Books Library")

        binding = FragmentFirstBinding.bind(view)

        binding.apply {
            fragmentfirstAddProduct.setOnClickListener {
                viewModel.addProductClicked()
//                openAddProductFragment()
            }

            fragmentfirstBooks.setOnClickListener {
                viewModel.booksClicked()
            }
        }

        listenForEvents()
    }


    private fun listenForEvents() {
        lifecycleScope.launchWhenStarted {
            viewModel.eventsAsFlow.collect { event ->
                when (event) {
                    is FirstFragEvents.ShowSnackBar -> {
                        showSnackbar(event.text)
                    }
                    FirstFragEvents.OpenAddProductFrag -> {
                        openAddProductFragment()
                    }
                    FirstFragEvents.OpenBooksFragment -> {
                        openBooksFragment()
                    }
                }

            }
        }
    }

    private fun showSnackbar(text: String) {
        Snackbar.make(requireView(), text, Snackbar.LENGTH_SHORT).show()
    }

    private fun openBooksFragment() {
        val action = FragmentFirstDirections.actionFragmentFirstToFragmentViewAllResults()
        findNavController().navigate(action)
    }

    private fun openAddProductFragment() {
        val action = FragmentFirstDirections.actionFragmentFirstToFragmentBooksChoose()
        findNavController().navigate(action)
    }
}