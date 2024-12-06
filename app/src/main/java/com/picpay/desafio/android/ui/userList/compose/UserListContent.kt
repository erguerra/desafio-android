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
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.room.util.TableInfo
import com.picpay.desafio.android.R
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.ui.theme.PicPayTheme
import com.picpay.desafio.android.ui.theme.PicPayTypography
import com.picpay.desafio.android.ui.theme.colorAccent
import com.picpay.desafio.android.ui.theme.colorPrimaryDark
import com.picpay.desafio.android.ui.userList.UserListViewModel

@Composable
fun UserListContent(
    modifier: Modifier = Modifier,
    viewState: UserListViewModel.ViewState,
    refresh: () -> Unit = {},
    retry: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(colorPrimaryDark)
            .padding(vertical = 16.dp),
        contentAlignment = Alignment.TopCenter,
    ) {
        when (viewState) {
            is UserListViewModel.ViewState.Success -> LazyColumn(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 24.dp),
            ) {
                item {
                    Text(
                        text = stringResource(R.string.title),
                        style = PicPayTypography.headlineLarge,
                        color = Color.White,
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
                color = colorAccent,
            )

            is UserListViewModel.ViewState.Retry -> Column {

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
        )
    }
}