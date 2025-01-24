package dev.phyo.userapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.phyo.userapp.data.model.User
import dev.phyo.userapp.domain.usecase.GetUserUseCase
import dev.phyo.userapp.util.DataResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val getUserUseCase: GetUserUseCase): ViewModel() {

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>>
        get() = _users

    init {
        viewModelScope.launch {
            getUserUseCase().collect{
                when(it){
                    is DataResult.Success -> {_users.value = it?.data ?: emptyList() }
                    is DataResult.Error -> {}
                    is DataResult.Loading -> {}
                }
            }
        }
    }
}