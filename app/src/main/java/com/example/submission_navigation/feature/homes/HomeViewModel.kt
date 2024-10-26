package com.example.submission_navigation.feature.homes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.entity.SearchUser
import com.example.core.domain.usecase.GetUserListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserListUseCase: GetUserListUseCase
) : ViewModel() {
    private val _userData = MutableStateFlow<List<SearchUser>?>(null)
    val userData: Flow<List<SearchUser>?> = _userData.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: Flow<Boolean> = _isLoading.asStateFlow()

    private val _userName = MutableStateFlow("Budi")

    init {
        findUser()
    }

    fun setUserName(name: String) {
        _userName.value = name
    }

    fun findUser() {
        _isLoading.value = true
        viewModelScope.launch {
            getUserListUseCase(_userName.value).collect { result ->
                _isLoading.value = false
                result.fold(
                    onSuccess = { users ->
                        _userData.value = users
                    },
                    onFailure = { error ->
                        Log.e(TAG, "Error fetching users: ${error}")
                    }
                )
            }
        }
    }

    companion object {
        private const val TAG = "HomeViewModel"
    }
//    private val _userData = MutableLiveData<List<SearchUser>?>()
//    val userData: LiveData<List<SearchUser>?> = _userData
//    private val _isLoading = MutableLiveData<Boolean>()
//    val isLoading: LiveData<Boolean> = _isLoading
//    private val _userName = MutableLiveData("Budi")
//
//    init {
//        findUser()
//    }
//
//    fun setUserName(name: String) {
//        _userName.value = name
//    }
//    fun findUser() {
//        _isLoading.value = true
//        viewModelScope.launch {
//            getUserListUseCase(_userName.value.toString()).collect { result ->
//                _isLoading.value = false
//                result.fold(
//                    onSuccess = { users ->
//                        _userData.value = users
//                    },
//                    onFailure = { error ->
//                        Log.e(TAG, "Error fetching users: ${error.message}")
//                    }
//                )
//            }
//        }
//    }
//    fun findUser() {
//        _isLoading.value = true
//        val client = ApiConfig.getApiService().getListUsers(_userName.value.toString())
//        client.enqueue(object : Callback<ResponseUser> {
//            override fun onResponse(
//                call: Call<ResponseUser>,
//                response: Response<ResponseUser>
//            ) {
//                _isLoading.value = false
//                if (response.isSuccessful) {
//                    _userData.value = response.body()?.items
//                } else {
//                    Log.e(TAG, "onFailure: ${response.message()}")
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
//                _isLoading.value = false
//                Log.e(TAG, "onFailure: ${t.message.toString()}")
//            }
//        })
//    }
//    companion object {
//        private const val TAG = "HomeViewModel"
//    }
}