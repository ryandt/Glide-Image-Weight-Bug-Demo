package com.example.glideimageweightbugdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.glideimageweightbugdemo.ui.theme.GlideImageWeightBugDemoTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GlideImageWeightBugDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    ImageGrid(
                        modifier = Modifier
                            .requiredSize(150.dp)
                            .clip(RoundedCornerShape(CornerSize(12.dp))),
                        imageUrls = listOf(
                            "https://media.post.rvohealth.io/wp-content/uploads/sites/2/2021/06/GRT-weight-dumbbell-1296x728-header.jpg",
                            "https://static.thcdn.com/images/xlarge/original/app/uploads/sites/478/2021/01/calories-weight-lifting-HERO-min_1642171338.jpg",
                            "https://www.usa-iron.com/cdn/shop/products/8Y0A9321_reduced_2000x.jpg?v=1655075241",
                        ),
                    )
                }
            }
        }
    }
}
