package com.jdagnogo.lydiausers.repository.api

import com.jdagnogo.lydiausers.model.User
import com.jdagnogo.lydiausers.repository.api.model.UserResponse

class UserMapper() {
    fun toUser(userResponse: UserResponse): User {
        with(userResponse) {
            return User(
                id = idResponse?.value?.toLongOrNull()?:0L,
                first = name?.first ?: "",
                last = name?.last ?: "",
                email = email
            )
        }
    }
}