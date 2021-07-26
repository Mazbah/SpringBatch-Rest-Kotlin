package com.mazbah.springBatchExample.batch

import com.mazbah.springBatchExample.model.User
import org.springframework.batch.item.ItemProcessor
import org.springframework.stereotype.Component
import java.lang.Exception
import java.util.*
import kotlin.collections.HashMap

@Component
class Processor: ItemProcessor<User, User> {

    val DEPT_NAMES: MutableMap<String, String> = HashMap()

    fun Processor() {
        DEPT_NAMES["001"] = "Technology"
        DEPT_NAMES["002"] = "Operations"
        DEPT_NAMES["003"] = "Accounts"
    }

    @Throws(Exception::class)
    override fun process(user: User): User {
        val deptCode: String = user.dept.toString()
        val dept = DEPT_NAMES[deptCode]
        user.dept = dept.toString()
        user.time = Date()
        println(String.format("Converted from [$deptCode] to [$dept]"))
        return user
    }
}