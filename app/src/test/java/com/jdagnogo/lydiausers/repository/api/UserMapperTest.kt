package com.jdagnogo.lydiausers.repository.api

import com.jdagnogo.lydiausers.repository.api.model.LocationResponse
import com.jdagnogo.lydiausers.repository.api.model.NameResponse
import com.jdagnogo.lydiausers.repository.api.model.PictureResponse
import com.jdagnogo.lydiausers.repository.api.model.UserResponse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


class UserMapperTest {
    private lateinit var userMapper: UserMapper

    @Before
    public fun before() {
        userMapper = UserMapper()
    }

    //region toUser
    @Test
    fun `given an UserResponse to toUser should return an user with the same data`() {
        val userResponse = UserResponse(
            name = NameResponse("", FAKE_NAME, FAKE_LASTNAME),
            gender = FAKE_GENDER,
            email = FAKE_EMAIL,
            phone = FAKE_PHONE,
            picture = PictureResponse(large = FAKE_PIC),
            nationality = FAKE_NAT,
            locationResponse = LocationResponse(FAKE_STREET, FAKE_CITY, FAKE_STATE)
        )

        val result = userMapper.toUser(userResponse)

        with(userResponse) {
            assertTrue(result.email == email)
            assertTrue(result.phone == phone)
            assertTrue(result.gender == gender)
            assertTrue(result.nationality == nationality)
            assertTrue(result.image == picture?.large)
            assertTrue(result.location == "${locationResponse?.street} ${locationResponse?.state} ${locationResponse?.city}")
        }
    }

    @Test
    fun `given an UserResponse without image to toUser should return an user without image`() {
        val userResponse = UserResponse(
            name = NameResponse("", FAKE_NAME, FAKE_LASTNAME)
        )

        val result = userMapper.toUser(userResponse)

        assertTrue(result.image.isEmpty())
    }

    companion object {
        const val FAKE_NAME = "FAKE_NAME"
        private const val FAKE_PIC = "FAKE_PIC"
        private const val FAKE_LASTNAME = "FAKE_LASTNAME"
        private const val FAKE_GENDER = "FAKE_GENDER"
        private const val FAKE_EMAIL = "FAKE_EMAIL"
        private const val FAKE_PHONE = "FAKE_PHONE"
        private const val FAKE_NAT = "FAKE_NAT"
        private const val FAKE_STREET = "FAKE_STREET"
        private const val FAKE_CITY = "FAKE_CITY"
        private const val FAKE_STATE = "FAKE_STATE"
    }
}