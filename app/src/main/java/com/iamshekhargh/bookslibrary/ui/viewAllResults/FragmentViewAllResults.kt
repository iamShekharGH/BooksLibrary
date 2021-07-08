package com.iamshekhargh.bookslibrary.ui.viewAllResults

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.iamshekhargh.bookslibrary.R
import com.iamshekhargh.bookslibrary.databinding.FragmentViewAllResultsBinding
import com.iamshekhargh.bookslibrary.network.module.res.Result
import com.iamshekhargh.bookslibrary.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

/**
 * Created by <<-- iamShekharGH -->>
 * on 07 July 2021, Wednesday
 * at 10:26 PM
 */
@AndroidEntryPoint
class FragmentViewAllResults : Fragment(R.layout.fragment_view_all_results) {
    val viewModel: ViewAllResultsViewModel by viewModels()
    lateinit var binding: FragmentViewAllResultsBinding
    lateinit var adapter: ViewAllResultsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.setTitle("Available Books")
        checkInternet()

        adapter = ViewAllResultsAdapter()
        binding = FragmentViewAllResultsBinding.bind(view)

        binding.apply {
            allResultsFragRv.adapter = adapter
        }



        listenForEvents()
        collectResult()
    }

    private fun collectResult() {

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.resultFlow.collect { response ->
                when (response) {
                    is Resource.Error -> {
                        showError(response.message)
                    }
                    is Resource.Loading -> {
                        showLoading()
                    }
                    is Resource.Success -> {
                        showResult(response.data)
                    }
                }
            }
        }
    }

    private fun showError(message: String?) {
        binding.apply {
            allResultsFragRv.isVisible = true
            allResultsError.isVisible = true
            allResultsError.text = message
            allResultsProgressbar.isVisible = false
        }
    }

    private fun showLoading() {
        binding.apply {
            allResultsFragRv.isVisible = true
            allResultsError.isVisible = false
            allResultsProgressbar.isVisible = true
            allResultsProgressbar.isIndeterminate = true
        }
    }

    private fun showResult(data: List<Result>?) {

        adapter.submitList(data)
        binding.apply {
            if (data.isNullOrEmpty()) {
                allResultsFragRv.isVisible = false
                allResultsError.isVisible = true
                allResultsError.text = "Sorry the List is empty."
                allResultsProgressbar.isVisible = false
            } else {
                allResultsFragRv.isVisible = true
                allResultsError.isVisible = false
                allResultsProgressbar.isVisible = false
            }
        }
    }

    private fun listenForEvents() {
        lifecycleScope.launchWhenStarted {
            viewModel.events.collect { event ->

                when (event) {
                    is Events.ShowSnackbar -> {
                        showSnackbar(event.text)
                    }
                    is Events.InternetConnectionToggle -> {
                        checkInternet()
                    }
                }
            }
        }
    }

    private fun checkInternet() {
        val cm = activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (cm != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)
                if (capabilities != null) {
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                        viewModel.setInternetStatus(true)

                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                        viewModel.setInternetStatus(true)
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                        viewModel.setInternetStatus(true)
                    }
                } else {
                    viewModel.setInternetStatus(false)
                }
            } else {
                viewModel.setInternetStatus(
                    cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
                )
            }
        }

    }

    private fun showSnackbar(text: String) {
        Snackbar.make(requireView(), text, Snackbar.LENGTH_SHORT).show()
    }
}