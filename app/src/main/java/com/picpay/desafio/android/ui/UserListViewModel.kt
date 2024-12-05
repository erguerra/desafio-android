package com.picpay.desafio.android.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.domain.actions.GetUsers
import com.picpay.desafio.android.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getUsers: GetUsers,
) : ViewModel() {

    private val _viewState =  MutableStateFlow<ViewState>(ViewState.Loading)
    val viewState: StateFlow<ViewState> = _viewState

    fun fetchUsers() {
        viewModelScope.launch {
            _viewState.value = ViewState.Loading
            getUsers().onSuccess {  userList ->
                handleSuccess(userList)
            }.onFailure {
                handleFailure(it)
            }
        }
    }

    private fun handleSuccess(userList: List<User>) {
        _viewState.value = ViewState.Success(userList)
    }

    private fun handleFailure(error: Throwable) {
        _viewState.value = ViewState.Retry
    }

    sealed interface ViewState {
        data object Loading : ViewState
        data object Retry: ViewState
        data class Success(val userList: List<User>) : ViewState
    }
}