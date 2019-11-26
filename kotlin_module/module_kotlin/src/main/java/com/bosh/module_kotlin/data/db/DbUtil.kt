package com.bosh.module_kotlin.data.db

import android.content.Context
import com.bosh.module_kotlin.data.db.table.MyObjectBox
import com.bosh.module_kotlin.data.db.table.TeacherDb
import io.objectbox.Box
import io.objectbox.BoxStore

/**
 * @author lzq
 * @date  2019-11-21
 */
class DbUtil(private var boxStore: BoxStore) {
    val boxTeacher : Box<TeacherDb> = boxStore.boxFor(TeacherDb::class.java)

    fun insert(teacher: TeacherDb) {
        boxTeacher.put(teacher)
    }

    fun get(id: Long) {
        boxTeacher.get(id)
    }
}