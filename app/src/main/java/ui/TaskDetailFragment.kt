package com.latorre.helloandroid.ui

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.latorre.helloandroid.R
import com.latorre.helloandroid.data.task.Task
import com.latorre.helloandroid.data.task.TaskRepository
import com.latorre.helloandroid.receiver.TaskReminderReceiver

class TaskDetailFragment : Fragment() {

    private var taskId: Int = -1
    private var currentTask: Task? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_task_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val etTitle = view.findViewById<EditText>(R.id.etTitle)
        val etDescription = view.findViewById<EditText>(R.id.etDescription)
        val tvDateTime = view.findViewById<TextView>(R.id.tvDateTime)
        val cbReminder = view.findViewById<CheckBox>(R.id.cbReminder)
        val btnSave = view.findViewById<Button>(R.id.btnSave)

        val repository = TaskRepository(requireContext())


        taskId = arguments?.getInt("taskId", -1) ?: -1


        if (taskId != -1) {
            currentTask = repository.getAllTasks().find { it.id == taskId }

            currentTask?.let {
                etTitle.setText(it.title)
                etDescription.setText(it.description)
                cbReminder.isChecked = it.hasReminder
            }
        }

        btnSave.setOnClickListener {

            if (taskId == -1) {


                val task = Task(
                    id = System.currentTimeMillis().toInt(),
                    title = etTitle.text.toString(),
                    description = etDescription.text.toString(),
                    hasReminder = cbReminder.isChecked
                )

                repository.addTask(task)

                if (cbReminder.isChecked) {
                    programarAlarma(task)
                }

            } else {


                val updatedTask = Task(
                    id = taskId,
                    title = etTitle.text.toString(),
                    description = etDescription.text.toString(),
                    hasReminder = cbReminder.isChecked
                )

                repository.updateTask(updatedTask)

            }

            Toast.makeText(requireContext(), "Tarea guardada", Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        }
    }

    private fun programarAlarma(task: Task) {

        val intent = Intent(requireContext(), TaskReminderReceiver::class.java)

        intent.putExtra("title", task.title)
        intent.putExtra("description", task.description)

        val pendingIntent = PendingIntent.getBroadcast(
            requireContext(),
            task.id,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val alarmManager =
            requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager

        alarmManager.set(
            AlarmManager.RTC_WAKEUP,
            System.currentTimeMillis() + 30000,
            pendingIntent
        )
    }
}