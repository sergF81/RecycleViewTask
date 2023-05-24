package com.example.recycleviewtask.data

class DataSource {

    // Источником может быть любое хранилище, бекенд, бд. В данном случае заглушка из объектов хранящихся в памяти
    private val flowers = ItemsFlower.flowerList()

    /**
     * Метод получения цветка по его id
     *
     * @param id - id цветка
     * @return null - если список пуст, или нет цветка с заданным id
     */
    fun getFlower(id: Int): ItemFlower? {
        return flowers.find { it.id == id }
    }

    /**
     * Метод добавления цветка в "хранилище"
     *
     * @return true - если удалось сохранить цветок, в противном случае false
     */
    fun addFlower(flower: ItemFlower): Boolean {
        return flowers.add(flower);
    }

    /**
     * Метод получения всех цветов
     *
     * @return если список пуст, возвращает null

     * p.s. В данном примере он {flowers}, конечно всегда не пуст. Но в реальности так не бывает.
     * Такой случай надо предусматривать, обрабатывать и писать тесты
     */
    fun getAll(): MutableList<ItemFlower>? {
        return flowers
    }
}