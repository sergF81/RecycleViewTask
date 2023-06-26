package com.example.recycleviewtask.presentation


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleviewtask.R
import com.example.recycleviewtask.data.ItemDifferentHolder
import com.example.recycleviewtask.data.ItemFlower
import com.example.recycleviewtask.databinding.RowTypeButtonBinding
import com.example.recycleviewtask.databinding.RowTypeImageBinding
import com.example.recycleviewtask.databinding.RowTypeTextBinding
import com.example.recycleviewtask.presentation.Const.HASBUTTON
import com.example.recycleviewtask.presentation.Const.HASIMAGE
import com.example.recycleviewtask.presentation.Const.HASTEXT
import com.squareup.picasso.Picasso
import kotlinx.coroutines.withContext


class RecycleViewItemAdapter(
    private var dataSet: MutableList<ItemFlower>,
    private var myListener: MyListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun updateItems(list: MutableList<ItemFlower>) {
        dataSet = list
        notifyItemRangeInserted(0, list.size)
    }

    class ItemHolderWithoutImage(itemView: RowTypeTextBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        private val binding = RowTypeTextBinding.bind(itemView.root)
        fun bind(s: ItemFlower) {
            binding.textIdView.text = s.id.toString()
            binding.textView.text = s.name
        }
    }


    class ItemHolderWithImage(itemView: RowTypeImageBinding) :
        RecyclerView.ViewHolder(itemView.root) {

        private val binding = RowTypeImageBinding.bind(itemView.root)
        fun bind(s: ItemFlower) {
            binding.textIdView2.text = s.id.toString()
            Picasso.with(binding.imageView.context)
                .load(s.resourceImageFlower)
                .into(binding.imageView);
        }
    }

    class ItemHolderWithButton(itemView: RowTypeButtonBinding) :
        RecyclerView.ViewHolder(itemView.root) {

        private val binding = RowTypeButtonBinding.bind(itemView.root)
        fun bind(s: ItemFlower) {
            binding.textIdView3.text = s.id.toString()
            binding.buttonGo.isEnabled
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (dataSet[position].itemDifferentHolder == ItemDifferentHolder.TYPE_IMAGE) {
            HASIMAGE
        } else {
            if (dataSet[position].itemDifferentHolder == ItemDifferentHolder.TYPE_TEXT) HASTEXT else HASBUTTON
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == HASIMAGE) {
            val view =
                RowTypeImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ItemHolderWithImage(view)
        } else {
            if (viewType == HASTEXT) {
                val view =
                    RowTypeTextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ItemHolderWithoutImage(view)
            } else {
                val view =
                    RowTypeButtonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ItemHolderWithButton(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == HASIMAGE) {
            (holder as ItemHolderWithImage).bind(dataSet[position])
            holder.itemView.setOnClickListener {
                myListener.myClick(dataSet, position)
            }

        } else {
            if (getItemViewType(position) == HASTEXT) {
                (holder as ItemHolderWithoutImage).bind(dataSet[position])
                holder.itemView.setOnClickListener {
                    myListener.myClick(dataSet, position)
                }

            } else {
                (holder as ItemHolderWithButton).bind(dataSet[position])
                holder.itemView.setOnClickListener {
                    myListener.myClick(dataSet, position)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    interface MyListener {
        fun myClick(userArray: MutableList<ItemFlower>, position: Int)
    }

}

private object Const {
    const val HASIMAGE = 0
    const val HASTEXT = 1
    const val HASBUTTON = 2
}

