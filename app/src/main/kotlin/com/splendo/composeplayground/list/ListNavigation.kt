package com.splendo.composeplayground.list

import com.splendo.composeplayground.item.ItemActivity
import com.splendo.kaluga.architecture.navigation.*

sealed class ListNavigationBundleSpecRow<T>(associatedType: NavigationBundleSpecType<T>) : NavigationBundleSpecRow<T>(associatedType) {

    object NavigationBundleInt : ListNavigationBundleSpecRow<Int>(NavigationBundleSpecType.IntegerType)

}

sealed class ListNavigationBundleSpec(rows: Set<ListNavigationBundleSpecRow<*>>) : NavigationBundleSpec<ListNavigationBundleSpecRow<*>>(rows) {

    object OpenItemDetailsBundleSpec : ListNavigationBundleSpec(setOf(ListNavigationBundleSpecRow.NavigationBundleInt))

}

sealed class ListNavigationAction(navigationBundle: NavigationBundle<ListNavigationBundleSpecRow<*>>) : NavigationAction<ListNavigationBundleSpecRow<*>>(navigationBundle) {

    class OpenItemDetailsAction(itemId: Int) : ListNavigationAction(ListNavigationBundleSpec.OpenItemDetailsBundleSpec.toBundle {
        when(it) {
            is ListNavigationBundleSpecRow.NavigationBundleInt -> it.convertValue(itemId)
        }
    })

}

fun listNavigationActionMapper(listNavigationAction: ListNavigationAction): NavigationSpec {
    when(listNavigationAction) {
        is ListNavigationAction.OpenItemDetailsAction -> NavigationSpec.Activity(ItemActivity::class.java)
    }

    return NavigationSpec.Activity(ItemActivity::class.java)
}