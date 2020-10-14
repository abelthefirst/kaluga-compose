package com.splendo.composeplayground.item

import com.splendo.kaluga.architecture.viewmodel.BaseViewModel

class AlertViewModel : BaseViewModel() {

    internal val alertMessage = "Message"

    internal val alertTitle = "Title"

    internal val dismissButtonTitle = "Dismiss"

    internal fun onDismiss() {
    }

}