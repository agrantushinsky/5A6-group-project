package com.project.freshtomatoes.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.project.freshtomatoes.R

/**
 * PasswordField represents a text field for users to enter their password. Includes the ability for
 * users to toggle the visibility of their password, by default, passwords are invisible to protect privacy.
 *
 * @param label Label for TextField.
 * @param password Current state of the password field.
 * @param onPasswordChange Callback on password field modification. Passes new password as String.
 * @param passwordVisible Current state of the password visibility. Defaults to a rememberSaveable of a mutable boolean.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordField(
    label: String,
    password: State<String>,
    onPasswordChange: (String) -> Unit,
    passwordVisible: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) }
) {
    // Set the show icon according to the current state of the password visibility.
    val icon = painterResource(
        if (passwordVisible.value) R.drawable.design_ic_visibility else R.drawable.design_ic_visibility_off
    )

    // Main text field for password entry.
    TextField(
        value = password.value,
        onValueChange = { onPasswordChange(it) },
        label = { Text(label) },
        trailingIcon = {
            // Toggleable icon button for password visibility.
            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(
                    painter = icon,
                    contentDescription = "Visibility Icon"
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        // Set password visibility accordingly.
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        modifier = Modifier.padding(20.dp)
    )
}
