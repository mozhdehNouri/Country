package com.example.myapplication.designSystem

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.myapplication.restcountries.ui.countryDetails.AppIcons.Close
import com.example.myapplication.restcountries.ui.countryDetails.AppIcons.Search
import com.example.myapplication.ui.theme.Secondary_Grey
import com.example.myapplication.ui.theme.Secondary_Grey_opacity_12

@Composable
 fun SearchTextField(
    searchQuery: String="",
    onSearchQueryChanged: (String) -> Unit ={},
) {
    TextField(
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedContainerColor = Secondary_Grey_opacity_12,
            focusedContainerColor = Secondary_Grey_opacity_12
        ),
        enabled = false,
        placeholder = {
            Text(
                "Search by country name or code ", style = MaterialTheme.typography
                    .labelLarge, color = MaterialTheme.colorScheme.onSurface
            )
        },
        leadingIcon = {
            Icon(
                imageVector =Search,
                contentDescription = null,
                tint = Secondary_Grey,
            )
        },
        trailingIcon = {
            if (searchQuery.isNotEmpty()) {
                IconButton(
                    onClick = {
                        onSearchQueryChanged("")
                    },
                ) {
                    Icon(
                        imageVector =Close,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }
        },
        onValueChange = {
            if ("\n" !in it) onSearchQueryChanged(it)
        },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .padding(start = 10.dp, top = 13.dp, end = 9.dp, bottom = 6.dp),
        shape = RoundedCornerShape(8.dp),
        value = searchQuery,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
        ),
        maxLines = 1,
        singleLine = true,
    )
}