package com.mohamedfathidev.accuweather.ui.forecasting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.mohamedfathidev.accuweather.R
import com.mohamedfathidev.accuweather.data.remote.model.CurrentWeather
import com.mohamedfathidev.accuweather.data.remote.model.Day
import com.mohamedfathidev.accuweather.util.ErrorDialog
import com.mohamedfathidev.accuweather.util.FullScreenLoading
import com.mohamedfathidev.accuweather.util.ResponseState
import com.mohamedfathidev.accuweather.util.parseDateToDay

@Composable
fun ForecastingScreen(
    navController: NavController,
    viewModel: ForecastingViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    var forecastedWeatherState by remember {
        mutableStateOf(CurrentWeather())
    }

    when (val pageState = state) {
        is ResponseState.Loading -> {
            FullScreenLoading()
        }

        is ResponseState.Error -> {
            ErrorDialog(message = pageState.message) {}
        }

        is ResponseState.Success -> {
            forecastedWeatherState = pageState.data as CurrentWeather
        }

        else -> {}
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {
            forecastedWeatherState.forecast?.forecastday?.let {
                items(items = it) { forecastDay ->
                    DaysItem(date = forecastDay.date, days = forecastDay.day, viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun DaysItem(
    viewModel: ForecastingViewModel,
    date: String,
    days: Day
) {
    Card(modifier = Modifier.fillMaxWidth()) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = parseDateToDay(date),
                    style = TextStyle(fontSize = 16.sp, color = MaterialTheme.colors.onBackground)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = days.condition.text,
                    style = TextStyle(fontSize = 16.sp, color = MaterialTheme.colors.onBackground)
                )
            }

            AsyncImage(
                contentDescription = "Weather icon",
                model = "https:${days.condition.icon}",
                placeholder = painterResource(
                    id = R.drawable.current_weather
                ),
                modifier = Modifier
                    .height(50.dp)
                    .width(50.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontSize = 16.0.sp,
                                fontWeight = FontWeight(700.0.toInt()),
                                fontStyle = FontStyle.Normal,
                            )
                        ) {
                            append((days.maxtempC ?: "..").toString())
                        }
                        withStyle(
                            style = SpanStyle(
                                baselineShift = BaselineShift.Superscript,
                                fontSize = 8.0.sp,
                                fontWeight = FontWeight(300.0.toInt()),
                                fontStyle = FontStyle.Normal,
                            )
                        ) { // AnnotatedString.Builder
                            append("o")
                        }
                    },
                    style = TextStyle(fontSize = 16.sp, color = MaterialTheme.colors.onBackground)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontSize = 16.0.sp,
                                fontWeight = FontWeight(700.0.toInt()),
                                fontStyle = FontStyle.Normal,
                            )
                        ) {
                            append((days.mintempC ?: "..").toString())
                        }
                        withStyle(
                            style = SpanStyle(
                                baselineShift = BaselineShift.Superscript,
                                fontSize = 8.0.sp,
                                fontWeight = FontWeight(300.0.toInt()),
                                fontStyle = FontStyle.Normal,
                            )
                        ) { // AnnotatedString.Builder
                            append("o")
                        }
                    },
                    style = TextStyle(fontSize = 16.sp, color = MaterialTheme.colors.onBackground)
                )
            }
        }
    }
}