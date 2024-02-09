package com.example.glideimageweightbugdemo.ui.theme

import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize

/**
 * Updates [size] with the size of the Composable once measured as non-zero.
 */
fun Modifier.changeSizeIfZero(size: MutableState<IntSize>): Modifier {
    return this.then(
        if (size.value.width == 0) {
            Modifier.onSizeChanged {
                if (it.width != 0) {
                    size.value = it
                }
            }
        } else {
            Modifier
        }
    )
}