package com.mazbah.springBatchExample.batch

import com.mazbah.springBatchExample.model.User
import com.mazbah.springBatchExample.repository.UserRepository
import com.mazbah.springBatchExample.service.UserService
import org.springframework.stereotype.Component
import org.springframework.batch.item.ItemWriter
import java.lang.Exception


@Component
class DBWriter(private var userService: UserService): ItemWriter<User> {

    @Throws(Exception::class)
    override fun write(users: List<User>) {
        println("Data Saved for Users: $users")
        userService.save(users)
    }
}