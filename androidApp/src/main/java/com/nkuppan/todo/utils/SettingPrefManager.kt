package com.nkuppan.todo.utils

import android.content.Context
import com.ancient.essentials.utils.SecuredPreferenceManager

object SettingPrefManager {

    fun initialize(aContext: Context) {
        SecuredPreferenceManager.initialize(aContext, SettingPrefKeys.PREF_NAME)
    }

    fun storeSelectedTaskGroup(aTaskGroupId: String) {
        SecuredPreferenceManager.storeValue(SettingPrefKeys.SELECTED_TASK_GROUP, aTaskGroupId)
    }

    fun getSelectedTaskGroup(): String {
        return SecuredPreferenceManager.getStringValue(SettingPrefKeys.SELECTED_TASK_GROUP) ?: ""
    }

    fun storeFilterOption(aFilterType: Int) {
        SecuredPreferenceManager.storeValue(SettingPrefKeys.SELECTED_FILTER, aFilterType)
    }

    fun getFilterType(): Int {
        return SecuredPreferenceManager.getIntValue(SettingPrefKeys.SELECTED_FILTER) ?: 1
    }
}

object SettingPrefKeys {

    const val PREF_NAME = "settings_pref"

    const val SELECTED_TASK_GROUP = "selected_task_group"
    const val SELECTED_FILTER = "selected_filter"
}