package com.example.roomproject

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomproject.db.Subscriber
import com.example.roomproject.db.SubscriberRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SubscriberViewmodel(private val repository: SubscriberRepository):ViewModel()
{

    val subscribers = repository.subscribers
    private var isUpdateOrDelete = false
    private lateinit var subscriberToUpdateOrDelete: Subscriber


    val inputName = MutableLiveData<String>()
    val inputEmail = MutableLiveData<String> ()

    val saveOrUpdateButton = MutableLiveData<String> ()
    val clearAllOrDeleteButton = MutableLiveData<String> ()

    init {
        saveOrUpdateButton.value ="Save"
        clearAllOrDeleteButton.value ="Clear All"
    }

    fun saveOrUpdate()
    {
        if(isUpdateOrDelete){
            subscriberToUpdateOrDelete.name = inputName.value!!
            subscriberToUpdateOrDelete.email =inputEmail.value!!
          update(subscriberToUpdateOrDelete)

        }else {


            val name = inputName.value!!
            val email = inputEmail.value!!
            insert(Subscriber(0, name, email))
            //to clear the input field after saving it
            inputName.value = ""
            inputEmail.value = ""
        }
    }


    fun clearAllorDelete()
    {
        if (isUpdateOrDelete){
            delete(subscriberToUpdateOrDelete)
        } else {
            clearAll()
        }
    }

    fun insert(subscriber: Subscriber){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(subscriber)
        }
    }

    fun update(subscriber: Subscriber){
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(subscriber)
        }
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(subscriber)

            // Since we are directly deal with the ui we need to change the thread using with context

            withContext(Dispatchers.Main){
                inputName.value = ""
                inputEmail.value = ""
                isUpdateOrDelete = false
                saveOrUpdateButton.value = "Save"
                clearAllOrDeleteButton.value = "ClearAll"
            }
        }
    }

    fun delete(subscriber: Subscriber){
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(subscriber)

            // Since we are directly deal with the ui we need to change the thread using with context

            withContext(Dispatchers.Main){
                inputName.value = ""
                inputEmail.value = ""
                isUpdateOrDelete = false
                saveOrUpdateButton.value = "Save"
                clearAllOrDeleteButton.value = "ClearAll"
            }
        }
    }

    fun clearAll() = viewModelScope.launch(Dispatchers.IO) {repository.clearAll()  }


    fun initUpdateAndDelete(subscriber:Subscriber)
    {
          inputName.value = subscriber.name
          inputEmail.value = subscriber.email
        isUpdateOrDelete = true
        subscriberToUpdateOrDelete = subscriber

      //  Now, I am going to write codes to change the displaying text values of the buttons.
        saveOrUpdateButton.value = "Update"
        clearAllOrDeleteButton.value = "Delete"
    }




}