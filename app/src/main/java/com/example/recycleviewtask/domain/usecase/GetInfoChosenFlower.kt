package com.example.recycleviewtask.domain.usecase

import com.example.recycleviewtask.data.DataSource
import com.example.recycleviewtask.data.ItemFlower

class GetInfoChosenFlower (private val dataSource: DataSource){
    fun getImageFlower(flower: ItemFlower): String? {

        return flower.resourceImageFlower
    }
    fun getDescriptionFlower(flower: ItemFlower): String? {
        return flower.descriptionFlower
    }
}

