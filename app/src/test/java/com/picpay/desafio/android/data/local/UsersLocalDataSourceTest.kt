package com.picpay.desafio.android.data.local

import com.picpay.desafio.android.data.models.UserEntity
import com.picpay.desafio.android.fixtures.UserFixture
import com.picpay.desafio.android.rules.MainDispatcherRule
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import kotlin.test.Test
import kotlin.test.assertEquals

class UsersLocalDataSourceTest : UserFixture {

    @get:Rule
    val testCoroutineRule = MainDispatcherRule()

    private lateinit var userDao: UserDao

    private lateinit var sut: UsersLocalDataSource
    private var result: Flow<List<UserEntity>> = emptyFlow()

    @Test
    fun `getAllUsers should return users from dao`() = runTest {

        givenALocalUserDataSource(locallyPersistedUsers())
        whenGettingUsersFromDao()
        result.collect {
            assertEquals(locallyPersistedUsers(), it)
        }

        // Then
        verify(userDao).getAllUsers()
    }

    @Test
    fun `saveUsers should insert users into dao`() = runTest {
        givenALocalUserDataSource(emptyList())
        whenSavingUsersIntoTheDatabase(locallyPersistedUsers())

        // Then
        verify(userDao).insert(locallyPersistedUsers())
    }

    private fun givenALocalUserDataSource(
        dbContent: List<UserEntity>,
        roomError: Throwable? = null,
    ) {
        userDao = mock {
            on { getAllUsers() } doReturn flow {
                if (roomError == null) {
                    emit(dbContent)
                } else {
                    throw roomError
                }
            }
        }

        sut = UsersLocalDataSource(
            usersDao = userDao,
            dispatcher = testCoroutineRule.testDispatcher,
        )
    }

    private fun whenGettingUsersFromDao() {
        result = sut.getAllUsers()
    }

    private suspend fun whenSavingUsersIntoTheDatabase(userList: List<UserEntity>) {
        sut.saveUsers(userList)
    }
}