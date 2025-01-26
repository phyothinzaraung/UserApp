package dev.phyo.userapp.presentation.user

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import dev.phyo.userapp.presentation.viewmodel.UIState
import dev.phyo.userapp.presentation.viewmodel.UserViewModel

@Composable
fun UserListScreen(navController: NavHostController, viewModel: UserViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    when(val s = uiState ){
        is UIState.Loading -> {
            CircularProgressIndicator()
        }
        is UIState.Done -> {
            Userlist(s.users, navController)
        }
        is UIState.Error -> {
            Text(
                text = s.message
            )
        }
    }
}