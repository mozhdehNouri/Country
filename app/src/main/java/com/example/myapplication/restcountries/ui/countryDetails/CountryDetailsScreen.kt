package com.example.myapplication.restcountries.ui.countryDetails

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myapplication.restcountries.ui.DynamicAsyncImage
import com.example.myapplication.restcountries.ui.countryDetails.AppIcons.ArrowBack
import com.example.myapplication.restcountries.ui.countryDetails.AppIcons.Menu
import com.example.myapplication.restcountries.ui.model.CountriesUiModel
import com.example.myapplication.ui.theme.AppThemePreviews
import com.example.myapplication.ui.theme.Black
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.Secondary_Grey
import com.example.myapplication.ui.theme.Secondary_Grey_opacity_12
import com.example.myapplication.ui.theme.Text_Element_Grey
import com.example.myapplication.ui.theme.White
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState


@Composable
fun CountryDetailsRoute(onBackClick: () -> Unit) {
    CountryDetailsContent(viewModel = hiltViewModel(), onBackClick)
}

@Composable
private fun CountryDetailsContent(viewModel: CountryDetailsViewModel, onBackClick: () -> Unit) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val scrollable = rememberScrollState()

    CountryDetailsBody(uiState = uiState, scrollable, onBackClick = onBackClick)
}


@Composable
fun CountryDetailsBody(
    uiState: CountriesUiModel,
    scrollable: ScrollState,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollable)
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Secondary_Grey_opacity_12)
        ) {
            DetailsScreenTopAppBar(onBackClick, modifier = Modifier.align(Alignment.TopEnd))
            DynamicAsyncImage(
                imageUrl = uiState.flags,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .clip(CircleShape)
                    .border(width = 4.dp, White, shape = CircleShape)
                    .align(Alignment.BottomCenter)
                    .size(180.dp)
                    .shadow(
                        elevation = 15.dp,
                        spotColor = Black,
                        ambientColor = Black
                    )
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp)
                .weight(2f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = uiState.name,
                style = MaterialTheme.typography.headlineMedium,
                color = Text_Element_Grey
            )
            Text(
                text = uiState.capital.firstOrNull() ?: "",
                style = MaterialTheme.typography.titleMedium,
                color = Secondary_Grey
            )
            Text(
                text = uiState.startOfWeek,
                style = MaterialTheme.typography.titleMedium,
                color = Secondary_Grey
            )

//            GoogleMapM(LatLng(uiState.capitalInfo.firstOrNull()?.toDouble() ?: 0.0,uiState.capitalInfo.lastOrNull()?.toDouble() ?: 0.0))
        }
    }
}

// This is need google map api
@Composable
fun GoogleMapM(
    markers: LatLng
) {
    GoogleMap(
        modifier = Modifier.size(100.dp),
        cameraPositionState = rememberCameraPositionState(),
        properties = MapProperties(isMyLocationEnabled = true)
    ) {
        Marker(
            state = MarkerState(position = markers),
            title = "Marker Title",
            snippet = "Marker Snippet"
        )
    }
}

@Composable
private fun DetailsScreenTopAppBar(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Transparent),
        horizontalArrangement = Arrangement
            .SpaceBetween
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                ArrowBack,
                contentDescription = null,
                tint = Color.White
            )
        }

        IconButton(onClick = {}) {
            Icon(Menu, contentDescription = null, tint = Color.White)
        }
    }

}

@AppThemePreviews
@Composable
fun DetailsScreenPreview() {
    MyApplicationTheme {
//        CountryDetailsBody((), rememberScrollState(), {})
    }
}