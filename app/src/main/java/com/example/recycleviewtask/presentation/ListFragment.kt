package com.example.recycleviewtask.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recycleviewtask.data.ItemFlower
import com.example.recycleviewtask.data.ItemsFlower
import com.example.recycleviewtask.databinding.FragmentListBinding
import com.example.recycleviewtask.domain.usecase.CaseAddItemList
import com.example.recycleviewtask.domain.usecase.CaseDeleteItemList


class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    var itemFlower = ItemsFlower().flowerList()
    var positionItem: Int = 0
    private var flowerName: String = ""
    private val caseDelete = CaseDeleteItemList(itemFlower)
    private val caseAdd = CaseAddItemList(itemFlower)

    private val adapter =
        RecycleViewItemAdapter(itemFlower, object : RecycleViewItemAdapter.MyListener {
            override fun myClick(userArray: MutableList<ItemFlower>, position: Int) {
                positionItem = position
                binding.buttonDeleteItem.isEnabled = true
            }
        })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonDeleteItem.setOnClickListener() {
            println("Выбран $positionItem элемент")
            adapter.notifyItemRemoved(positionItem)
            adapter.notifyItemRangeChanged(0, adapter.itemCount)
            caseDelete.deleteItemFlowers(positionItem)
            binding.buttonDeleteItem.isEnabled = false
        }

        binding.buttonAddItem.setOnClickListener() {
            flowerName = binding.textInputItem.text.toString()
            caseAdd.addItemFlowers(flowerName)
            adapter.notifyItemChanged(0, adapter.itemCount)
            binding.textInputItem.setText("")
            if (flowerName.equals("")) {
                Toast.makeText(
                    this.context,
                    "Не могу добавиль элемент, так как поле пустое",
                    Toast.LENGTH_SHORT
                ).show()
            }
            binding.buttonAddItem.isEnabled = false

        }

        binding.textInputItem.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if (flowerName != null) {
                    binding.buttonAddItem.isEnabled = true
                } else binding.buttonAddItem.isEnabled = false
            }
        })


        binding.listItemRecycle.layoutManager = GridLayoutManager(context, 1)
        binding.listItemRecycle.adapter = adapter
        adapter.notifyItemRangeChanged(0, adapter.itemCount)


    }

    companion object {

        @JvmStatic
        fun newInstance() = ListFragment()

    }
}

