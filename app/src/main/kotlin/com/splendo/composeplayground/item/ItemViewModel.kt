package com.splendo.composeplayground.item

import androidx.lifecycle.MutableLiveData
import com.splendo.composeplayground.common.Composer
import com.splendo.composeplayground.data.DataStore
import com.splendo.kaluga.alerts.Alert
import com.splendo.kaluga.alerts.AlertInterface
import com.splendo.kaluga.alerts.buildAlert
import com.splendo.kaluga.architecture.viewmodel.BaseViewModel
import kotlinx.coroutines.*

private const val DELAY_MS = 500L

class ItemViewModel(val mAlertBuilder: AlertInterface.Builder, private val mComposer: Composer<ItemViewModel>, private val mDataStore: DataStore, private val mItemId: Int) : BaseViewModel(), Composer<ItemViewModel> by mComposer {

    val buttonTitle = "Show alert"

    val name: MutableLiveData<String> = MutableLiveData("")

    private val mAlert: AlertInterface

    init {
        mComposer.viewModel = this

        val alertViewModel = AlertViewModel()
        mAlert = mAlertBuilder.buildAlert {
            setTitle(alertViewModel.alertTitle)
            setMessage(alertViewModel.alertMessage)
            addActions(Alert.Action(alertViewModel.dismissButtonTitle, handler = alertViewModel::onDismiss))
        }
    }

    fun showAlert() = mAlert.showAsync()

    override fun onResume(scope: CoroutineScope) {
        scope.launch {
            val itemDeferred = async(currentCoroutineContext()) {
                delay(DELAY_MS)
                mDataStore.getItemById(mItemId)
            }
            name.value = itemDeferred.await().name
        }
    }

}