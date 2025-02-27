package com.example.roomproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.roomproject.databinding.ActivityMainBinding
import com.example.roomproject.databinding.ListItemBinding
import com.example.roomproject.db.Subscriber

class MyRecyclerViewAdapter(private val subscriberList:List<Subscriber>,
                            private val clickListner:(Subscriber)->Unit):RecyclerView.Adapter<MyViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ListItemBinding= DataBindingUtil.inflate(layoutInflater,R.layout.list_item,parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return subscriberList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(subscriberList[position],clickListner)
    }


}

class MyViewHolder(val binding: ListItemBinding):RecyclerView.ViewHolder(binding.root)
{
      fun bind(subscriber: Subscriber,clickListner:(Subscriber)->Unit)
      {
          binding.nameTextView.text = subscriber.name
          binding.emailTextView.text = subscriber.email
          binding.listItemLayout.setOnClickListener {
              clickListner(subscriber)
          }
      }
}