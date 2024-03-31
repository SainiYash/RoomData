package com.example.roomproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.roomproject.databinding.ActivityMainBinding
import com.example.roomproject.db.Subscriber
import com.example.roomproject.db.SubscriberDatabase
import com.example.roomproject.db.SubscriberRepository

class MainActivity : AppCompatActivity() {
    private  lateinit var binding : ActivityMainBinding
    private lateinit var  viewmodel: SubscriberViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        val dao = SubscriberDatabase.getInstance(application).subscribeDao
        val repository = SubscriberRepository(dao)
        val factory = SubscriberViewModelFactory(repository)
        viewmodel = ViewModelProvider(this,factory).get(SubscriberViewmodel::class.java)
        binding.myViewModel = viewmodel
        binding.lifecycleOwner = this
        initRecyclerView()
    }

    private fun initRecyclerView()
    {
        binding.subscriberRecyclerview.layoutManager = LinearLayoutManager(this)
        displaySubscriberList()
    }




    private fun displaySubscriberList()
    {
        viewmodel.subscribers.observe(this, Observer {
            Log.i("My tag",it.toString())
            binding.subscriberRecyclerview.adapter = MyRecyclerViewAdapter(it,{selectedItem:Subscriber->ListItemClick(selectedItem)})
        })
    }

    private fun ListItemClick(subscriber:Subscriber)
    {
        Toast.makeText(this,"selected name $subscriber.name",Toast.LENGTH_LONG).show()

        // calling to created newly update or delete function
        viewmodel.initUpdateAndDelete(subscriber)

    }


}