package com.example.carrentalowner.ui.home.addnewcar

import android.content.Intent
import android.net.Uri
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.carrentalowner.data.local.ClientPreferences
import com.example.carrentalowner.databinding.FragmentAddCarBinding
import com.example.carrentalowner.utils.base.BaseFragment
import com.example.carrentalowner.utils.extension.snack
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCarFragment : BaseFragment<FragmentAddCarBinding,AddCarViewModel>(
    FragmentAddCarBinding::inflate
) {

    override val viewModel  by viewModels<AddCarViewModel>()
    private lateinit var imageUri : String
    private var adId : String = ""
    private val fakeImageUrl : String = "https://www.yenikoymotors.com/img/araclar/1160/119.jpg"

    companion object{
        const val SELECT_IMAGE_CODE = 1
    }

    override fun onCreateFinished() {
        viewModel.addPost(ClientPreferences(requireContext()).getOwnerId().toString(),"Advertisement")
    }

    override fun initListeners() {

        binding.etCarName.doAfterTextChanged {
            checkFields()
        }

        binding.etFuel.doAfterTextChanged {
            checkFields()
        }

        binding.etDailyFee.doAfterTextChanged {
            checkFields()
        }

        binding.etDeposit.doAfterTextChanged {
            checkFields()
        }

        binding.ivAddPhoto.setOnClickListener {
            chooseFromGallery()
        }

        binding.btnAddCar.setOnClickListener {
            addNewCar()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 1){
            val uri : Uri = data?.data!!
            imageUri = uri.toString()
            binding.ivAddPhoto.setImageURI(uri)
        }
    }

    private fun chooseFromGallery(){
        val intent : Intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent,"Title"),SELECT_IMAGE_CODE)
    }
    override fun observeEvents() {
        with(viewModel){

            addCarResponse?.observe(viewLifecycleOwner, Observer {
                snack(requireView(),it.message.toString())
            })

            isLoading.observe(viewLifecycleOwner, Observer {
                handleViewActions(it)
            })

            onError.observe(viewLifecycleOwner, Observer {
                snack(requireView(),it.toString())
            })

            addPostResponse?.observe(viewLifecycleOwner, Observer {
                it?.let {
                    adId = it.data?.get(0)?.adId.toString()
                }
            })

        }
    }

    private fun handleViewActions(isLoading: Boolean = false) {
        binding.progressBar.isVisible = isLoading
    }

    private fun addNewCar(){
        val ownerId = ClientPreferences(requireContext()).getOwnerId().toString()
        val ownerName = ClientPreferences(requireContext()).getOwnerName().toString()
        val carName = binding.etCarName.text.toString()
        val fuel = binding.etFuel.text.toString()
        val dailyFee = binding.etDailyFee.text.toString()
        val deposit = binding.etDeposit.text.toString()


        viewModel.addNewCar(ownerId,fakeImageUrl,adId,ownerName,carName,fuel,deposit,dailyFee)
    }


    private fun checkFields() {
        if (!binding.etCarName.text.isNullOrEmpty() && !binding.etFuel.text.isNullOrEmpty() && !binding.etDailyFee.text.isNullOrEmpty() &&
            !binding.etDeposit.text.isNullOrEmpty())  {
            binding.btnAddCar.isEnabled = true
            binding.btnAddCar.alpha = 1F
        } else {
            binding.btnAddCar.isEnabled = false
            binding.btnAddCar.alpha = 0.8F
        }
    }
}