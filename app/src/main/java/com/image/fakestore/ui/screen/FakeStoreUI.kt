package com.image.fakestore.ui.screen

import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.image.fakestore.R
import com.image.fakestore.ui.model.FakeStoreData
import com.image.fakestore.ui.viewModel.FakeStoreViewModel
import com.image.fakestore.ui.viewState.UiState

@Composable
fun FakeStoreUI(viewModel: FakeStoreViewModel) {
    val modifier = Modifier.fillMaxSize().background(color = Color.White)
    val data by viewModel.storeData.collectAsState()

    AnimatedContent(targetState = viewModel.uiState.value, label = "", transitionSpec = {
        fadeIn(animationSpec = tween(1000)) togetherWith fadeOut(animationSpec = tween(600))
    }) {
        Log.e("checkUi","  :  $it")
        when (it) {
            // Specifies the mapping between a given FreeTrialUIState and a composable function.
            UiState.StoreData -> StoreItemsList(data,modifier)
            UiState.InProgress -> ProgressLoader(modifier)
        }
    }
}

@Composable
fun StoreItemsList(data: ArrayList<FakeStoreData>, modifier: Modifier) {
    val listState = rememberLazyListState()
    LazyColumn(
        state = listState,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier,
    ) {
        items(data.size) { itemIndex ->
            StoreItemUI(data[itemIndex])
        }
    }
}

@Composable
fun ProgressLoader(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier, contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(48.dp),
            color = colorResource(id = R.color.dark),
            trackColor = colorResource(id = R.color.light),
            strokeWidth = 4.dp
        )
    }
}

@Composable
fun StoreItemUI(fakeStoreData: FakeStoreData) {

    Row(
        modifier = Modifier,
        horizontalArrangement = Arrangement.Center,
//        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(fakeStoreData.image ?: "-")
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "",
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .padding(20.dp)
                .width(100.dp)
        )

        Column {
            Text(
                modifier = Modifier
                    .padding(all = 12.dp)
                    .fillMaxWidth(),
                text = fakeStoreData.title.toString(),
                style = TextStyle(fontSize = 16.sp),
                textAlign = TextAlign.Start
            )
            Text(
                modifier = Modifier
                    .padding(start = 12.dp, end = 12.dp)
                    .fillMaxWidth(),
                text = fakeStoreData.description.toString(),
                style = TextStyle(fontSize = 12.sp),
                textAlign = TextAlign.Start
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FreeTrialUIPreview() {
    StoreItemUI(FakeStoreData())
}
