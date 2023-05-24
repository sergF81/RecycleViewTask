package com.example.recycleviewtask.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recycleviewtask.data.DataSource
import com.example.recycleviewtask.data.ItemFlower
import com.example.recycleviewtask.databinding.FragmentListBinding
import com.example.recycleviewtask.domain.usecase.AddFlowerCase
import com.example.recycleviewtask.domain.usecase.GetAllFlowersCase

private const val ERROR_MESSAGE = "Не могу добавить элемент, так как поле пустое"

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private var flowerName: String = ""

    // тут сам отрефакторишь, для закрепления
//    private val caseDelete = CaseDeleteItemList(DataSource)
    private val addFlowersCase = AddFlowerCase(DataSource())
    private val getAllFlowersCase = GetAllFlowersCase(DataSource())
    private var flowers = mutableListOf<ItemFlower>()
    var positionItem: Int = 0

    //я пока не стал заморачиваться с ид, пока для демонстрации пусть будет вот такой костыль
    private var itemId: Int = 17

    /**
     * В реальности мы не можем написать код вот так,
     *
     * RecycleViewItemAdapter(getAllFlowersCase.getAll(), object : RecycleViewItemAdapter.MyListener {
     *
     * Потому, что юзкейс может вернуть ошибку,
     * например отпал интернет, или бд упала.
     * По этому нам приходится создавать отдельный список, в который мы по возможности будем загружать данные
     */
    private val adapter = RecycleViewItemAdapter(flowers, object : RecycleViewItemAdapter.MyListener {
        override fun myClick(userArray: MutableList<ItemFlower>, position: Int) {
            positionItem = position
            binding.buttonDeleteItem.isEnabled = true
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater)
        loadFlowers()
        return binding.root
    }

    private fun loadFlowers() {
        flowers = getAllFlowersCase.getAll()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonDeleteItem.setOnClickListener() {
            println("Выбран $positionItem элемент")
            adapter.notifyItemRemoved(positionItem)
            adapter.notifyItemRangeChanged(0, adapter.itemCount)
//            caseDelete.deleteItemFlowers(positionItem)
            binding.buttonDeleteItem.isEnabled = false
        }

        binding.buttonAddItem.setOnClickListener {
            flowerName = binding.textInputItem.text.toString()

            addFlowersCase.addFlower(ItemFlower(itemId++, flowerName))
            adapter.notifyItemChanged(0, adapter.itemCount)
            binding.textInputItem.setText("")

            if (flowerName.isEmpty()) showToast(ERROR_MESSAGE)

            binding.buttonAddItem.isEnabled = false
        }

        binding.textInputItem.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                binding.buttonAddItem.isEnabled = true
            }
        })


        binding.listItemRecycle.layoutManager = GridLayoutManager(context, 1)
        binding.listItemRecycle.adapter = adapter
        adapter.notifyItemRangeChanged(0, adapter.itemCount)
    }

    private fun showToast(message: String) {
        Toast.makeText(
            requireContext(),
            message,
            Toast.LENGTH_SHORT
        ).show()
    }

    companion object {

        @JvmStatic
        fun newInstance() = ListFragment()
    }
}

