package com.rohitjakhar.mvvmtemplate.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rohitjakhar.mvvmtemplate.R
import com.rohitjakhar.mvvmtemplate.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding: FragmentHomeBinding by viewBinding()
    private val viewModel by viewModel<HomeViewModel>() // HomeViewModel needs to be declared in di/AppModule.kt

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
