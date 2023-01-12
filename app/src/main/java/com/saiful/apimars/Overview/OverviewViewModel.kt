package com.saiful.apimars.Overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saiful.apimars.network.MarsApi
import kotlinx.coroutines.launch

class OverviewViewModel : ViewModel() {
    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    init {
        getMarsPhotos()
    }

    private fun getMarsPhotos() {

        viewModelScope.launch {
            try {
                val listResult = MarsApi.retrofitService.getPhotos()
                _status.value = "Success: ${listResult.size} Mars photos retrieved"
                Log.d("TAG", listResult.size.toString())
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }
}