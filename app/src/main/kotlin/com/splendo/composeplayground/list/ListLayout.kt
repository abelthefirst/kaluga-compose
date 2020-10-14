package com.splendo.composeplayground.list

import android.util.Log
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.splendo.composeplayground.data.DataItem

@Composable
fun layout(colors: Colors, viewModel: ListViewModel) {
    Log.d("Debug", "fun layout(colors: Colors, viewModel: ListViewModel)")
    val loading = viewModel.loading.observeAsState()
    layout(
        colors,
        viewModel.itemList.observeAsState(),
        viewModel::onItemClick,
        loading.value!!
    )
}

@Composable
@Preview
fun layoutPreview() {
    layout(
        lightColors(),
        object : State<List<DataItem>?> {
            override val value = listOf(
                DataItem(1, "Example 1"),
                DataItem(2, "Example 2")
            )
        },
        {},
        true)
}

@Composable
@Preview
private fun dataItem() {
    dataItem(dataItem = DataItem(1, "Name"), dataItemClickHandler = {})
}

@Composable
private fun dataItem(dataItem: DataItem, dataItemClickHandler: (Int) -> Unit) {
    val boxModifier = Modifier
        .clip(shape = RoundedCornerShape(8.dp))
        .background(MaterialTheme.colors.primary)
        .fillMaxWidth()
        .clickable {
            dataItemClickHandler(dataItem.id)
        }
    Box(boxModifier) {
        Text(modifier = Modifier.padding(16.dp), text = dataItem.name, color = Color.White)
    }
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
private fun loadedState(itemList: List<DataItem>, itemClickHandler: (Int) -> Unit) {
    Log.d("Debug", "private fun loadedState(itemList: List<DataItem>, itemClickHandler: (Int) -> Unit)")
    LazyColumnFor(contentPadding = PaddingValues(16.dp, 8.dp, 16.dp), items = itemList) {
        dataItem(it, itemClickHandler)
    }
}

@Composable
private fun layout(
    colors: Colors,
    itemList: State<List<DataItem>?>,
    itemClickHandler: (Int) -> Unit,
    loading: Boolean) {
    Log.d("Debug", "private fun layout(\n" +
            "    colors: Colors,\n" +
            "    itemList: State<List<DataItem>?>,\n" +
            "    itemClickHandler: (Int) -> Unit,\n" +
            "    loading: State<Boolean?>)")
    if (loading) {
        Log.d("Debug", "ololo 1")
        loadingState()
    } else {
        Log.d("Debug", "ololo 2")
        loadedState(itemList.value!!, itemClickHandler)
    }
}

@Composable
private fun loadingState() {
    Log.d("Debug", "private fun loadingState()")
    Box(Modifier.fillMaxSize(), Alignment.Center) {
        CircularProgressIndicator()
    }
}