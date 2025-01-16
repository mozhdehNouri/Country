package com.example.myapplication.designSystem

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CommunityListLoading() {
    CircularProgressIndicator(modifier = Modifier.size(80.dp))
}