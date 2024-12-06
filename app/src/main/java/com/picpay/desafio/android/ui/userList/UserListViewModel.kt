package com.picpay.desafio.android.ui.userList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.domain.actions.GetUsers
import com.picpay.desafio.android.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getUsers: GetUsers,
) : ViewModel() {

    private val _viewState = MutableStateFlow<ViewState>(ViewState.Loading)
    val viewState: StateFlow<ViewState> = _viewState

    fun fetchUsers() {
        viewModelScope.launch {
            getUsers().onStart {
                _viewState.value = ViewState.Loading
            }.catch {
                handleFailure()
            }.collect { userList ->
                handleSuccess(userList)
            }
        }
    }

    private fun handleSuccess(userList: List<User>) {
        _viewState.value = ViewState.Success(userList)
    }

    private fun handleFailure() {
        _viewState.value = ViewState.Retry
    }

    sealed interface ViewState {
        data object Loading : ViewState
        data object Retry : ViewState
        data class Success(val userList: List<User>) : ViewState
    }
}