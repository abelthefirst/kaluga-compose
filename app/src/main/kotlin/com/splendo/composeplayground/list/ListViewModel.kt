package com.splendo.composeplayground.list

import androidx.lifecycle.MutableLiveData
import com.splendo.composeplayground.data.DataItem
import com.splendo.composeplayground.data.DataStore
import com.splendo.kaluga.architecture.navigation.Navigator
import com.splendo.kaluga.architecture.viewmodel.NavigatingViewModel
import kotlinx.coroutines.*

private const val DELAY_MS = 1500L

class ListViewModel(private val mDataStore: DataStore, navigator: Navigator<ListNavigationAction>) : NavigatingViewModel<ListNavigationAction>(navigator) {

    val itemList: MutableLiveData<List<DataItem>> = MutableLiveData(emptyList())

    val loading: MutableLiveData<Boolean> = MutableLiveData(false)

    fun onItemClick(itemId: Int) {
        navigator.navigate(ListNavigationAction.OpenItemDetailsAction(itemId))
    }

    override fun onResume(scope: CoroutineScope) {
        if (itemList.value!!.isEmpty()) {
            scope.launch {
                loading.value = true
                val list = withContext(currentCoroutineContext()) {
                    delay(DELAY_MS)
                    mDataStore.itemList
                }
                loading.value = false
                itemList.value = list.map { it.copy() }
            }
        }
    }

}