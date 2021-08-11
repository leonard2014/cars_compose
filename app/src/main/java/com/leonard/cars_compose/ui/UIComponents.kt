package com.leonard.cars_compose.ui

import android.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.request.CachePolicy
import com.leonard.cars_compose.ui.model.CarsUIItem

@Composable
fun ProgressBar() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ListRow(car: CarsUIItem) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp, bottom = 8.dp, end = 8.dp),
        verticalAlignment = Alignment.Top
    ) {
        car.image?.let {
            CarImage(
                painter = rememberImagePainter(
                    data = car.image,
                    builder = {
                        placeholder(R.drawable.ic_menu_gallery)
                        error(R.drawable.ic_menu_gallery)
                        crossfade(true)
                        diskCachePolicy(CachePolicy.DISABLED)
                    }
                )
            )
        } ?: CarImage(
            painter = painterResource(id = R.drawable.ic_menu_gallery),
        )

        Spacer(Modifier.width(8.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                car.title,
                style = MaterialTheme.typography.subtitle1,
                maxLines = 1
            )
            Text(
                car.price,
                style = MaterialTheme.typography.caption,
                maxLines = 1
            )
        }

    }
}

@Composable
private fun CarImage(painter: Painter) {
    Image(
        painter = painter,
        contentDescription = null,
        modifier = Modifier
            .size(64.dp)
            .clip(RoundedCornerShape(percent = 10)),
        alignment = Alignment.Center,
        contentScale = ContentScale.Crop
    )
}

@Composable
@Preview
private fun PreviewListRow() {
    ListRow(
        CarsUIItem(title = "2021 Toyota Corolla", price = "$30,000", image = null)
    )
}