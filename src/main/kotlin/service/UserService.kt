package service

import model.User

interface UserService {

    fun addUser(user: User): User

}