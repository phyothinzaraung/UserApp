package dev.phyo.userapp.presentation.user

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.phyo.userapp.presentation.viewmodel.UserViewModel

@Composable
fun UserDetailsScreen(userId: String, viewModel: UserViewModel) {

    LaunchedEffect(userId) {
        viewModel.getUserById(userId.toInt())
    }

    val user by viewModel.user.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize()
        .padding(16.dp)) {
        Text(user.id.toString())
        Text("${user.first_name} ${user.last_name}")
        Text(user.email)

    }
}