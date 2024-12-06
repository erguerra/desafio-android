package com.picpay.desafio.android.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.picpay.desafio.android.fixtures.UserEntityFixture
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertEquals


class UserDaoTest : UserEntityFixture {

    private lateinit var db: PicPayDatabase
    private lateinit var userDao: UserDao

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            PicPayDatabase::class.java,
        ).build()
        userDao = db.userDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertUsers() = runTest {

        userDao.insert(userEntityList())

        val usersFromDb = userDao.getAllUsers().first()
        assertEquals(10, usersFromDb.size)
        assertEquals(1, usersFromDb.first().id)

    }

    @Test
    fun testConflictRule() = runTest {
        val newNameForFirstUser = "Toninho Rodrigues"
        userDao.insert(userEntityList())
        userDao.insert(listOf(userEntityList().first().copy(name = newNameForFirstUser)))

        val usersFromDb = userDao.getAllUsers().first()
        assertEquals(10, usersFromDb.size)
        assertEquals(newNameForFirstUser, usersFromDb.first().name)
        assertEquals(1, usersFromDb.first().id)

    }

    @Test
    fun deleteUser() = runTest {
        userDao.insert(userEntityList())
        userDao.delete(userEntityList().first())

        val usersFromDb = userDao.getAllUsers().first()
        assertEquals(9, usersFromDb.size)
    }

    @Test
    fun getAllUsersEmpty() = runTest {
        val usersFromDb = userDao.getAllUsers().first()
        assertEquals(0, usersFromDb.size)
    }
}