package com.example.myapplication.ui.forum

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.common.Result
import com.example.myapplication.data.remote.repository.HoyaRepository
import com.example.myapplication.data.remote.response.ForumResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForumViewModel @Inject constructor(private val hoyaRepository: HoyaRepository) : ViewModel() {

    private val _stateForum = MutableStateFlow<Result<List<ForumResponse>>>(Result.Loading)
    val stateForum : StateFlow<Result<List<ForumResponse>>> = _stateForum

    fun listForum(token: String) {
        viewModelScope.launch {
            hoyaRepository.listForum(token).collect { result ->
                when(result) {
                    is Result.Loading -> _stateForum.value = Result.Loading
                    is Result.Success -> result.data.let {
                        _stateForum.value = Result.Success(it)
                    }
                    is Result.Error -> { _stateForum.value = Result.Error(result.message.toString()) }
                }
            }
        }

    }
}