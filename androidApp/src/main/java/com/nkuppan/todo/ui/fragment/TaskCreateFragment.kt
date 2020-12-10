package com.nkuppan.todo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ancient.essentials.extentions.EventObserver
import com.ancient.essentials.extentions.autoCleared
import com.ancient.essentials.extentions.setupStringSnackbar
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.nkuppan.todo.R
import com.nkuppan.todo.databinding.FragmentTaskCreateDialogBinding
import com.nkuppan.todo.ui.viewmodel.TaskCreateViewModel

class TaskCreateFragment : BottomSheetDialogFragment() {

    private var dataBinding: FragmentTaskCreateDialogBinding by autoCleared()

    private var viewModel: TaskCreateViewModel by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_task_create_dialog, container, false)
        viewModel = ViewModelProvider(this).get(TaskCreateViewModel::class.java)
        dataBinding = FragmentTaskCreateDialogBinding.bind(view)
        dataBinding.viewModel = viewModel
        dataBinding.lifecycleOwner = this.viewLifecycleOwner
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.root.setupStringSnackbar(
            this,
            viewModel.snackBarMessage,
            Snackbar.LENGTH_SHORT
        )

        viewModel.selectDateTime.observe(viewLifecycleOwner, EventObserver {
            //TODO create time selection dialog
        })

        viewModel.success.observe(viewLifecycleOwner, EventObserver {
            dismiss()
        })
    }
}