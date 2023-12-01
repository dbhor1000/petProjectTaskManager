package org.example.service.impl

import org.example.model.User
import org.example.repository.UserRepository
import org.example.service.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {

    override fun addUser(user: User): User = userRepository.save(user)

    override fun getUserById(userId: Long): User? = userRepository.getById(userId)
}
