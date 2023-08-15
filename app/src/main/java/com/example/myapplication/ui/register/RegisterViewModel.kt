package com.example.myapplication.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.remote.repository.AuthRepository
import com.example.myapplication.data.remote.response.RegisterResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import com.example.myapplication.common.Result
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    private val _state = MutableLiveData<Result<RegisterResponse>>()
    val state : LiveData<Result<RegisterResponse>> = _state

    fun register(name: String, username: String, email: String, password: String, role: String) {
        authRepository.register(name, username, email, password, role).onEach { result ->
            when(result) {
                is Result.Loading -> _state.value = Result.Loading
                is Result.Success -> result.data.let { _state.value = Result.Success(it) }
                is Result.Error -> _state.value = Result.Error(result.message)
            }
        }.launchIn(viewModelScope)
    }
}