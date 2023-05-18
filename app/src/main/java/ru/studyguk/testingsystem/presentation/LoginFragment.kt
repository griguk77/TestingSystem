package ru.studyguk.testingsystem.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import common.distinctUntilChanged
import domain.models.User
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.launch
import ru.studyguk.testingsystem.R
import ru.studyguk.testingsystem.databinding.FragmentLoginBinding
import ru.studyguk.testingsystem.presentation.viewmodel.MainViewModel
import ru.studyguk.testingsystem.presentation.viewmodel.MainViewModelFactory

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class LoginFragment : Fragment() {

    private val vm: MainViewModel by activityViewModels{ MainViewModelFactory(requireActivity().application, requireActivity()) }
    private lateinit var binding: FragmentLoginBinding
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.textViewRegistrLink.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrFragment)
        }
        binding.buttonLoginLogin.setOnClickListener {
            binding.progressBarLogin.visibility = View.VISIBLE
            val email = binding.editTextTextEmailAddressLogin.text.toString()
            val password = binding.editTextTextPasswordLogin.text.toString()
            if (email.isEmpty() || password.isEmpty()) {
                Snackbar.make(
                    view,
                    "Заполните все поля ввода",
                    Snackbar.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            val user = User(
                email,
                password
            )
            vm.login(user)
            binding.progressBarLogin.visibility = View.GONE
        }
        lifecycleScope.launch {
            vm.success.takeWhile { isAdded }.collect {
                if (it) {
                    Snackbar.make(
                        view,
                        "Вход выполнен",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    Log.d("ttt", "привет")
                    findNavController().navigate(R.id.action_loginFragment_to_catalogFragment)
                } else {
                    Snackbar.make(
                        view,
                        "Вход не выполнен, проверьте введённые данные",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }
}