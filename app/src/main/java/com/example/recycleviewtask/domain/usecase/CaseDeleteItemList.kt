package com.example.recycleviewtask.domain.usecase

import com.example.recycleviewtask.data.ItemFlower

class CaseDeleteItemList(private var itemsFlower: MutableList<ItemFlower>){


    fun deleteItemFlowers(positionItem: Int){
        itemsFlower.removeAt(positionItem)
    }

}