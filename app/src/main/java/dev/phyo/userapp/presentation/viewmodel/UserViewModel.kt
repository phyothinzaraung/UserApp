package dev.phyo.userapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.phyo.userapp.domain.mapper.Users
import dev.phyo.userapp.domain.usecase.GetSavedUserUseCase
import dev.phyo.userapp.util.DataResult
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getSavedUserUseCase: GetSavedUserUseCase
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

//    private val _users = MutableStateFlow<List<User>>(emptyList())
//    val users: StateFlow<List<User>>
//        get() = _users
//
//    init {
//        viewModelScope.launch {
//            getAndSaveUserUseCase()
//            getSavedUserUseCase().collect{
//                when(it){
//                    is DataResult.Success -> {
//                        _users.value = it.data ?: emptyList()
//                    }
//                    is DataResult.Error -> {}
//                    is DataResult.Loading -> {}
//                }
//            }
//        }
//    }
}

interface UIState{
    object Loading: UIState
    data class Error(val message: String): UIState
    data class Done(val users: Users): UIState
}