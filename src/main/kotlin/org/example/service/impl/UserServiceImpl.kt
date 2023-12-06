package org.example.service.impl

import org.example.model.User
import org.example.repository.UserRepository
import org.example.service.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {

    override fun getUserById(userId: Long): User? = userRepository.getById(userId)

    override fun addUser(user: User) {
        val newUser = userRepository.save(user)
    }
}
