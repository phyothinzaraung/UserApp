package dev.phyo.userapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.phyo.userapp.data.remote.model.User
import dev.phyo.userapp.domain.mapper.Users
import dev.phyo.userapp.domain.usecase.GetSavedUserUseCase
import dev.phyo.userapp.domain.usecase.GetUserByIdUseCase
import dev.phyo.userapp.util.DataResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    getSavedUserUseCase: GetSavedUserUseCase,
    private val getUserByIdUseCase: GetUserByIdUseCase
): ViewModel() {

    val uiState = getSavedUserUseCase().map {
        when(it){
                is DataResult.Success -> {
                    if(it.data != null){
                        UIState.Done(it.data)
                    }else{
                        UIState.Error("No Data from server")
                    }
                }
                is DataResult.Error -> {
                    UIState.Error(it.message?: "Something went wrong")
                }
                is DataResult.Loading -> {
                    UIState.Loading
                }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = UIState.Loading
    )

    private val _userId = MutableStateFlow<Int?>(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    val user: StateFlow<User> = _userId
        .filterNotNull()
        .flatMapLatest { userId ->
            getUserByIdUseCase(userId)
        }
        .map { result ->
            when (result) {
                is DataResult.Success -> {
                    result.data ?: User(0, "", "", "", "")
                }
                is DataResult.Error -> {
                    User(0, "", "", "", "")
                }
                is DataResult.Loading -> {
                    User(0, "Loading...", "", "", "")
                }
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = User(0, "", "", "", "")
        )

    fun getUserById(userId: Int) {
        _userId.value = userId
    }
}

interface UIState{
    object Loading: UIState
    data class Error(val message: String): UIState
    data class Done(val users: Users): UIState
}