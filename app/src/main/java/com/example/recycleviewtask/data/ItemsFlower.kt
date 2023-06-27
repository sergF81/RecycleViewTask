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
            name = "Роза",
            descriptionFlower = "Одна прекрасная роза среди навоза!",
            resourceImageFlower = "https://upload.wikimedia.org/wikipedia/commons/b/bf/Rosa_Red_Chateau01.jpg",
            ItemDifferentHolder.TYPE_IMAGE

        ),
        ItemFlower(
            id = 2,
            name = "Лилия",
            descriptionFlower = null,
            resourceImageFlower = null,
            ItemDifferentHolder.TYPE_BUTTON
        ),
        ItemFlower(
            id = 3,
            name = "Тюльпан",
            descriptionFlower = null,
            resourceImageFlower = null,
            ItemDifferentHolder.TYPE_TEXT
        ),
        ItemFlower(
            id = 4,
            name = "Гладиолус",
            descriptionFlower = null,
            resourceImageFlower = null,
            ItemDifferentHolder.TYPE_TEXT

            ),
        ItemFlower(
            id = 5,
            name = "Гартензия",
            descriptionFlower = "Много цветков в одном кусте.",
            resourceImageFlower = "https://korolevskysad.ru/wp-content/uploads/2019/02/Gortenziya-krupnolistnaya-Blyu-Bodensi-1.jpg",
            ItemDifferentHolder.TYPE_IMAGE


        ),
        ItemFlower(
            id = 6,
            name = "Пион",
            descriptionFlower = null,
            resourceImageFlower = null,
            ItemDifferentHolder.TYPE_TEXT

            ),


        ItemFlower(
            id = 7,
            name = "Фиалка",
            descriptionFlower = null,
            resourceImageFlower = null,
            ItemDifferentHolder.TYPE_TEXT

            )
    )

    fun flowerList() = flowers
}