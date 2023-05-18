package ru.studyguk.testingsystem.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import domain.models.User
import ru.studyguk.testingsystem.R
import ru.studyguk.testingsystem.databinding.FragmentLoginBinding
import ru.studyguk.testingsystem.presentation.viewmodel.MainViewModel
import ru.studyguk.testingsystem.presentation.viewmodel.MainViewModelFactory

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class LoginFragment : Fragment() {

    private val vm: MainViewModel by activityViewModels{ MainViewModelFactory(requireActivity().application, requireActivity()) }
    private lateinit var binding: FragmentLoginBinding
    private var auth = FirebaseAuth.getInstance()
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
                binding.progressBarLogin.visibility = View.GONE
                return@setOnClickListener
            }
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity()) { task ->
                    binding.progressBarLogin.visibility = View.GONE
                    if (task.isSuccessful) {
                        vm.login(email)
                        Snackbar.make(
                            view,
                            "Вход выполнен",
                            Snackbar.LENGTH_SHORT
                        ).show()
                        findNavController().navigate(R.id.action_loginFragment_to_catalogFragment)
                    } else {
                        Snackbar.make(
                            view,
                            "Вход не выполнен, проверьте введённые данные",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
//            binding.progressBarLogin.visibility = View.GONE
//            vm.success.observe(viewLifecycleOwner) {
//                if (it == true) {
//                    Snackbar.make(
//                        view,
//                        "Вход выполнен",
//                        Snackbar.LENGTH_SHORT
//                    ).show()
//                    findNavController().navigate(R.id.action_loginFragment_to_catalogFragment)
//                } else {
//                    Snackbar.make(
//                        view,
//                        "Вход не выполнен, проверьте введённые данные",
//                        Snackbar.LENGTH_SHORT
//                    ).show()
//                }
//            }
        }
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Snackbar.make(
                    view,
                    "Закройте приложение с помощью закрытия вкладки",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, callback)
        super.onViewCreated(view, savedInstanceState)
    }
}