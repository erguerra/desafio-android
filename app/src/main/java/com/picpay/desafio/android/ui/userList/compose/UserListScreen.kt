package com.picpay.desafio.android.ui.userList.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.picpay.desafio.android.ui.userList.UserListViewModel

@Composable
fun UserListScreen() {

    val viewModel: UserListViewModel = hiltViewModel()
    val state by viewModel.viewState.collectAsState()

    UserListContent(
        viewState = state,
        onRetry = viewModel::fetchUsers,
    )

    LaunchedEffect(viewModel) {
        viewModel.fetchUsers()
    }
}