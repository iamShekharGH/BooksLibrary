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
import kotlinx.android.synthetic.main.fragment_add_product.*
import kotlinx.coroutines.flow.collect

/**
 * Created by <<-- iamShekharGH -->>
 * on 05 July 2021, Monday
 * at 10:48 PM
 */
class FragmentFirst : Fragment(R.layout.fragment_first) {

    val viewModel: FragmentFirstViewModel by viewModels()
    lateinit var binding: FragmentFirstBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFirstBinding.bind(view)

        binding.apply {
            fragmentfirstAddProduct.setOnClickListener {
                viewModel.addProductClicked()
//                openAddProductFragment()

            }

            fragmentfirstSyncProduct.setOnClickListener {

            }
        }



        setupEvents()

    }


    private fun setupEvents() {
        lifecycleScope.launchWhenStarted {
            viewModel.eventsAsFlow.collect { event ->
                when (event) {
                    is FirstFragEvents.ShowSnackBar -> {
                        showSnackbar(event.text)
                    }
                    FirstFragEvents.OpenAddProductFrag -> {
                        openAddProductFragment()
                    }
                }

            }
        }
    }

    private fun showSnackbar(text: String) {
        Snackbar.make(requireView(), text, Snackbar.LENGTH_SHORT).show()
    }

    private fun openAddProductFragment() {
        val direction = FragmentFirstDirections.actionFragmentFirstToFragmentAddProduct()
        findNavController().navigate(direction)
    }
}