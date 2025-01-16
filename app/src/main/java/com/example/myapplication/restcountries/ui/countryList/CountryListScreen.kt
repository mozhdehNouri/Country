package com.example.myapplication.restcountries.ui.countryList

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myapplication.R
import com.example.myapplication.designSystem.CommunityListLoading
import com.example.myapplication.designSystem.TMCenterAlignedTopAppBar
import com.example.myapplication.restcountries.ui.DynamicAsyncImage
import com.example.myapplication.restcountries.ui.countryDetails.AppIcons.KeyboardArrowRight
import com.example.myapplication.designSystem.SearchTextField
import com.example.myapplication.restcountries.ui.model.CountriesUiModel
import com.example.myapplication.ui.theme.AppThemePreviews
import com.example.myapplication.ui.theme.Light_Grey
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.Secondary_Grey
import com.example.myapplication.ui.theme.Text_Element_Grey
import com.example.myapplication.ui.theme.Yellow_800

@Composable
fun CountryListRoute(onClick: (name: String) -> Unit) {
    CountryListContent(
        viewModel = hiltViewModel(),
        onClick
    )
}

@Composable
private fun CountryListContent(
    viewModel:
    CountryListViewModel,
    onItemClick: (id: String) -> Unit
) {
    val uiState by viewModel.getCountries.collectAsStateWithLifecycle()
    val context = LocalContext.current
    when (uiState) {
        is CountryListUiState.Loading -> {
            CommunityListLoading()
        }

        is CountryListUiState.Success -> CountryListBody(
            (uiState as CountryListUiState.Success).countryList,
            onItemClick = onItemClick
        )

    }

    LaunchedEffect(Unit) {
        viewModel.event.collect {
            Toast.makeText(context, (it as ShowError.ShowToast).message, Toast.LENGTH_SHORT).show()
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CountryListBody(
    countryList: List<CountriesUiModel>,
    onItemClick: (id: String) -> Unit,
    modifier: Modifier = Modifier
) {

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { TMCenterAlignedTopAppBar(R.string.lbl_list_toolbar) }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            SearchTextField()
            LazyColumn(modifier = Modifier.padding(top = 21.dp)) {
                item {
                    Text(
                        "Countries",
                        modifier = Modifier.padding(start = 13.dp),
                        style =
                        MaterialTheme.typography.titleMedium,
                        color = Secondary_Grey
                    )
                }
                items(items = countryList, key = { it.name }) {
                    CountryItemsList(
                        it, onItemClick
                    )
                }

            }
        }
    }
}

@Composable
private fun CountryListLoading() {
    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        CircularProgressIndicator(
            modifier = Modifier.size(160.dp), color = Yellow_800, strokeCap
            = StrokeCap.Square
        )
    }
}

@Composable
private fun CountryItemsList(
    item: CountriesUiModel,
    onItemClick: (id: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.5.dp)
            .clickable {
                onItemClick(item.name)
            }
    ) {
        DynamicAsyncImage(imageUrl = item.flags, contentDescription = null)
        Column(
            modifier = Modifier
                .weight(2f)
                .padding(start = 9.dp)
        ) {
            Text(
                item.name, style = MaterialTheme.typography.bodyLarge, color =
                Text_Element_Grey
            )
            Text(
                item.capital.firstOrNull() ?: "",
                style = MaterialTheme.typography.bodyMedium,
                color =
                Secondary_Grey
            )
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 9.dp),
                thickness = 1.dp,
                color = Light_Grey
            )
        }
        IconButton(onClick = { }) {
            Icon(
                KeyboardArrowRight,
                contentDescription = null,
                tint = Secondary_Grey
            )
        }

    }
}

@Composable
fun CountryErrorDialog(
    dialogState: Boolean,
    text: String,
    onCancelAction: () -> Unit,
    onSubmitAction: () -> Unit
) {
//    if (dialogState) {
//        TMDialog(
//            text = text,
//            onCancelAction = onCancelAction,
//            onConfirmAction = onSubmitAction,
//            confirmTextButton = stringResource(id = R.string.lbl_try_again),
//            cancelButtonText = stringResource(id = R.string.lbl_exit)
//        )
//    }
}

@AppThemePreviews
@Composable
fun CommunityListBodyPreview() {
    MyApplicationTheme {
    }
}
