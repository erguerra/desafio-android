package com.picpay.desafio.android.ui.userList.compose

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.ui.userList.UserListViewModel

class UserListContentPreviewProvider : PreviewParameterProvider<UserListViewModel.ViewState> {

    override val values: Sequence<UserListViewModel.ViewState>
        get() = sequenceOf(success, loading, retry)

    private val success = UserListViewModel.ViewState.Success(
        listOf(
            User(
                id = 1,
                name = "Sandrine Spinka",
                img = "https://randomuser.me/api/portraits/men/1.jpg",
                username = "Tod86",
            ),
            User(
                id = 2,
                name = "Carli Carroll",
                img = "https://randomuser.me/api/portraits/men/2.jpg",
                username = "Constantin_Sawayn",
            ),
            User(
                id = 3,
                name = "Annabelle Reilly",
                img = "https://randomuser.me/api/portraits/men/3.jpg",
                username = "Lawrence_Nader62",
            ),
            User(
                id = 4,
                name = "Mrs. Hilton Welch",
                img = "https://randomuser.me/api/portraits/men/4.jpg",
                username = "Tatyana_Ullrich",
            ),
            User(
                id = 5,
                name = "Ms. Simeon Yost",
                img = "https://randomuser.me/api/portraits/men/5.jpg",
                username = "Yasmine_Von5",
            ),
            User(
                id = 1,
                name = "Sandrine Spinka",
                img = "https://randomuser.me/api/portraits/men/1.jpg",
                username = "Tod86",
            ),
            User(
                id = 2,
                name = "Carli Carroll",
                img = "https://randomuser.me/api/portraits/men/2.jpg",
                username = "Constantin_Sawayn",
            ),
            User(
                id = 3,
                name = "Annabelle Reilly",
                img = "https://randomuser.me/api/portraits/men/3.jpg",
                username = "Lawrence_Nader62",
            ),
            User(
                id = 4,
                name = "Mrs. Hilton Welch",
                img = "https://randomuser.me/api/portraits/men/4.jpg",
                username = "Tatyana_Ullrich",
            ),
            User(
                id = 5,
                name = "Ms. Simeon Yost",
                img = "https://randomuser.me/api/portraits/men/5.jpg",
                username = "Yasmine_Von5",
            ),
        )
    )

    private val loading = UserListViewModel.ViewState.Loading

    private val retry = UserListViewModel.ViewState.Retry
}