package com.picpay.desafio.android.ui.userList.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.picpay.desafio.android.R
import com.picpay.desafio.android.ui.theme.PicPayTheme
import com.picpay.desafio.android.ui.theme.PicPayTypography
import com.picpay.desafio.android.ui.userList.UserListViewModel

@Composable
fun UserListContent(
    modifier: Modifier = Modifier,
    viewState: UserListViewModel.ViewState,
    onRetry: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(vertical = 16.dp),
        contentAlignment = Alignment.TopCenter,
    ) {
        val lazyListState = rememberLazyListState()
        when (viewState) {
            is UserListViewModel.ViewState.Success -> LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                state = lazyListState,
            ) {
                item {
                    Text(
                        text = stringResource(R.string.title),
                        style = PicPayTypography.headlineLarge,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                    Spacer(modifier = Modifier.height(24.dp))

                }

                items(viewState.userList) { user ->
                    UserItem(
                        name = user.name,
                        username = user.username,
                        avatar = user.img,
                    )
                }
            }

            is UserListViewModel.ViewState.Loading -> CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(top = 8.dp, bottom = 8.dp),
                color = MaterialTheme.colorScheme.secondary,
            )

            is UserListViewModel.ViewState.Retry -> Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxSize()
                    .padding(24.dp),
            ) {
                Text(
                    text = stringResource(R.string.error),
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    onClick = onRetry,
                    colors = ButtonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        disabledContainerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        contentColor = MaterialTheme.colorScheme.secondary,
                        disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                ) {
                    Text(
                        text = stringResource(R.string.retry),
                        style = MaterialTheme.typography.headlineSmall,
                        maxLines = 1,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                }
            }
        }
    }
}


@Composable
@Preview
fun UserListContentPreview(
    @PreviewParameter(UserListContentPreviewProvider::class) viewState: UserListViewModel.ViewState,
) {
    PicPayTheme {
        UserListContent(
            viewState = viewState,
            onRetry = { }
        )
    }
}