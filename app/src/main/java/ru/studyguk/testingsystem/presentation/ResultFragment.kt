package ru.studyguk.testingsystem.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import ru.studyguk.testingsystem.R
import ru.studyguk.testingsystem.databinding.FragmentResultBinding
import ru.studyguk.testingsystem.presentation.viewmodel.MainViewModel
import ru.studyguk.testingsystem.presentation.viewmodel.MainViewModelFactory

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ResultFragment : Fragment() {
    private val vm: MainViewModel by activityViewModels{ MainViewModelFactory(requireActivity().application) }
    private lateinit var binding: FragmentResultBinding
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        vm.testName.observe(viewLifecycleOwner) {
            binding.textViewSubjectNameResult.text = it
        }
        vm.pointsResult.observe(viewLifecycleOwner) {
            val result = "Результат теста: ${Math.round(it).toInt()}"
            binding.textViewResult.text = result
        }
        vm.textResult.observe(viewLifecycleOwner) {
            binding.textViewResultText.text = vm.textResult.value
        }
        binding.buttonShowResultsResult.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_allResultsFragment)
        }
        binding.buttonToCatalog.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_catalogFragment)
        }
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_resultFragment_to_catalogFragment)
            }
        }
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, callback)
        super.onViewCreated(view, savedInstanceState)
    }
}