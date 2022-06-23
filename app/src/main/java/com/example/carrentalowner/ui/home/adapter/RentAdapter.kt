package com.example.carrentalowner.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.carrentalowner.databinding.CarItemBinding
import com.example.carrentalowner.model.rentResponse.ownerResponse.Car
import com.example.carrentalowner.utils.extension.OnItemClickListener

/**
 *Created by Mert Melih Aytemur on 1.06.2022.
 */
class RentAdapter(
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<RentAdapter.ViewHolder>() {
    private var carsList = mutableListOf<Car>()

    class ViewHolder(private val itemBinding: CarItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(car: Car, listener : OnItemClickListener) {
            itemBinding.carsResponse = car
            itemBinding.onItemClickListener = listener
            itemBinding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CarItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(carsList[position],onItemClickListener)
    }

    override fun getItemCount(): Int = carsList.size

    fun updateList(_rentList: MutableList<Car>) {
        carsList = _rentList
        notifyDataSetChanged()
    }
}
