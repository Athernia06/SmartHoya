package com.example.myapplication.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.common.Result
import com.example.myapplication.data.remote.repository.HoyaRepository
import com.example.myapplication.data.remote.response.HoyaResponse
import com.example.myapplication.data.remote.response.IslandResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val hoyaRepository: HoyaRepository) : ViewModel() {

    private val _stateIsland = MutableStateFlow<Result<List<IslandResponse>>>(Result.Loading)
    val stateIsland : StateFlow<Result<List<IslandResponse>>> = _stateIsland

    private val _stateHoya = MutableStateFlow<Result<List<HoyaResponse>>>(Result.Loading)
    val stateHoya : StateFlow<Result<List<HoyaResponse>>> = _stateHoya

    fun listIsland(token: String) {
        viewModelScope.launch {
            hoyaRepository.listIsland(token).collect { result ->
                when(result) {
                    is Result.Loading -> _stateIsland.value = Result.Loading
                    is Result.Success -> result.data.let {
                        _stateIsland.value = Result.Success(it)
                    }
                    is Result.Error -> { _stateIsland.value = Result.Error(result.message.toString()) }
                }
            }
        }

    }

    fun listHoya(token: String, islandId: String) {
        viewModelScope.launch {
            hoyaRepository.listHoya(token, islandId).collect { result ->
                when(result) {
                    is Result.Loading -> _stateHoya.value = Result.Loading
                    is Result.Success -> result.data.let {
                        _stateHoya.value = Result.Success(it)
                    }
                    is Result.Error -> { _stateHoya.value = Result.Error(result.message.toString()) }
                }
            }
        }
    }
}