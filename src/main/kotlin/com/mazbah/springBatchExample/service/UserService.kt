package com.mazbah.springBatchExample.service

import com.mazbah.springBatchExample.model.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification


interface UserService {
    fun findById(id:Long): User?
    fun findAll(): List<User>
    fun findAll(paging: Pageable): Page<User>
    fun findAll(spec: Specification<User>, page: Pageable): Page<User>
    fun delete(user: User)
    fun save(users: List<User>): MutableList<User>
}