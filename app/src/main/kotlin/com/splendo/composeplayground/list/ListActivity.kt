package com.splendo.composeplayground.list

import android.os.Bundle
import androidx.compose.ui.platform.setContent
import com.google.android.material.composethemeadapter.createMdcTheme
import com.splendo.kaluga.architecture.navigation.Navigator
import com.splendo.kaluga.architecture.viewmodel.KalugaViewModelActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ListActivity : KalugaViewModelActivity<ListViewModel>() {

    override val viewModel: ListViewModel by viewModel { parametersOf(Navigator(::listNavigationActionMapper)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val (colors) = createMdcTheme(this)
            layout(colors!!, viewModel)
        }
    }

}