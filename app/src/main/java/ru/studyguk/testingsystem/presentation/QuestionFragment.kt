package ru.studyguk.testingsystem.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import ru.studyguk.testingsystem.R
import ru.studyguk.testingsystem.databinding.FragmentQuestionBinding
import ru.studyguk.testingsystem.presentation.viewmodel.MainViewModel
import ru.studyguk.testingsystem.presentation.viewmodel.MainViewModelFactory
import kotlin.math.floor

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class QuestionFragment : Fragment() {
    private val vm: MainViewModel by activityViewModels{ MainViewModelFactory(requireActivity().application) }
    private lateinit var binding: FragmentQuestionBinding
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuestionBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        vm.question.observe(viewLifecycleOwner) {
            binding.textViewSubjectNameQuest.text = vm.testName.value
            val text = "${it.queNum}/${vm.queCount.value}"
            binding.textViewProgress.text = text
            binding.textViewQuestDeclaration.text = it.queText
            binding.checkBox.text = it.variants[0]
            binding.checkBox2.text = it.variants[1]
            binding.checkBox3.text = it.variants[2]
            binding.checkBox4.text = it.variants[3]
            binding.checkBox.isChecked = false
            binding.checkBox2.isChecked = false
            binding.checkBox3.isChecked = false
            binding.checkBox4.isChecked = false
        }
        binding.buttonContinue.setOnClickListener {
            var pointResult = 0.0
            if (binding.checkBox.isChecked) {
                pointResult += vm.question.value?.points?.get(0) ?: 0.0
            }
            if (binding.checkBox2.isChecked) {
                pointResult += vm.question.value?.points?.get(1) ?: 0.0
            }
            if (binding.checkBox3.isChecked) {
                pointResult += vm.question.value?.points?.get(2) ?: 0.0
            }
            if (binding.checkBox4.isChecked) {
                pointResult += vm.question.value?.points?.get(3) ?: 0.0
            }
            vm.setPoints(pointResult + (vm.pointsResult.value ?: 0.0))
            //Log.d("RRR", "промежуточное = $pointResult")
            //Log.d("RRR", "итоговое = ${vm.pointsResult.value.toString()}")
            if (vm.queCount.value == vm.question.value?.queNum) {
//                vm.pointsResult.value?.times(10.0)?.let { it1 -> floor(it1.div(10.0)) }
//                    ?.let { it2 -> vm.setPoints(it2) }
                //Log.d("RRR", "конечное = ${vm.pointsResult.value.toString()}")
                findNavController().navigate(R.id.action_questionFragment_to_resultFragment)
            } else {
                vm.testName.value?.let { it1 -> vm.getQuestion(it1, (vm.question.value?.queNum?.plus(1) ?: 1)) }
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }
}