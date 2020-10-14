package com.splendo.composeplayground.data

class DataStore(itemListSize: Int) {

    val itemList: List<DataItem>

    init {
        val list = mutableListOf<DataItem>()
        for (i in 0..itemListSize) {
            list.add(DataItem(i, "Item nr $i"))
        }
        itemList = list
    }

    fun getItemById(itemId: Int) = itemList.first { it.id == itemId }

}