package com.apps.simplenewsfeed.ui.main

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apps.simplenewsfeed.data.models.Item
import com.apps.simplenewsfeed.data.source.repository.DefaultNewsFeedRepository
import com.apps.simplenewsfeed.util.Result
import kotlinx.coroutines.launch

class NewsFeedViewModel @ViewModelInject constructor(
    val repository: DefaultNewsFeedRepository
) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    var error = MutableLiveData<String>()
    val latestItems = MutableLiveData<List<Item>>()

    fun getItems(needsRefresh : Boolean) {
        Log.d(TAG, "Attempt to refresh data")
        isLoading.value = true
        viewModelScope.launch {
            when (val result = repository.getItems(needsRefresh)) {
                is Result.Success -> {
                    isLoading.value = false
                    if (result.data != null) {
                        latestItems.value = result.data
                        if(needsRefresh) {
                            repository.deleteItems()
                            repository.saveItems(result.data)
                        }

                        Log.d(TAG, "Refresh of the data is successful")
                        error.value = null
                    } else {
                        Log.d(TAG, "Refreshed data is null!")
                        error.value = "No data found"
                    }
                }
                is Result.Error -> {
                    Log.d(TAG, "Error during data refresh: ${result.exception}")
                    isLoading.value = false
                    error.value = result.exception.message
                }
                is Result.Loading -> isLoading.postValue(true)
            }
        }
    }

    companion object {
        var TAG = NewsFeedViewModel::class.java.simpleName
    }
}