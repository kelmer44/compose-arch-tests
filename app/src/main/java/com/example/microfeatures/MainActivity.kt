package com.example.microfeatures

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.microfeatures.ui.MicroFeaturesTestApp
import com.example.microfeatures.ui.screens.factories.friends.FriendListComposableFactory
import com.example.microfeatures.ui.screens.factories.time.TimeComposableFactory
import com.example.microfeatures.ui.screens.factories.user.UserComposableFactory
import com.example.microfeatures.ui.screens.regular.RegularArchitectureScreen
import com.example.microfeatures.ui.theme.MicroFeaturesTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var friendListComposableFactory: FriendListComposableFactory

    @Inject
    lateinit var userComposableFactory: UserComposableFactory

    @Inject
    lateinit var timeComposableFactory: TimeComposableFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MicroFeaturesTheme {
              MicroFeaturesTestApp(
                  friendListComposableFactory = friendListComposableFactory,
                  userComposableFactory = userComposableFactory,
                  timeComposableFactory = timeComposableFactory
              )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MicroFeaturesTheme {
        Greeting("Android")
    }
}