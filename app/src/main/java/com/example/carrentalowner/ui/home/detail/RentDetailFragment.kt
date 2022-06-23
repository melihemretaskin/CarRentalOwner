package com.example.carrentalowner.ui.home.detail


import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.carrentalowner.databinding.FragmentRentDetailBinding
import com.example.carrentalowner.utils.base.BaseFragment
import com.example.carrentalowner.utils.extension.snack
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RentDetailFragment : BaseFragment<FragmentRentDetailBinding,RentDetailViewModel>(
    FragmentRentDetailBinding::inflate
){
    override val viewModel by viewModels<RentDetailViewModel>()
    private val args by navArgs<RentDetailFragmentArgs>()

    override fun onCreateFinished() {

    }

    override fun initListeners() {
        binding.tvCarName.text = args.rentDetail.carName.toString()
        binding.tvUserEmail.text = args.rentDetail.email.toString()
        //binding.tvRentDate.text = args.rentDetail.rentDate.toString()

        binding.btnAccept.setOnClickListener {
            //acceptReservation()
        }
    }

    override fun observeEvents() {
        with(viewModel){
            rentAcceptResponse?.observe(viewLifecycleOwner, Observer {
                snack(requireView(),it.toString())
            })

            onError.observe(viewLifecycleOwner, Observer {
                snack(requireView(),it.toString())
            })
        }
    }

    private fun acceptReservation(){
        //val id = args.rentDetail.id.toString()
        //viewModel.acceptReservation(id,"")
    }
}