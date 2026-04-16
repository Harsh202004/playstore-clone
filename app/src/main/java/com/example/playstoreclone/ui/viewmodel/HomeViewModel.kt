package com.example.playstoreclone.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.playstoreclone.data.model.AppModel
import com.example.playstoreclone.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val appRepository: AppRepository
) : ViewModel() {

    private val _allApps = MutableStateFlow<List<AppModel>>(emptyList())
    val allApps: StateFlow<List<AppModel>> = _allApps.asStateFlow()

    private val _topRatedApps = MutableStateFlow<List<AppModel>>(emptyList())
    val topRatedApps: StateFlow<List<AppModel>> = _topRatedApps.asStateFlow()

    private val _searchResults = MutableStateFlow<List<AppModel>>(emptyList())
    val searchResults: StateFlow<List<AppModel>> = _searchResults.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadApps()
    }

    private fun loadApps() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                appRepository.loadMockData()
                appRepository.getAllApps().collect {
                    _allApps.value = it
                }
                appRepository.getTopRatedApps().collect {
                    _topRatedApps.value = it
                }
                _isLoading.value = false
            } catch (e: Exception) {
                _isLoading.value = false
            }
        }
    }

    fun searchApps(query: String) {
        if (query.isEmpty()) {
            _searchResults.value = emptyList()
            return
        }
        viewModelScope.launch {
            appRepository.searchApps(query).collect {
                _searchResults.value = it
            }
        }
    }

    fun getAppsByCategory(category: String) {
        viewModelScope.launch {
            appRepository.getAppsByCategory(category).collect {
                _allApps.value = it
            }
        }
    }
}

