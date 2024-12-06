package com.picpay.desafio.android.data.mappers

import com.picpay.desafio.android.fixtures.UserFixture
import org.junit.Test
import kotlin.test.assertEquals

class UserMapperTest : UserFixture {

    private val sut = UserMapper()

    @Test
    fun `when converting from entity to domain should return a domain model`() {
        assertEquals(domainUser(), sut.toDomain(userEntity()))
    }

    @Test
    fun `when converting from response to entity should return an entity model`() {
        val entity = sut.toEntity(userResponse()).copy(
            timestamp = timeStamp(), // Needed to ensure the assertion not relying on the time
        )
        assertEquals(userEntity(), entity)
    }
}