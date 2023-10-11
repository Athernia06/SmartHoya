package com.example.myapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.common.Result
import com.example.myapplication.data.remote.repository.HoyaRepository
import com.example.myapplication.data.remote.response.IslandResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val hoyaRepository: HoyaRepository) : ViewModel() {

    private val _stateIsland = MutableLiveData<Result<List<IslandResponse>>>()
    val stateIsland : LiveData<Result<List<IslandResponse>>> = _stateIsland

    fun listIsland(token: String) {
        hoyaRepository.listIsland(token).onEach { result ->
            when(result) {
                is Result.Loading -> _stateIsland.value = Result.Loading
                is Result.Success -> result.data.let {
                    _stateIsland.value = Result.Success(it)
                }
                is Result.Error -> { _stateIsland.value = Result.Error(result.message.toString()) }
            }
        }.launchIn(viewModelScope)
    }
}