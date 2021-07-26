package com.mazbah.springBatchExample.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name="new_user")
data class User(
    @Id @Column(name = "user_id") var id: Long? = null,
    @Column(name = "user_name") var name: String? = null,
    @Column(name = "department") var dept: String? = null,
    @Column(name = "salary") var salary: Int? = null,
    @Column(name = "time") var time: Date? = null
)