package dev.phyo.userapp.presentation.user

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import dev.phyo.userapp.data.remote.model.User

@Composable
fun Userlist(users: List<User>, navController: NavHostController) {
    LazyColumn {
        items(users){ user ->
            UserItem(user, onClick = {
                navController.navigate("user_details/${user.id}")
                Log.d("UserId", user.id.toString())
            })
        }
    }
}