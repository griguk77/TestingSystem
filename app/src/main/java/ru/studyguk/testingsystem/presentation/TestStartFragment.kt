package ru.studyguk.testingsystem.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import ru.studyguk.testingsystem.R
import ru.studyguk.testingsystem.databinding.FragmentTestStartBinding
import ru.studyguk.testingsystem.presentation.viewmodel.MainViewModel
import ru.studyguk.testingsystem.presentation.viewmodel.MainViewModelFactory


class TestStartFragment : Fragment() {
    private val vm: MainViewModel by activityViewModels { MainViewModelFactory(requireActivity().application) }
    lateinit var binding: FragmentTestStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTestStartBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        vm.testName.observe(viewLifecycleOwner) {
            binding.textViewSubjectNameStart.text = it
            vm.getDeclTest(it)
            vm.getQueCount(it)
        }
        vm.declaration.observe(viewLifecycleOwner) {
            binding.textViewSubjectDeclaration.text = it
        }
        binding.buttonShowResultsStart.setOnClickListener {
            findNavController().navigate(R.id.action_testStartFragment_to_allResultsFragment)
        }
        binding.buttonStartTest.setOnClickListener {
            vm.setQueNum(1)
            vm.setPoints(0.0)
            vm.testName.value?.let { it1 -> vm.getQuestion(it1, 1) }
            findNavController().navigate(R.id.action_testStartFragment_to_questionFragment)
        }
        super.onViewCreated(view, savedInstanceState)
    }
}