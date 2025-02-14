package com.coinscope.design.widget

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.coinscope.design.R
import com.coinscope.design.resources.Dimens

@Composable
fun SearchWidget(
    text: MutableState<String>,
    modifier: Modifier = Modifier,
    @StringRes hintId: Int = R.string.search_hint,
    onValueChanged: (String) -> Unit
) {
    val showCancel = remember { mutableStateOf(false) }
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        OutlinedTextField(
            shape = MaterialTheme.shapes.medium,
            value = text.value,
            onValueChange = { newText ->
                text.value = newText
                showCancel.value = newText.isNotEmpty()
                onValueChanged.invoke(newText)
            },
            colors = OutlinedTextFieldDefaults.colors(),
            textStyle = MaterialTheme.typography.bodySmall,
            singleLine = true,
            placeholder = {
                Text(
                    text = stringResource(hintId),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            },
            modifier = Modifier.weight(1F)
        )
        AnimatedVisibility(visible = showCancel.value) {
            Row {
                SpacerWidget(size = Dimens.medium)
                Text(text = stringResource(R.string.cancel),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.clickable {
                        text.value = ""
                        showCancel.value = false
                        onValueChanged.invoke(text.value)
                    })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchWidgetPreview() {
    val search = remember { mutableStateOf("") }
    Box(modifier = Modifier.padding(all = Dimens.large)) {
        SearchWidget(text = search, onValueChanged = {})
    }
}
