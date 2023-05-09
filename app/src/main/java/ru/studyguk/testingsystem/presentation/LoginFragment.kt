package ru.studyguk.testingsystem.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ru.studyguk.testingsystem.R
import ru.studyguk.testingsystem.databinding.FragmentLoginBinding
import ru.studyguk.testingsystem.presentation.viewmodel.MainViewModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class LoginFragment : Fragment() {
    private lateinit var vm: MainViewModel
    private lateinit var binding: FragmentLoginBinding
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        vm = ViewModelProvider(this).get(MainViewModel::class.java)
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
            findNavController().navigate(R.id.action_loginFragment_to_catalogFragment)
        }
        super.onViewCreated(view, savedInstanceState)
    }
}