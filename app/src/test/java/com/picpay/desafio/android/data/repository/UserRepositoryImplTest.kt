package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.local.UsersLocalDataSource
import com.picpay.desafio.android.data.mappers.UserMapper
import com.picpay.desafio.android.data.models.UserEntity
import com.picpay.desafio.android.data.models.UserResponse
import com.picpay.desafio.android.data.network.UsersRemoteDataSource
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.fixtures.UserFixture
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import java.io.IOException
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.fail

class UserRepositoryImplTest : UserFixture {

    private lateinit var sut: UserRepositoryImpl
    private lateinit var remoteDataSource: UsersRemoteDataSource
    private lateinit var localDataSource: UsersLocalDataSource
    private val userMapper: UserMapper = UserMapper()
    private var result: Flow<List<User>> = emptyFlow()

    @Test
    fun `getUsers should emit local users when data is available and cache is valid`() = runTest {
        givenAUserRepository(
            localUsers = locallyPersistedUsers(timestamp = System.currentTimeMillis()),
        )
        whenGettingUsers()
        thenRepositoryShouldReturnAFlowWithADomainUserList()
        verify(localDataSource).getAllUsers()
        verifyNoMoreInteractions(remoteDataSource)
    }

    @Test
    fun `getUsers should emit remote users when data is available but cache is invalid`() =
        runTest {
            givenAUserRepository(
                localUsers = locallyPersistedUsers(),
                remoteUsers = remoteUsers()
            )
            whenGettingUsers()
            thenRepositoryShouldReturnAFlowWithADomainUserList()
            verify(localDataSource).getAllUsers()
            verify(remoteDataSource).getUsers()
            verify(localDataSource).saveUsers(any())
        }

    @Test
    fun `getUsers should fetch and cache users when local data is empty`() = runTest {

        givenAUserRepository(remoteUsers = remoteUsers())

        whenGettingUsers()

        thenRepositoryShouldReturnAFlowWithADomainUserList()
        verify(localDataSource).getAllUsers()
        verify(remoteDataSource).getUsers()
        verify(localDataSource).saveUsers(any())
    }

    @Test
    fun `fetchAndCacheUsers should save fetched users`() = runTest {
        givenAUserRepository(remoteUsers = remoteUsers())

        whenFetchingAndCachingUsers()

        // Then
        thenRepositoryShouldReturnAFlowWithADomainUserList()
        verify(remoteDataSource).getUsers()
        verify(localDataSource).saveUsers(any())
    }

    @Test
    fun `fetchAndCacheUsers should handle errors from remoteDataSource`() = runTest {

        val exception = IOException("Simulated error")

        givenAUserRepository(fetchError = exception)

        assertFailsWith<IOException> {
            whenFetchingAndCachingUsers()
        }

        // Collecting the flow is needed to trigger the inner calls
        result.collect { }

        // Then

        verify(remoteDataSource).getUsers()
        verifyNoMoreInteractions(localDataSource)
    }

    private fun givenAUserRepository(
        localUsers: List<UserEntity>? = null,
        remoteUsers: List<UserResponse>? = null,
        fetchError: Throwable? = null,
    ) {
        localDataSource = mock {
            on { getAllUsers() } doReturn flow { emit(localUsers.orEmpty()) }
        }

        remoteDataSource = if (fetchError == null) mock {
            onBlocking { getUsers() } doReturn remoteUsers.orEmpty()
        } else {
            mock {
                onBlocking { getUsers() } doAnswer  {
                    throw fetchError
                }
            }
        }
        sut = UserRepositoryImpl(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource,
            userMapper = userMapper,
        )

    }

    private fun whenGettingUsers() {
        result = sut.getUsers()
    }

    private suspend fun whenFetchingAndCachingUsers() {
        sut.fetchAndCacheUsers()
    }

    private suspend fun thenRepositoryShouldReturnAFlowWithADomainUserList() {
        result.catch {
            fail("Flow should correctly emmit")
        }.collect {
            assertEquals(domainUserList(), it)
        }
    }
}
