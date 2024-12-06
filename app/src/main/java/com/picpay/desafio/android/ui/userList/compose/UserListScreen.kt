package com.picpay.desafio.android.ui.userList.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import com.picpay.desafio.android.ui.userList.UserListViewModel

@Composable
fun UserListScreen() {

    val viewModel: UserListViewModel = hiltViewModel()
    val state by viewModel.viewState.collectAsState()

    UserListContent(
        viewState = state,
    )

    EventHandler(viewModel)

    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
        viewModel.fetchUsers()
    }
}

@Composable
private fun EventHandler(
    viewModel: UserListViewModel,
) {
    // TODO: Handle refresh and retry
}