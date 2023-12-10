package com.project.freshtomatoes.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
// Coded by Jose
/**
 * Error dialog component; creates a alert box showing the relevant text.
 *
 * @param header ErrorDialog's header
 * @param text ErrorDialog's inner text
 * @param onDismiss Callback when dialog is dismissed
 */
@Composable
fun ErrorDialog(
    header: String,
    text: String,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text(header, fontSize = 20.sp)
        },
        text = {
            Text(text)
        },
        confirmButton = {
            Button(
                onClick = {
                    onDismiss()
                }
            ) {
                Text("OK")
            }
        }
    )
}
