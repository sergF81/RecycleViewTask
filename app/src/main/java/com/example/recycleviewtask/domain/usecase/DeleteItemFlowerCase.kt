package com.example.recycleviewtask.domain.usecase

import com.example.recycleviewtask.data.DataSource
import com.example.recycleviewtask.data.ItemFlower


class DeleteItemFlowerCase(private val dataSource: DataSource, ){


    fun deleteItemFlowers(flower: ItemFlower): Boolean{

        return dataSource.deleteFlower(flower)
    }

}