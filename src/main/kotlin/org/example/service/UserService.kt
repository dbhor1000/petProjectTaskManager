package org.example.service

import org.example.model.User

interface UserService {

    fun addUser(user: User): User

    fun getUserById(userId: Long): User?
}
