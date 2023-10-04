package com.example.myapplication.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.common.Result
import com.example.myapplication.common.pref.DataStorePreference
import com.example.myapplication.data.remote.repository.AuthRepository
import com.example.myapplication.data.remote.response.LoginResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val pref: DataStorePreference
) : ViewModel() {

    private val _state = MutableLiveData<Result<LoginResponse>>()
    val state : LiveData<Result<LoginResponse>> = _state

    val getToken = pref.getToken()
    val getName = pref.getName()
    val getUsername = pref.getUsername()
    val getEmail = pref.getEmail()

    fun login(email: String, password: String) {
        authRepository.login(email, password).onEach { result ->
            when(result) {
                is Result.Loading -> _state.value = Result.Loading
                is Result.Success -> result.data.let {
                    pref.saveToken(it.token)
                    pref.saveName(it.user.name)
                    pref.saveEmail(it.user.email)
                    pref.saveUsername(it.user.username)
                    _state.value = Result.Success(it)
                }
                is Result.Error -> { _state.value = Result.Error(result.message.toString()) }
            }
        }.launchIn(viewModelScope)
    }
}