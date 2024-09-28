package org.example.db

import org.example.dto.UserDto

object Database {
    val users = mutableListOf<UserDto>()

    fun createUser(userDto: UserDto): UserDto? {
        val user = readUser(userDto.id)
        if (user == null) users.add(userDto) else return user
        return null
    }

    fun readUser(id: Long): UserDto? = users.singleOrNull { it.id == id }

}