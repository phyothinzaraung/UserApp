package dev.phyo.userapp.presentation.user

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import dev.phyo.userapp.data.remote.model.User

@Composable
fun Userlist(users: List<User>, navController: NavHostController, modifier: Modifier = Modifier) {
    Scaffold { innerPadding ->
        LazyColumn(modifier = modifier.padding(innerPadding)) {
            items(users){ user ->
                UserItem(user){
                    navController.navigate("user_details/${user.id}")
                }
            }
        }
    }

}