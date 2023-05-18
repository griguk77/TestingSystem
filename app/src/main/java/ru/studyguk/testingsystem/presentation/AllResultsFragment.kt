package ru.studyguk.testingsystem.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import domain.models.Result
import ru.studyguk.testingsystem.databinding.FragmentAllResultsBinding
import ru.studyguk.testingsystem.presentation.adapter.ResultAdapter
import ru.studyguk.testingsystem.presentation.viewmodel.MainViewModel
import ru.studyguk.testingsystem.presentation.viewmodel.MainViewModelFactory

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AllResultsFragment : Fragment() {
    private val vm: MainViewModel by activityViewModels { MainViewModelFactory(requireActivity().application) }
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
        binding = FragmentAllResultsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        vm.testName.observe(viewLifecycleOwner) {
            val test = "Все результаты теста \"$it\""
            binding.textViewAllResults.text = test
            vm.getAllResults(it)
        }
        vm.results.observe(viewLifecycleOwner) {
            val adapter = ResultAdapter(it)
            binding.recyclerViewResults.layoutManager = LinearLayoutManager(this.context)
            binding.recyclerViewResults.adapter = adapter
        }

        super.onViewCreated(view, savedInstanceState)
    }
}