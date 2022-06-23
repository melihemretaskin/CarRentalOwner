package com.example.carrentalowner.ui.home

import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carrentalowner.MainActivity
import com.example.carrentalowner.R
import com.example.carrentalowner.data.local.ClientPreferences
import com.example.carrentalowner.databinding.FragmentHomeBinding
import com.example.carrentalowner.model.rentResponse.ownerResponse.Car
import com.example.carrentalowner.ui.home.adapter.RentAdapter
import com.example.carrentalowner.utils.base.BaseFragment
import com.example.carrentalowner.utils.extension.OnItemClickListener
import com.example.carrentalowner.utils.extension.loadImageView
import com.example.carrentalowner.utils.extension.snack
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding,HomeViewModel>(
    FragmentHomeBinding::inflate
) {
    override val viewModel by viewModels<HomeViewModel>()
    private val args by navArgs<HomeFragmentArgs>()

    private var rentList : MutableList<Car> = mutableListOf()
    private var temporaryList: MutableList<Car> = mutableListOf()

    //Navigation Drawer
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var headerView: View
    private lateinit var headerEmail: TextView

    override fun onCreateFinished() {
        viewModel.getReservations(ClientPreferences(requireContext()).getOwnerId().toString())

        //snack(requireView(), "$ownerId,$ownerName")
        setRecyclerViewAdapter()
        setNavigationDrawer()
    }

    override fun initListeners() {
        binding.tvOwnerName.text = args.ownerData.ownerName.toString()

        binding.fabOpenNavDrawer.setOnClickListener {
            binding.drawerLayout.openDrawer(Gravity.LEFT)
        }

        binding.swipeToRefresh.setOnRefreshListener {
            refresh()
        }

        binding.ivPhoto.loadImageView(args.ownerData.avatar.toString())
        binding.tvOwnerName.text = ClientPreferences(requireContext()).getOwnerName().toString()
    }

    override fun observeEvents() {
        with(viewModel){
            rentResponse?.observe(viewLifecycleOwner, Observer {
                it?.let {
                    rentList.clear()
                    it.cars?.forEach { car ->
                        rentList.add(car)
                    }
                }
                temporaryList.clear()
                temporaryList.addAll(rentList)
                (binding.rvRents.adapter as RentAdapter).updateList(temporaryList)
            })

            isLoading.observe(viewLifecycleOwner, Observer {
                handleViewActions(it)
            })

            onError.observe(viewLifecycleOwner, Observer {
                snack(requireView(),it.toString())
            })
        }
    }

    private fun setNavigationDrawer(){
        drawerLayout = binding.drawerLayout
        navView = binding.navView
        headerView = navView.getHeaderView(0)
        headerEmail = headerView.findViewById(R.id.tvUserEmail)
        //headerEmail.text = ClientPreferences(requireContext()).getUserEmail().toString()

        toggle = ActionBarDrawerToggle(
            activity as MainActivity,
            drawerLayout,
            R.string.open,
            R.string.close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navView.inflateMenu(R.menu.nav_menu)

        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_add ->{
                    findNavController().navigate(R.id.action_homeFragment_to_addCarFragment)
                }
                R.id.nav_feedback -> {}
                R.id.nav_logout -> logout()
                R.id.nav_history -> findNavController().navigate(R.id.action_homeFragment_to_rentHistoryFragment)
            }
            true
        }
    }

    private fun logout(){
        ClientPreferences(requireContext()).clearSharedPref()
        findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
    }

    private fun setRecyclerViewAdapter(){
        val mLayoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        binding.rvRents.layoutManager = mLayoutManager

        val mAdapter = RentAdapter(object : OnItemClickListener{
            override fun onClick(car: Car) {
                //val action = HomeFragmentDirections.actionHomeFragmentToRentDetailFragment(car)
                //findNavController().navigate(action)
            }
        })
        binding.rvRents.adapter = mAdapter
    }

    private fun handleViewActions(isLoading: Boolean = false) {
        binding.rvRents.isVisible = !isLoading
        binding.progressBar.isVisible = isLoading
    }
    private fun refresh(){
        viewModel.getReservations(ClientPreferences(requireContext()).getOwnerId().toString())
        viewModel.fetchOwnerData(ClientPreferences(requireContext()).getOwnerId().toString())
        binding.swipeToRefresh.isRefreshing = false
    }
}