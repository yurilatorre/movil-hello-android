package com.latorre.helloandroid.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.latorre.helloandroid.R
import com.latorre.helloandroid.data.task.TaskRepository

class TaskListFragment : Fragment() {

    private lateinit var recyclerTasks: RecyclerView
    private lateinit var btnAdd: Button
    private lateinit var adapter: TaskAdapter
    private lateinit var repository: TaskRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_task_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        recyclerTasks = view.findViewById(R.id.recyclerTasks)
        btnAdd = view.findViewById(R.id.btnAdd)

        repository = TaskRepository(requireContext())

        recyclerTasks.layoutManager = LinearLayoutManager(requireContext())

        adapter = TaskAdapter(ArrayList(repository.getAllTasks())) { task ->

            val bundle = Bundle()
            bundle.putInt("taskId", task.id)

            findNavController().navigate(R.id.taskDetailFragment, bundle)
        }

        recyclerTasks.adapter = adapter

        btnAdd.setOnClickListener {
            findNavController().navigate(R.id.taskDetailFragment)
        }
    }

    override fun onResume() {
        super.onResume()

        adapter = TaskAdapter(ArrayList(repository.getAllTasks())) { task ->

            val bundle = Bundle()
            bundle.putInt("taskId", task.id)

            findNavController().navigate(R.id.taskDetailFragment, bundle)
        }

        recyclerTasks.adapter = adapter
    }
}