package com.iamshekhargh.bookslibrary.ui.addProductFragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.iamshekhargh.bookslibrary.R
import com.iamshekhargh.bookslibrary.databinding.FragmentAddProductBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

/**
 * Created by <<-- iamShekharGH -->>
 * on 07 July 2021, Wednesday
 * at 7:00 PM
 */
@AndroidEntryPoint
class FragmentAddProduct : Fragment(R.layout.fragment_add_product) {

    val viewModel: FragmentAddProductViewModel by viewModels()
    lateinit var binding: FragmentAddProductBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.setTitle("Add Product")

        binding = FragmentAddProductBinding.bind(view)

        binding.apply {
            fragmentAddpProductSave.setOnClickListener {
                viewModel.saveClicked(
                    fragmentAddpProductName.text.toString(),
                    fragmentAddpProductDescription.text.toString(),
                    fragmentAddpProductPrice.text.toString(),
                    fragmentAddpProductQuantity.text.toString()
                )
            }
        }

        listenForEvents()
    }

    private fun listenForEvents() {
        lifecycleScope.launchWhenStarted {
            viewModel.events.collect { event ->
                when (event) {
                    is AddProductEvents.ShowSnackbar -> {
                        showSnackbar(event.text)
                    }
                    AddProductEvents.PopTheFragment -> {
                        val direction =
                            FragmentAddProductDirections.actionFragmentAddProductToFragmentBooks()
                        findNavController().navigate(direction)
                    }
                }
            }
        }
    }

    private fun showSnackbar(text: String) {
        Snackbar.make(requireView(), text, Snackbar.LENGTH_SHORT).show()
    }
}