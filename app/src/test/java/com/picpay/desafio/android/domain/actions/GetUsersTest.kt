package com.picpay.desafio.android.domain.actions

import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.repository.UserRepository
import com.picpay.desafio.android.fakes.FakeUserRepository
import com.picpay.desafio.android.fixtures.UserFixture
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import okio.IOException
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import kotlin.test.Test
import kotlin.test.assertEquals


class GetUsersTest : UserFixture {

    private lateinit var sut: GetUsers

    private lateinit var repository: UserRepository

    private var result: Flow<List<User>> = flowOf(emptyList())

    @Test
    fun `given a getUsers action a user list should be emitted in case of success`() = runTest {
        givenAGetUsersAction()
        whenCallingGetUsers()
        thenTheUserListIsEmitted()
    }

    @Test
    fun `given a getUsers action an error should be thrown in case of failure`() = runTest {
        givenAGetUsersActionWithFailure()
        whenCallingGetUsers()
        thenTheUserListIsNotEmitted()
    }


    private fun givenAGetUsersAction() {
        repository = FakeUserRepository()
        sut = GetUsers(repository)
    }

    private fun givenAGetUsersActionWithFailure() {
        repository = mock {
            on { getUsers() } doReturn flow {
                throw IOException()
            }
        }
        sut = GetUsers(repository)
    }

    private fun whenCallingGetUsers() {
        result = sut()
    }

    private suspend fun thenTheUserListIsEmitted() {
        assertEquals(domainUserList(), result.firstOrNull())
    }

    private fun thenTheUserListIsNotEmitted() {
        result.catch {
            assert(it is IOException)
        }
    }
}