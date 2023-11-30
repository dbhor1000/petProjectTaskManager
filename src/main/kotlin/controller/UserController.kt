package controller

import model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import service.UserService
import java.net.URI

@RestController
@RequestMapping("/usersController")
class UserController(@Autowired private val userService: UserService) {

    @PostMapping("/addNew")
    fun addUser(@RequestBody user: User): ResponseEntity<User> {
        val newUser = userService.addUser(user)
        return ResponseEntity.created(URI.create("/users/${newUser.id}")).body(newUser)
    }

}