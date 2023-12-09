package com.project.freshtomatoes.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.project.freshtomatoes.data.FilterConfig

//Coded by Aidan
// Clamps an integer to a min/max value.
private fun ClampInt(value: Int?, min: Int, max: Int): Int? {
    if (value == null) {
        return null
    }

    return Math.min(max, Math.max(min, value))
}

/**
 * FilterConfigurator allows the user to setup their FilterConfig with a clickable dropdown.
 *
 * @param modifier Modifier to be passed down. Defaulted to Modifier.
 * @param filterConfig Current filter config to display.
 * @param setFilterConfig Callback function for when the configurator changes the filter config
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterConfigurator(modifier: Modifier = Modifier, filterConfig: FilterConfig, setFilterConfig: (FilterConfig) -> Unit) { // expanded state of dropdown
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
            value = if (filterConfig.minRating != null || filterConfig.maxRating != null) "Rating" else "",
            label = { Text("Filter") },
            readOnly = true,
            onValueChange = {},
            modifier = modifier.menuAnchor(),
            trailingIcon = { Icon(imageVector = Icons.Filled.FilterAlt, contentDescription = "Filter icon") }
        )

        // Menu to display when expanded is true
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            // Add every value of the enum as a DropdownMenuItem
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Rating min & max fields.
                TextField(
                    value = if (filterConfig.minRating == null) "" else filterConfig.minRating.toString(),
                    onValueChange = {
                        setFilterConfig(FilterConfig(ClampInt(it.toIntOrNull(), 0, 5), filterConfig.maxRating))
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.width(50.dp)
                )
                Text(" <= Rating <= ")
                TextField(
                    value = if (filterConfig.maxRating == null) "" else filterConfig.maxRating.toString(),
                    onValueChange = {
                        setFilterConfig(FilterConfig(filterConfig.minRating, ClampInt(it.toIntOrNull(), 0, 5)))
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.width(50.dp)
                )
            }
        }
    }
}
