package com.splendo.composeplayground.item

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.splendo.composeplayground.common.Composer

class ItemComposer : Composer<ItemViewModel> {

    override lateinit var viewModel: ItemViewModel

    @Composable
    override fun composeUi(colors: Colors) {
        val showAlert = remember { mutableStateOf(false) }
        layout(
            colors,
            //viewModel::showAlert,
            {
                showAlert.value = true
            },
            viewModel.buttonTitle,
            viewModel.name.observeAsState()
        )
        if (showAlert.value) {
            showAlert(showAlert)
        }
    }

    @Composable
    @Preview
    fun layoutPreview() {
        val showAlert = remember { mutableStateOf(false) }
        layout(
            lightColors(),
            {
                showAlert.value = true
            },
            "Show alert",
            object : State<String?> {
                override val value = "Example 1"
            }
        )
        if (showAlert.value) {
            showAlert(showAlert)
        }
    }

    @Composable
    private fun layout(
        colors: Colors,
        buttonClickHandler: () -> Unit,
        buttonTitle: String,
        itemName: State<String?>) {
        MaterialTheme(colors) {
            Box(modifier = Modifier.fillMaxSize(), Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = itemName.value!!, style = MaterialTheme.typography.h4)
                    Spacer(Modifier.preferredHeight(16.dp))
                    Button(buttonClickHandler) {
                        Text(buttonTitle)
                    }
                }

            }
        }
    }

    @Composable
    @Preview
    fun showAlert(showAlert: MutableState<Boolean>) {
        AlertDialog(
            onDismissRequest = { showAlert.value = false },
            title = {
                Text(text = "Title")
            },
            text = {
                Text("Message")
            },
            confirmButton = {
                Button(onClick = { showAlert.value = false }) {
                    Text("Dismiss")
                }
            }
        )
    }

}