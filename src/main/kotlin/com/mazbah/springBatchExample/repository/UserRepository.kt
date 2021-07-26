package com.mazbah.springBatchExample.repository

import com.mazbah.springBatchExample.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface UserRepository: JpaRepository<User, Long>, JpaSpecificationExecutor<User>