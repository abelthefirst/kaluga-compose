package com.splendo.composeplayground.common

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import com.splendo.kaluga.architecture.viewmodel.BaseViewModel

interface Composer<T : BaseViewModel> {

    var viewModel: T

    @Composable
    fun composeUi(colors: Colors)

}