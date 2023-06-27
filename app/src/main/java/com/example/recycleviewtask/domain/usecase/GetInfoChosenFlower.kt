package com.example.recycleviewtask.domain.usecase


import com.example.recycleviewtask.data.ItemFlower

class GetInfoChosenFlower{
    fun getImageFlower(flower: ItemFlower): String? {

        return flower.resourceImageFlower
    }
    fun getDescriptionFlower(flower: ItemFlower): String? {
        return flower.descriptionFlower
    }
}

