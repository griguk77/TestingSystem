package ru.studyguk.testingsystem.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
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
    private val vm: MainViewModel by activityViewModels { MainViewModelFactory(requireActivity().application, requireActivity()) }
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
            binding.progressBarRegistr.visibility = VISIBLE
            val email = binding.editTextTextEmailAddressRegistr.text.toString()
            val password = binding.editTextTextPasswordRegistr.text.toString()
            val repeatPassword = binding.editTextTextRepeatPassword.text.toString()
            if (email.isEmpty() || password.isEmpty() || repeatPassword.isEmpty()) {
                Snackbar.make(
                    view,
                    "Заполните все поля ввода",
                    Snackbar.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            if (repeatPassword != password) {
                Snackbar.make(
                    view,
                    "Пароли не совпадают, проверьте введённые данные",
                    Snackbar.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            val user = User(
                email,
                password
            )
            vm.registr(user)
            binding.progressBarRegistr.visibility = GONE
            if (vm.success.value == true) {
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