package com.example.myapplication.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.common.Result
import com.example.myapplication.data.remote.repository.AuthRepository
import com.example.myapplication.data.remote.response.LoginResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    private val _state = MutableLiveData<Result<LoginResponse>>()
    val state : LiveData<Result<LoginResponse>> = _state

    fun login(email: String, password: String) {
        authRepository.login(email, password).onEach { result ->
            when(result) {
                is Result.Loading -> _state.value = Result.Loading
                is Result.Success -> { result.data.let { _state.value = Result.Success(it) } }
                is Result.Error -> { _state.value = Result.Error(result.message.toString()) }
            }
        }.launchIn(viewModelScope)
    }
}