package com.example.carrentalowner.ui.home.rentstate

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carrentalowner.data.local.ClientPreferences
import com.example.carrentalowner.databinding.FragmentRentHistoryBinding
import com.example.carrentalowner.model.rentResponse.Rent
import com.example.carrentalowner.ui.home.rentstate.adapter.RentHistoryAdapter
import com.example.carrentalowner.utils.base.BaseFragment
import com.example.carrentalowner.utils.extension.snack
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RentHistoryFragment : BaseFragment<FragmentRentHistoryBinding,RentHistoryViewModel>(
    FragmentRentHistoryBinding::inflate
) {
    override val viewModel by viewModels<RentHistoryViewModel>()
    private var rentHistoryList : MutableList<Rent> = mutableListOf()
    private var temporaryList: MutableList<Rent> = mutableListOf()

    override fun onCreateFinished() {
        viewModel.getRentHistory(ClientPreferences(requireContext()).getOwnerId().toString())
        setRecyclerViewAdapter()
        refresh()
    }

    override fun initListeners() {
        binding.swipeToRefresh.setOnRefreshListener {
            refresh()
        }
    }

    override fun observeEvents() {
        with(viewModel){
            rentHistoryListResponse.observe(viewLifecycleOwner, Observer {
                it?.let {
                    rentHistoryList.clear()
                    it.rent?.forEach { rent ->
                        rentHistoryList.add(rent)
                    }
                    temporaryList.clear()
                    temporaryList.addAll(rentHistoryList)
                    (binding.rvRentHistory.adapter as RentHistoryAdapter).updateList(temporaryList)
                }
            })
            onLoading.observe(viewLifecycleOwner, Observer {
                handleViewActions(it)
            })
            onError.observe(viewLifecycleOwner, Observer {
                snack(requireView(),it.toString())
            })
        }
    }

    private fun setRecyclerViewAdapter(){
        val mLayoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        binding.rvRentHistory.layoutManager = mLayoutManager
        binding.rvRentHistory.adapter = RentHistoryAdapter()
    }

    private fun handleViewActions(isLoading: Boolean = false) {
        binding.rvRentHistory.isVisible = !isLoading
        binding.progressBar.isVisible = isLoading
    }
    private fun refresh(){
        viewModel.getRentHistory(ClientPreferences(requireContext()).getOwnerId().toString())
        binding.swipeToRefresh.isRefreshing = false
    }

}