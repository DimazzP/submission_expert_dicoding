package com.example.submission_navigation.feature.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.data.models.ResultUser
import com.example.core.domain.entity.DetailUser
import com.example.core.domain.entity.Favorite
import com.example.core.domain.usecase.DeleteFavoriteUseCase
import com.example.core.domain.usecase.GetFavoriteByIdUseCase
import com.example.core.domain.usecase.GetFavoritesUseCase
import com.example.core.domain.usecase.GetUserDetailUseCase
import com.example.core.domain.usecase.InsertFavoriteUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getUserDetailUseCase: GetUserDetailUseCase,
    private val insertFavoriteUseCase: InsertFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    private val getFavoriteByIdUseCase: GetFavoriteByIdUseCase,
) : ViewModel() {

    private val _userData = MutableLiveData<DetailUser>()
    val userData: LiveData<DetailUser> = _userData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getFavoriteById(username: String): LiveData<Favorite?> {
        return getFavoriteByIdUseCase.execute(username).asLiveData()
    }

    fun insertFavorite() {
        _insertResult.value = ResultUser.Loading
        viewModelScope.launch {
            val result = insertFavoriteUseCase.execute(Favorite(userData.value?.login.toString(), userData.value?.avatarUrl))
            _insertStatus.value = result
        }
    }

    fun deleteFavorite() {
        _deleteResult.value = ResultUser.Loading
        viewModelScope.launch {
            val result = deleteFavoriteUseCase.execute(Favorite(userData.value?.login.toString(), userData.value?.avatarUrl))
            _deleteStatus.value = result
        }
    }

    fun findUser(username: String) {
        _isLoading.value = true
        viewModelScope.launch {
            getUserDetailUseCase(username).collect { result ->
                _isLoading.value = false
                result.fold(
                    onSuccess = { user ->
                        _userData.value = user
                    },
                    onFailure = { error ->
                        // Handle error
                    }
                )
            }
        }
    }

    private val _insertResult = MutableLiveData<ResultUser<Unit>>()
    private val _deleteResult = MutableLiveData<ResultUser<Unit>>()
    private val _insertStatus = MutableLiveData<ResultUser<Unit>?>()
    private val _deleteStatus = MutableLiveData<ResultUser<Unit>?>()
    val insertStatus: MutableLiveData<ResultUser<Unit>?>
        get() = _insertStatus
    val deleteStatus: LiveData<ResultUser<Unit>?>
        get() = _deleteStatus

    fun clear(){
        _insertStatus.value = null
        _deleteStatus.value = null
    }

    companion object {
        private const val TAG = "DetailViewModel"
    }
}