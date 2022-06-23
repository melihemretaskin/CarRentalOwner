package com.example.carrentalowner.ui.home.rentstate.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.carrentalowner.databinding.ItemHistoryBinding
import com.example.carrentalowner.model.rentResponse.Rent

/**
 *Created by Mert Melih Aytemur on 22.06.2022.
 */
class RentHistoryAdapter: RecyclerView.Adapter<RentHistoryAdapter.ViewHolder>() {
    private var rentHistoryList = mutableListOf<Rent>()

    class ViewHolder(private val itemBinding : ItemHistoryBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(rentHistory: Rent){
            itemBinding.rentResponse = rentHistory
            itemBinding.executePendingBindings()
        }
        companion object{
            fun from(parent: ViewGroup) : ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemHistoryBinding.inflate(layoutInflater,parent,false)

                return ViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(rentHistoryList[position])
    }

    override fun getItemCount(): Int = rentHistoryList.size

    fun updateList(_rentHistoryList : MutableList<Rent>){
        rentHistoryList = _rentHistoryList
        notifyDataSetChanged()
    }
}