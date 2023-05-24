package com.example.recycleviewtask.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleviewtask.R
import com.example.recycleviewtask.data.ItemFlower
import com.example.recycleviewtask.databinding.ListRowItemBinding

class RecycleViewItemAdapter(
    private var dataSet: MutableList<ItemFlower>,
    private var myListener: MyListener
) : RecyclerView.Adapter<RecycleViewItemAdapter.ItemHolder>() {

    fun updateItems(list: MutableList<ItemFlower>) {
        dataSet = list
        notifyItemRangeInserted(0, list.size)
    }

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ListRowItemBinding.bind(itemView)
        fun bind(s: ItemFlower) {
            binding.textViewItemRow.text = s.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_row_item, parent, false)

        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(dataSet[position])
        holder.itemView.setOnClickListener {
            myListener.myClick(dataSet, position)

        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    interface MyListener {

        fun myClick(userArray: MutableList<ItemFlower>, position: Int)
    }
}