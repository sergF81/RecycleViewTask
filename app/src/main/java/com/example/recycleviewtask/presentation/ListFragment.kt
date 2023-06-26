package com.example.recycleviewtask.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recycleviewtask.data.DataSource
import com.example.recycleviewtask.data.ItemDifferentHolder
import com.example.recycleviewtask.data.ItemFlower
import com.example.recycleviewtask.databinding.FragmentListBinding
import com.example.recycleviewtask.domain.usecase.AddFlowerCase
import com.example.recycleviewtask.domain.usecase.DeleteItemFlowerCase
import com.example.recycleviewtask.domain.usecase.GetAllFlowersCase
import android.R

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.example.recycleviewtask.data.ChosenFlower
import com.example.recycleviewtask.domain.usecase.GetInfoChosenFlower


private const val ERROR_MESSAGE = "Не могу добавить элемент, так как поле пустое"


class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private var flowerName: String = ""
    private lateinit var flower: ItemFlower
    private val deleteItemFlowerCase = DeleteItemFlowerCase(DataSource())
    private val addFlowersCase = AddFlowerCase(DataSource())
    private val getAllFlowersCase = GetAllFlowersCase(DataSource())
    private val getInfoChosenFlower = GetInfoChosenFlower(DataSource())
    private var flowers = mutableListOf<ItemFlower>()
    var positionItem: Int = 0

    /**
     * В реальности мы не можем написать код вот так,
     *
     * RecycleViewItemAdapter(getAllFlowersCase.getAll(), object : RecycleViewItemAdapter.MyListener {
     *
     * Потому, что юзкейс может вернуть ошибку,
     * например отпал интернет, или бд упала.
     * По этому нам приходится создавать отдельный список, в который мы по возможности будем загружать данные
     */
    private val adapter =
        RecycleViewItemAdapter(flowers, object : RecycleViewItemAdapter.MyListener {
            override fun myClick(flowerArray: MutableList<ItemFlower>, position: Int) {
                positionItem = position
                flower = flowerArray[positionItem]
                val imageOneFlower = getInfoChosenFlower.getImageFlower(flower)
                val descriptionOneFlower = getInfoChosenFlower.getDescriptionFlower(flower)
                val chosenFlower = ChosenFlower().apply{
                       sourceImageFlower = imageOneFlower
                    descriptionFlower = descriptionOneFlower
                }

                if (imageOneFlower.equals(null)) {
                    binding.buttonDeleteItem.isEnabled = true

                } else {
                    //создание action для передачи параметров во фрагмент InfoFlowerFragment
//                    val action =
//                        ListFragmentDirections.actionListFragmentToInfoFlowerFragment(imageOneFlower.toString(), descriptionOneFlower.toString())

                    val action = ListFragmentDirections.actionListFragmentToInfoFlowerFragment(chosenFlower)

                    // передача самого action через navController
                    findNavController().navigate(action)



// создание nav-контроллера с дальнейшим открытием фрагмента infoFlowerFragment
//                    val controller = findNavController()
//                    controller.navigate(com.example.recycleviewtask.R.id.infoFlowerFragment)
                }
            }
        })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater)
        return binding.root
    }

    private fun loadFlowers() {
        flowers = getAllFlowersCase.getAll()
        adapter.updateItems(flowers)
        adapter.notifyItemRangeInserted(0, flowers.size)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonDeleteItem.setOnClickListener {
            adapter.notifyItemRemoved(positionItem)
            deleteItemFlowerCase.deleteItemFlowers(flower)

            for (i in flower.id..flowers.size) {
                flowers[i - 1].id = i

            }
            adapter.notifyItemRangeChanged(positionItem, flowers.size)
            binding.buttonDeleteItem.isEnabled = false

        }

        binding.buttonAddItem.setOnClickListener {

            flowerName = binding.textInputItem.text.toString()
            if (flowerName.isEmpty()) showToast(ERROR_MESSAGE) else {
                addFlowersCase.addFlower(
                    ItemFlower(
                        flowers.size + 1,
                        flowerName,
                        null,
                        null,
                        ItemDifferentHolder.TYPE_TEXT
                    )
                )
                adapter.notifyItemChanged(0, adapter.itemCount)
                binding.textInputItem.setText("")
                binding.buttonAddItem.isEnabled = false
            }

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

        binding.listItemRecycle.layoutManager = LinearLayoutManager(requireContext())
        binding.listItemRecycle.adapter = adapter
        adapter.notifyItemRangeChanged(0, adapter.itemCount)
        loadFlowers()
    }

    private fun showToast(message: String) {
        Toast.makeText(
            requireActivity(), message, Toast.LENGTH_SHORT
        ).show()
    }
}

