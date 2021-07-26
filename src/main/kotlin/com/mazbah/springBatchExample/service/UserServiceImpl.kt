package com.mazbah.springBatchExample.service

import com.mazbah.springBatchExample.model.User
import com.mazbah.springBatchExample.repository.UserRepository
import org.springframework.stereotype.Service

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification

@Service("UserService")
class UserServiceImpl(private val userRepository: UserRepository): UserService {

    override fun findAll(): List<User> {
        return userRepository.findAll()
    }

    override fun findAll(paging: Pageable): Page<User> {
        return userRepository.findAll(paging)
    }

    override fun findAll(spec: Specification<User>, page: Pageable): Page<User> {
        return userRepository.findAll(spec, page)
    }

    override fun findById(id: Long): User? {
        val user = userRepository.findById(id)
        return user.orElse(null)
    }

    override fun delete(user: User) {
        userRepository.delete(user)
    }

    override fun save(user: List<User>): MutableList<User> {
        return userRepository.saveAll(user)
    }
}