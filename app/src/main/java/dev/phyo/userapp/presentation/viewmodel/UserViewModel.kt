package dev.phyo.userapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.phyo.userapp.data.remote.model.User
import dev.phyo.userapp.domain.usecase.GetSavedUserUseCase
import dev.phyo.userapp.domain.usecase.GetAndSaveUserUseCase
import dev.phyo.userapp.util.DataResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getAndSaveUserUseCase: GetAndSaveUserUseCase,
    private val getSavedUserUseCase: GetSavedUserUseCase
): ViewModel() {

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>>
        get() = _users

    init {
        viewModelScope.launch {
            getAndSaveUserUseCase()
            getSavedUserUseCase().collect{
                when(it){
                    is DataResult.Success -> {
                        _users.value = it.data ?: emptyList()
                    }
                    is DataResult.Error -> {}
                    is DataResult.Loading -> {}
                }
            }
        }
    }
}