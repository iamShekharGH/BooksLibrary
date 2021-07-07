package com.iamshekhargh.bookslibrary.ui.viewAllResults

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.iamshekhargh.bookslibrary.R
import com.iamshekhargh.bookslibrary.databinding.FragmentViewAllBooksBinding
import com.iamshekhargh.bookslibrary.databinding.FragmentViewAllResultsBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by <<-- iamShekharGH -->>
 * on 07 July 2021, Wednesday
 * at 10:26 PM
 */
@AndroidEntryPoint
class FragmentViewAllResults : Fragment(R.layout.fragment_view_all_results) {
    val viewModel: ViewAllResultsViewModel by viewModels()
    lateinit var binding: FragmentViewAllResultsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentViewAllResultsBinding.bind(view)
    }
}