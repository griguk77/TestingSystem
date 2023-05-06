package ru.studyguk.testingsystem.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import data.models.Result
import ru.studyguk.testingsystem.databinding.FragmentAllResultsBinding
import ru.studyguk.testingsystem.presentation.adapter.ResultAdapter

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AllResultsFragment : Fragment() {
    private var results: ArrayList<Result> = ArrayList()
    private lateinit var binding: FragmentAllResultsBinding
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (results.size == 0) {
            results.add(Result(1, 2, "sdfhjhd", 73, "22.02.2023"))
            results.add(Result(2, 2, "ersg345", 81, "23.03.2023"))
            results.add(Result(3, 2, "esrfhj348rywe", 57, "05.04.2023"))
        }
        val adapter = ResultAdapter(results)
        binding = FragmentAllResultsBinding.inflate(layoutInflater)
        binding.recyclerViewResults.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerViewResults.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}