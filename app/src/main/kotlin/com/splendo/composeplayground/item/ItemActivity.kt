package com.splendo.composeplayground.item

import android.os.Bundle
import androidx.compose.ui.platform.setContent
import com.google.android.material.composethemeadapter.createMdcTheme
import com.splendo.composeplayground.list.ListNavigationBundleSpec
import com.splendo.composeplayground.list.ListNavigationBundleSpecRow
import com.splendo.kaluga.alerts.AlertInterface
import com.splendo.kaluga.architecture.navigation.toNavigationBundle
import com.splendo.kaluga.architecture.viewmodel.KalugaViewModelActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ItemActivity : KalugaViewModelActivity<ItemViewModel>() {

    override val viewModel: ItemViewModel by viewModel {
        val navigationBundle = intent.extras!!.toNavigationBundle(ListNavigationBundleSpec.OpenItemDetailsBundleSpec)
        val itemId = navigationBundle.get(ListNavigationBundleSpecRow.NavigationBundleInt)
        parametersOf(AlertInterface.Builder(), ItemComposer(), itemId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val (colors) = createMdcTheme(this)
            viewModel.composeUi(colors!!)
        }
    }

}