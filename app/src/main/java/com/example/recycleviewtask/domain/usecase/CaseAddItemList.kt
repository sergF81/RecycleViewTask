package com.example.recycleviewtask.domain.usecase

import android.widget.EditText
import com.example.recycleviewtask.data.ItemFlower

class CaseAddItemList(var itemsFlower: MutableList<ItemFlower>) {

    fun addItemFlowers(flowerName: String) {

        itemsFlower.add(itemsFlower.size, ItemFlower(4, flowerName))

    }


}

