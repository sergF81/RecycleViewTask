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
            imageFlower = "https://upload.wikimedia.org/wikipedia/commons/b/bf/Rosa_Red_Chateau01.jpg"

        ),
        ItemFlower(
            id = 2,
            name = "Лилия",
            imageFlower = "https://upload.wikimedia.org/wikipedia/commons/1/1e/Lilium_candidum_2.jpg"

        ),
        ItemFlower(
            id = 3,
            name = "Тюльпан",
            imageFlower = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8f/Tulipa_Couleur_Cardinal.jpg/1200px-Tulipa_Couleur_Cardinal.jpg"

        ),
        ItemFlower(
            id = 4,
            name = "Гладиолус",
            imageFlower = "https://img.7dach.ru/image/600/00/00/53/2013/02/20/d9311a.jpg"

        ),
        ItemFlower(
            id = 5,
            name = "Гартензия",
            imageFlower = "https://korolevskysad.ru/wp-content/uploads/2019/02/Gortenziya-krupnolistnaya-Blyu-Bodensi-1.jpg"


        ),
        ItemFlower(
            id = 6,
            name = "Пион",
            imageFlower = "https://tsvetomania.ru/upload/resize_cache/iblock/873/710_605_1/8730652728d8f94dc77dfc27e1221c61.jpg"


        ),


        ItemFlower(
            id = 7,
            name = "Фиалка",
            imageFlower = "https://cdn.botanichka.ru/wp-content/uploads/2022/12/czvetochnye-motylki-fialka-sestrinskaya-v-sadu.jpg"


        )
    )

    fun flowerList() = flowers
}