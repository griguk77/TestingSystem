package ru.studyguk.testingsystem.presentation

import android.os.Bundle
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

class QuestionFragment : Fragment() {
    private val vm: MainViewModel by activityViewModels { MainViewModelFactory(requireActivity().application) }
    private lateinit var binding: FragmentQuestionBinding

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
            if (vm.queCount.value == vm.question.value?.queNum) {
                vm.testName.value?.let { it1 ->
                    vm.userName.value?.let { it2 ->
                        vm.pointsResult.value?.let { it3 -> Math.round(it3).toInt() }?.let { it4 ->
                            vm.getTextResult(it1, it4, it2)
                        }
                    }
                }
                findNavController().navigate(R.id.action_questionFragment_to_resultFragment)
            } else {
                vm.testName.value?.let { it1 ->
                    vm.getQuestion(
                        it1,
                        (vm.question.value?.queNum?.plus(1) ?: 1)
                    )
                }
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }
}