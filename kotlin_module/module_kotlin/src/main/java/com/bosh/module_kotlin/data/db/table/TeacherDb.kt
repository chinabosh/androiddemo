package com.bosh.module_kotlin.data.db.table

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

/**
 * @author lzq
 * @date  2019-11-21
 */
@Entity data class TeacherDb(
        @Id var id: Long,
        var name: String,
        var role: String
)