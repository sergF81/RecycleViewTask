package com.example.recycleviewtask.domain.usecase

import com.example.recycleviewtask.data.DataSource
import com.example.recycleviewtask.data.ItemFlower

/**
 * Юзкейс для добавления цветка
 *
 * @param dataSource - источник данных (бекенд, бд, файл и тд). То место куда мы хотим сохранить данные
 */
class AddFlowerCase(private val dataSource: DataSource) {

    /**
     * @return true - если удалось сохранить цветок, иначе false
     */
    fun addFlower(flower: ItemFlower): Boolean {

        return dataSource.addFlower(flower)
    }
}
