package com.rohitjakhar.mvvmtemplate.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    fun getData() {
        viewModelScope.launch(IO) {

        }
    }
}
