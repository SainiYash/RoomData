package com.example.roomproject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.roomproject.db.SubscriberRepository

class SubscriberViewModelFactory(private val repository: SubscriberRepository)
    :ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SubscriberViewmodel::class.java)) {
            return SubscriberViewmodel(repository) as T
        }
          throw IllegalArgumentException("Unknown View Model Class")
    }



}