package com.example.recycleviewtask.domain.usecase

import com.example.recycleviewtask.data.DataSource
import com.example.recycleviewtask.data.ItemFlower

/**
 * Юзкейс для получения всех цветов
 *
 * @param dataSource - источник данных (бекенд, бд, файл и тд). То место куда мы хотим сохранить данные
 */
class GetAllFlowersCase(private val dataSource: DataSource) {

    /**
     * @return true - если удалось сохранить цветок, иначе false
     *
     * p.s. тут мы намерено меняем возвращаемый тип с nullable, на не nullable
     * Потому, что нам удобнее работать с пустым списком, чем с null
     *
     * Например, на бекенде цветов нет (кончились на складе),
     * значит в ответе от сервера мы получим null вместе списка.
     *
     * Чтобы красиво отобразить эту ситуацию, нам придется добавить некрасивую обработку на слое отображения.
     * А можем обработать эту ситуацию на уровне бизнес логики приложения.
     * Если у нас есть список и он пуст - отобразить пустой экран (т.н. "empty state"), если список не пуст,
     * отображаем то, что получили с бека.
     */
    fun getAll(): MutableList<ItemFlower> {

        val data = dataSource.getAll()

        return if (data.isNullOrEmpty()) {
            mutableListOf()
        } else
            return data;
    }
}