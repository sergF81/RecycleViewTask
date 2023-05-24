package com.example.recycleviewtask.data

// модификатор internal - для того, что это класс нам не нужен во всей программе. А только в данном каталоге.
/**
 * object - потому, что мы хотим сохранять в памяти актуальное состояние списка
 */

internal object ItemsFlower {

    // сохраняем в памяти состояние списка
    private val flowers = mutableListOf(
        ItemFlower(
            id = 1,
            name = "Роза"

        ),
        ItemFlower(
            id = 2,
            name = "Лилия"
        ),
        ItemFlower(
            id = 3,
            name = "Тюльпан"
        ),
        ItemFlower(
            id = 4,
            name = "Гладиолус"
        ),
        ItemFlower(
            id = 5,
            name = "Гартензия 1"

        ),
        ItemFlower(
            id = 6,
            name = "Гартензия 2"

        ),
        ItemFlower(
            id = 7,
            name = "Гартензия 3"

        ),
        ItemFlower(
            id = 8,
            name = "Гартензия 4"

        ),
        ItemFlower(
            id = 9,
            name = "Гартензия 5"

        ),
        ItemFlower(
            id = 10,
            name = "Гартензия 6"

        ),
        ItemFlower(
            id = 11,
            name = "Гартензия 7"

        ),
        ItemFlower(
            id = 12,
            name = "Гартензия 8"

        ),
        ItemFlower(
            id = 13,
            name = "Гартензия 9"

        ),
        ItemFlower(
            id = 14,
            name = "Гартензия 10"

        ),
        ItemFlower(
            id = 15,
            name = "Гартензия 11"

        ),
        ItemFlower(
            id = 16,
            name = "Гартензия 12"

        )
    )

    fun flowerList() = flowers
}