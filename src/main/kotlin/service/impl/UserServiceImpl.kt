package service.impl

import model.User
import org.springframework.stereotype.Service
import repository.UserRepository
import service.UserService

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService{

    override fun addUser(user: User): User = userRepository.save(user)

}