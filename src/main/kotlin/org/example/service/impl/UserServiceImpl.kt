package org.example.service.impl

import org.example.model.User
import org.springframework.stereotype.Service
import org.example.repository.UserRepository
import org.example.service.UserService

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {

    override fun addUser(user: User): User = userRepository.save(user)


}