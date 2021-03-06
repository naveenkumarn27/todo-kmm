package com.nkuppan.todo.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nkuppan.todo.R
import com.nkuppan.todo.ui.fragment.TaskEditFragment

class TaskEditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_base)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.place_holder, TaskEditFragment())
            .commitNow()
    }
}