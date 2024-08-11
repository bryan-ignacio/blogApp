package com.androidgt.blogapp.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.androidgt.blogapp.R
import com.androidgt.blogapp.data.remote.auth.AuthDataSource
import com.androidgt.blogapp.databinding.FragmentHomeScreenBinding
import com.androidgt.blogapp.databinding.FragmentSetupProfileBinding
import com.androidgt.blogapp.domain.auth.AuthRepoImplements
import com.androidgt.blogapp.presentation.auth.AuthViewModel
import com.androidgt.blogapp.presentation.auth.AuthViewModelFactory


class SetupProfileFragment : Fragment(R.layout.fragment_setup_profile) {

    private lateinit var binding: FragmentSetupProfileBinding
    private val viewModel by viewModels<AuthViewModel> {
        AuthViewModelFactory(
            AuthRepoImplements(
                AuthDataSource()
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSetupProfileBinding.bind(view)
    }
}