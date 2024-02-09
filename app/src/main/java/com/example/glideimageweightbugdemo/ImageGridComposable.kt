package com.example.glideimageweightbugdemo

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.glideimageweightbugdemo.ui.theme.changeSizeIfZero

/***
 * Composable view to handle the display of images in a grid that will change its layout depending
 * on the amount of images provided, it can go from displaying 1 single image to a max of 4 images.
 *
 * @param modifier The modifier to be applied to the layout.
 * @param imageUrls A list of images string urls, this component handles resizing of the images
 * based on the size of the view
 * @param horizontalRatio The ratio of the horizontal space that should be used for the columns.
 * e.g. given a ratio of 2.0 (i.e. 2:1), the first column will occupy 2/3rds of the available space.
 */
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ImageGrid(
    modifier: Modifier = Modifier,
    imageUrls: List<String>,
    horizontalRatio: Float = 1.0f,
) {
    val grid = ImageGrid.from(imageUrls)
    val placeholderImage = ColorDrawable(Color.GRAY)

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(horizontalRatio, false)) {
            key(grid.topStart) {
                val topStartImageSize = remember { mutableStateOf(IntSize.Zero) }
                GlideImage(
                    modifier = Modifier
                        .weight(1f, false)
                        .changeSizeIfZero(topStartImageSize),
                    model = getSizedImageUrl(grid.topStart, topStartImageSize.value),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                ) {
                    it.placeholder(placeholderImage)
                }
            }
            if (grid.bottomStart != null) {
                key(grid.bottomStart) {
                    val bottomStartSize = remember { mutableStateOf(IntSize.Zero) }
                    GlideImage(
                        modifier = Modifier
                            .weight(1f, false)
                            .changeSizeIfZero(bottomStartSize)
                            .padding(top = 2.dp),
                        model = getSizedImageUrl(grid.bottomStart, bottomStartSize.value),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    ) {
                        it.placeholder(placeholderImage)
                    }
                }
            }
        }
        if (grid.topEnd != null) {
            Column(
                modifier = Modifier
                    .weight(1f, false)
                    .padding(start = 2.dp),
            ) {
                key(grid.topEnd) {
                    val topEndImageSize = remember { mutableStateOf(IntSize.Zero) }
                    GlideImage(
                        modifier = Modifier
                            .weight(1f, false)
                            .changeSizeIfZero(topEndImageSize),
                        model = getSizedImageUrl(grid.topEnd, topEndImageSize.value),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    ) {
                        it.placeholder(placeholderImage)
                    }
                }
                if (grid.bottomEnd != null) {
                    key(grid.bottomEnd) {
                        val bottomEndImageSize = remember { mutableStateOf(IntSize.Zero) }
                        GlideImage(
                            modifier = Modifier
                                .weight(1f, false)
                                .padding(top = 2.dp)
                                .changeSizeIfZero(bottomEndImageSize),
                            model = getSizedImageUrl(grid.bottomEnd, bottomEndImageSize.value),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                        ) {
                            it.placeholder(placeholderImage)
                        }
                    }
                }
            }
        }
    }
}

/**
 * Simple data class for determining the image positions. Lays out images in one of the following
 * 4 layouts depending on how many images are available.
 *
 * 1.
 * A A
 * A A
 *
 * 2.
 * A B
 * A B
 *
 * 3.
 * A B
 * A C
 *
 * 4.
 * A B
 * C D
 */
private data class ImageGrid(
    val topStart: String,
    val topEnd: String? = null,
    val bottomStart: String? = null,
    val bottomEnd: String? = null
) {

    companion object {

        fun from(list: List<String>): ImageGrid = when (list.size) {
            0 -> ImageGrid("")
            1 -> ImageGrid(list[0])
            2 -> ImageGrid(topStart = list[0], topEnd = list[1])
            3 -> ImageGrid(topStart = list[0], topEnd = list[1], bottomEnd = list[2])
            else -> ImageGrid(
                topStart = list[0], topEnd = list[1],
                bottomStart = list[2], bottomEnd = list[3]
            )
        }
    }
}

private fun getSizedImageUrl(originalUrl: String, size: IntSize): String? {
    return if (size.width == 0) {
        // Don't load anything until we've got a size laid out
        null
    } else {
        // Just returning originalUrl here for demo purposes
        originalUrl
    }
}
