package com.picpay.desafio.android.ui.userList

import app.cash.turbine.test
import com.picpay.desafio.android.domain.actions.GetUsers
import com.picpay.desafio.android.fixtures.UserFixture
import com.picpay.desafio.android.rules.MainDispatcherRule
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class UserListViewModelTest : UserFixture {

    @get:Rule
    val testCoroutineRule = MainDispatcherRule()

    private lateinit var viewModel: UserListViewModel
    private lateinit var getUsers: GetUsers

    @Test
    fun `fetchUsers should emit Loading and then Success when users are fetched successfully`() =
        runTest {
            givenAUserListViewModel()

            viewModel.viewState.test {
                // When
                viewModel.fetchUsers()

                // Then
                assertEquals(UserListViewModel.ViewState.Loading, awaitItem())
                assertEquals(
                    domainUserList(),
                    assertIs<UserListViewModel.ViewState.Success>(awaitItem()).userList
                )
                cancelAndIgnoreRemainingEvents()
            }
            verify(getUsers).invoke()
        }

    @Test
    fun `fetchUsers should emit Loading and then Retry when an error occurs`() = runTest {

        val exception = Exception("Network error")
        givenAUserListViewModel(getUsersError = exception)

        viewModel.viewState.test {
            // When
            viewModel.fetchUsers()

            // Then
            assertEquals(UserListViewModel.ViewState.Loading, awaitItem())
            assertEquals(UserListViewModel.ViewState.Retry, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
        verify(getUsers).invoke()
    }

    private fun givenAUserListViewModel(getUsersError: Throwable? = null) {
        getUsers = mock {
            on { invoke() } doReturn flow {
                if (getUsersError != null) {
                    throw getUsersError
                } else {
                    emit(domainUserList())
                }
            }
        }

        viewModel = UserListViewModel(getUsers)
    }

    @Test
    fun `fetchUsers should update state to Loading when fetch is started`() = runTest {
        givenAUserListViewModel()

        viewModel.viewState.test {
            // When
            viewModel.fetchUsers()

            // Then
            assertEquals(UserListViewModel.ViewState.Loading, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}