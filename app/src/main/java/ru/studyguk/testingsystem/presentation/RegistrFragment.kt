package ru.studyguk.testingsystem.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import domain.models.User
import ru.studyguk.testingsystem.R
import ru.studyguk.testingsystem.databinding.FragmentRegistrBinding
import ru.studyguk.testingsystem.presentation.viewmodel.MainViewModel
import ru.studyguk.testingsystem.presentation.viewmodel.MainViewModelFactory

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class RegistrFragment : Fragment() {

    private val vm: MainViewModel by activityViewModels{ MainViewModelFactory(requireActivity().application) }
    private lateinit var binding: FragmentRegistrBinding
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttonLoginRegistr.setOnClickListener {
            val user = User(
                binding.editTextTextEmailAddressRegistr.text.toString(),
                binding.editTextTextPasswordRegistr.text.toString()
            )
            val success = vm.registr(user)
            if (success) {
                Snackbar.make(
                    view,
                    "Пользователь ${vm.userName.value.toString()} успешно зарегистрирован",
                    Snackbar.LENGTH_SHORT
                ).show()
                findNavController().navigate(R.id.action_registrFragment_to_loginFragment)
            } else {
                Snackbar.make(
                    view,
                    "Регистрация не выполнена, проверьте введённые данные",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }
}