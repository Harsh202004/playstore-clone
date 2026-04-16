package com.example.playstoreclone.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.playstoreclone.data.model.AppModel
import com.example.playstoreclone.data.model.ReviewModel
import com.example.playstoreclone.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppDetailViewModel @Inject constructor(
    private val appRepository: AppRepository
) : ViewModel() {

    private val _appDetail = MutableStateFlow<AppModel?>(null)
    val appDetail: StateFlow<AppModel?> = _appDetail.asStateFlow()

    private val _reviews = MutableStateFlow<List<ReviewModel>>(emptyList())
    val reviews: StateFlow<List<ReviewModel>> = _reviews.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun loadAppDetail(appId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            appRepository.getAppById(appId).collect {
                _appDetail.value = it
            }
            appRepository.getReviewsByAppId(appId).collect {
                _reviews.value = it
            }
            _isLoading.value = false
        }
    }

    fun addReview(review: ReviewModel) {
        viewModelScope.launch {
            appRepository.addReview(review)
        }
    }
}

