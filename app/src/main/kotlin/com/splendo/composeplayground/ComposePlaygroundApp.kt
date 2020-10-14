package com.splendo.composeplayground

import android.app.Application
import com.splendo.composeplayground.common.Composer
import com.splendo.composeplayground.data.DataStore
import com.splendo.composeplayground.item.ItemViewModel
import com.splendo.composeplayground.list.ListNavigationAction
import com.splendo.composeplayground.list.ListViewModel
import com.splendo.kaluga.alerts.AlertInterface
import com.splendo.kaluga.architecture.navigation.Navigator
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

private const val DATA_STORE_ITEMS_COUNT = 20

class ComposePlaygroundApp : Application() {

    private val dataModule = module {

        single { DataStore(DATA_STORE_ITEMS_COUNT) }

    }

    private val viewModelModule = module {

        viewModel { (navigator: Navigator<ListNavigationAction>) -> ListViewModel(get(), navigator) }

        viewModel { (alertBuilder: AlertInterface.Builder, composer: Composer<ItemViewModel>, itemId: Int) -> ItemViewModel(alertBuilder, composer, get(), itemId) }

    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ComposePlaygroundApp)
            modules(listOf(dataModule, viewModelModule))
        }
    }

}