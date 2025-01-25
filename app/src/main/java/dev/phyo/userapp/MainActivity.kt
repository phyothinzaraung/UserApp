package dev.phyo.userapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import dagger.hilt.android.AndroidEntryPoint
import dev.phyo.userapp.data.remote.model.User
import dev.phyo.userapp.presentation.viewmodel.UIState
import dev.phyo.userapp.presentation.viewmodel.UserViewModel
import dev.phyo.userapp.ui.theme.UserAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UserAppTheme {
                UserMain(userViewModel)
            }
        }
    }
}

@Composable
fun UserMain(userViewModel: UserViewModel) {
    val uiState by userViewModel.uiState.collectAsStateWithLifecycle()
    when(val s = uiState ){
        is UIState.Loading -> {
            CircularProgressIndicator()
        }
        is UIState.Done -> { Userlist(s.users)}
        is UIState.Error -> {
            Text(
                text = s.message
            )
        }
    }

}

@Composable
fun Userlist(users: List<User>) {
    LazyColumn {
        items(users){
            UserItem(it)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun UserItem(user: User) {
    Row {
        GlideImage(model = user.avatar, contentDescription = "user image")
        Text(user.email)
    }
}