package com.example.carrentalowner.ui.auth.login

import android.text.Editable
import android.text.TextWatcher
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.carrentalowner.data.local.ClientPreferences
import com.example.carrentalowner.databinding.FragmentLoginBinding
import com.example.carrentalowner.utils.base.BaseFragment
import com.example.carrentalowner.utils.extension.snack
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding,LoginViewModel>(
    FragmentLoginBinding::inflate
) {
    private var ownerId: String = ""
    private var password: String = ""
    override val viewModel by viewModels<LoginViewModel>()

    override fun onCreateFinished() {

    }

    override fun initListeners() {
        binding.etOwnerId.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                checkFields()
            }
        })

        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                checkFields()
            }
        })

        binding.btnLogin.setOnClickListener {
            login()
        }
    }

    override fun observeEvents() {
        with(viewModel){
            loginResponse?.observe(viewLifecycleOwner, Observer {
                if(it.status == "true"){
                    snack(requireView(),"Login Successful")
                    ClientPreferences(requireContext()).setOwnerId(ownerId)
                    ClientPreferences(requireContext()).setOwnerName(it.data?.get(0)?.ownerName.toString())
                    val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment(it.data!![0])
                    findNavController().navigate(action)
                } else if(it.status == "false"){
                    snack(requireView(),it.message.toString())
                }
            })
            isLoading.observe(viewLifecycleOwner, Observer {
                handleViewAction(it)
            })

            onError.observe(viewLifecycleOwner, Observer {
                snack(requireView(),it.toString())
            })
        }
    }

    private fun handleViewAction(isLoading: Boolean = false) {
        binding.progressBar.isVisible = isLoading
    }

    private fun checkFields() {
        if (!binding.etOwnerId.text.isNullOrEmpty() && !binding.etPassword.text.isNullOrEmpty()) {
            binding.btnLogin.isEnabled = true
            binding.btnLogin.alpha = 1F
        } else {
            binding.btnLogin.isEnabled = false
            binding.btnLogin.alpha = 0.2F
        }
    }

    private fun login() {
        ownerId = binding.etOwnerId.text.toString()
        password = binding.etPassword.text.toString()

        viewModel.login(ownerId, password)
    }

}