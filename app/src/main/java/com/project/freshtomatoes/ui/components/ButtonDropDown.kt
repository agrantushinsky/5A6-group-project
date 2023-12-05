package com.project.freshtomatoes.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Generic button dropdown
 *
 * @param label: String (Label for the combobox)
 * @param currentState: T (state of the combobox)
 * @param setState: Sets the state of the enum
 * @param enumValues: Array<T> (array of enum values. T.values(), for some reason the generic cant do it)
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T: Enum<T>> ButtonDropDown(label: String, icon: ImageVector, currentState: T, setState: (T) -> Unit, enumValues: Array<T>, modifier: Modifier = Modifier) {
    // expanded state of dropdown
    var expanded by remember { mutableStateOf(false) }

    // Main composable for the dropdown
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        // TextField to display the label and value of the dropdown
        // Modifier.menuAnchor() allows the dropdown to happen.
        TextField(
            value = currentState.toString(),
            label = { Text(label) },
            readOnly = true,
            onValueChange = {},
            modifier = modifier.menuAnchor(),
            trailingIcon = { Icon(imageVector = icon, contentDescription = "Button dropdown icon")},
        )

        // Menu to display when expanded is true
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            // Add every value of the enum as a DropdownMenuItem
            enumValues.forEach {
                DropdownMenuItem(
                    text = { Text(it.toString()) },
                    // "unexpand" the dropdown when an option is selected.
                    onClick = {
                        setState(it)
                        expanded = false
                    },
                    modifier = if(currentState == it) Modifier.background(MaterialTheme.colorScheme.primary) else Modifier
                )
            }
        }
    }
}