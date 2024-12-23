package com.picpay.desafio.android.data.network

import com.picpay.desafio.android.data.models.UserResponse
import com.picpay.desafio.android.fixtures.UserFixture
import com.picpay.desafio.android.rules.MainDispatcherRule
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import java.io.IOException
import kotlin.test.Test
import kotlin.test.assertEquals

class UsersRemoteDataSourceTest : UserFixture {

    @get:Rule
    val testCoroutineDispatcher = MainDispatcherRule()

    private lateinit var sut: UsersRemoteDataSource
    private lateinit var picPayService: PicPayService
    private var result: Result<List<UserResponse>>? = null

    @Test
    fun `getUsers should call picPayService and emit users list`() = runTest {
        givenARemoteDataSource(response = remoteUsers())
        whenFetchingUsers()
        assertEquals(Result.success(remoteUsers()), result)
        verify(picPayService).getUsers()
    }

    @Test
    fun `getUsers should handle empty list correctly`() = runTest {

        givenARemoteDataSource(response = emptyList())
        whenFetchingUsers()

        assertEquals(Result.success(emptyList()), result)
        verify(picPayService).getUsers()
    }

    @Test
    fun `getUsers should handle errors from picPayService`() = runTest {
        val exception = IOException("Network error")
        givenARemoteDataSource(serviceError = exception)
        whenFetchingUsers()
        assertEquals(Result.failure(exception), result)
        verify(picPayService).getUsers()
    }

    private fun givenARemoteDataSource(
        serviceError: Throwable? = null,
        response: List<UserResponse> = remoteUsers(),
    ) {
        picPayService = if (serviceError == null) {
            mock {
                onBlocking { getUsers() } doReturn response
            }
        } else {
            mock {
                onBlocking { getUsers() } doAnswer {
                    throw serviceError
                }
            }
        }

        sut = UsersRemoteDataSource(
            picPayService = picPayService,
            dispatcher = testCoroutineDispatcher.testDispatcher
        )

    }

    private suspend fun whenFetchingUsers() {
        result = kotlin.runCatching {
            sut.getUsers()
        }
    }
}