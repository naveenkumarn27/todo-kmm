package com.nkuppan.todo.shared

import com.nkuppan.todo.db.MyDatabase
import com.nkuppan.todo.db.Task
import com.nkuppan.todo.db.TaskGroup
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class TaskRepository(databaseDriverFactoryFactory: DatabaseDriverFactory) {

    private val database = MyDatabase(databaseDriverFactoryFactory.createDriver())

    private val taskQuery = database.taskQueries
    private val subTaskQuery = database.subTaskQueries
    private val tagsQuery = database.tagsQueries
    private val taskAndTagsQuery = database.taskAndTagsQueries
    private val taskGroupQuery = database.taskGroupQueries

    init {
        CoroutineScope(Dispatchers.Default).launch {
            insertBasicGroups()
        }
    }

    private fun insertBasicGroups() {

        val taskGroupList = taskGroupQuery.findAllGroup().executeAsList()

        if (taskGroupList.isNullOrEmpty()) {
            insertTaskGroup(
                TaskGroup(
                    "1",
                    "My List",
                    CommonUtils.getDateTime().toDouble(),
                    CommonUtils.getDateTime().toDouble()
                )
            )
        }
    }

    fun clearDatabase() {
        taskQuery.transaction {
            taskQuery.removeAllTask()
            subTaskQuery.removeAllSubTask()
            tagsQuery.removeAllTags()
            taskAndTagsQuery.removeAll()
            taskGroupQuery.removeAll()
        }
    }

    fun getAllTasks(aGroupId: String): List<Task> {
        return taskQuery.findAllTask(aGroupId).executeAsList()
    }

    fun getPendingTask(aGroupId: String): List<Task> {
        return taskQuery.findPendingTask(aGroupId).executeAsList()
    }

    fun getCompletedTask(aGroupId: String): List<Task> {
        return taskQuery.findCompletedTask(aGroupId).executeAsList()
    }

    fun removeAllTasks(aTaskGroupId: String) {
        taskQuery.removeAllTasks(aTaskGroupId)
    }

    fun removeCompletedTask(aTaskGroupId: String) {
        taskQuery.removeCompletedTasks(aTaskGroupId)
    }

    fun findThisTask(aId: String): Task? {

        val task = taskQuery.findTaskById(aId).executeAsOneOrNull()

        if (task != null) {
            //TODO read tags and sub tasks
            //val tagsList = tagsDBQuery.findTagById().executeAsList();
        }

        return task
    }

    fun insertTask(aTask: Task) {
        taskQuery.insertTask(
            id = aTask.id,
            group_id = aTask.group_id,
            title = aTask.title,
            description = aTask.description,
            status = aTask.status,
            created_on = aTask.created_on,
            updated_on = aTask.updated_on
        )
    }

    fun findGroupById(aTaskGroupId: String): TaskGroup? {
        return taskGroupQuery.findGroupById(aTaskGroupId).executeAsOneOrNull()
    }

    fun findAllGroups(): List<TaskGroup> {
        return taskGroupQuery.findAllGroup().executeAsList()
    }

    fun removeThisGroup(aTaskGroupId: String) {
        taskGroupQuery.removeGroup(aTaskGroupId)
    }

    fun insertTaskGroup(aTaskGroup: TaskGroup) {
        taskGroupQuery.insertGroup(
            id = aTaskGroup.id,
            group_name = aTaskGroup.group_name,
            created_on = aTaskGroup.created_on,
            updated_on = aTaskGroup.updated_on
        )
    }
}