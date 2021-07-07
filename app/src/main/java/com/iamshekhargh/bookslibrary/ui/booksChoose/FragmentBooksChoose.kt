package com.iamshekhargh.bookslibrary.ui.booksChoose

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.iamshekhargh.bookslibrary.R
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by <<-- iamShekharGH -->>
 * on 08 July 2021, Thursday
 * at 1:00 AM
 */
@AndroidEntryPoint
class FragmentBooksChoose : Fragment(R.layout.fragment_books_choose) {
    val viewModel: FragmentChooseViewModel by viewModels()
}