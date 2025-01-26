package dev.phyo.userapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.phyo.userapp.presentation.user.UserDetailsScreen
import dev.phyo.userapp.presentation.user.UserListScreen
import dev.phyo.userapp.presentation.viewmodel.UserViewModel
import dev.phyo.userapp.ui.theme.UserAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UserAppTheme {
                AppNavigation(navController = rememberNavController())
            }
        }
    }
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "user_list") {
        composable("user_list") {
            UserListScreen(navController= navController, viewModel = hiltViewModel<UserViewModel>())
        }
        composable("user_details/{id}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("id") ?: ""
            UserDetailsScreen(userId, viewModel = hiltViewModel<UserViewModel>())
        }
    }
}