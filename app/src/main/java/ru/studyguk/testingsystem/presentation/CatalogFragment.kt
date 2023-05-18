package ru.studyguk.testingsystem.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import data.models.Test
import ru.studyguk.testingsystem.R
import ru.studyguk.testingsystem.databinding.FragmentCatalogBinding
import ru.studyguk.testingsystem.presentation.adapter.TestAdapter
import ru.studyguk.testingsystem.presentation.viewmodel.MainViewModel
import ru.studyguk.testingsystem.presentation.viewmodel.MainViewModelFactory

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CatalogFragment : Fragment(), TestAdapter.OnItemClickListener {
    private val vm: MainViewModel by activityViewModels { MainViewModelFactory(requireActivity().application, requireActivity()) }
    private lateinit var binding: FragmentCatalogBinding
    private var param1: String? = null
    private var param2: String? = null
    private var backPressedTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCatalogBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        vm.userName.observe(viewLifecycleOwner) {
            val greeting = "Здравствуйте, $it!"
            binding.textViewHelloUser.text = greeting
        }
        vm.showCatalog()
        vm.catalog.observe(viewLifecycleOwner) {
            val listAdapter = TestAdapter(it, this)
            binding.recyclerViewTests.layoutManager = LinearLayoutManager(requireContext())
            binding.recyclerViewTests.adapter = listAdapter
        }
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (backPressedTime + 2000 > System.currentTimeMillis()) {
                    FirebaseAuth.getInstance().signOut()
                    Snackbar.make(
                        view,
                        "Вы вышли из аккаунта",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    findNavController().navigate(R.id.action_catalogFragment_to_loginFragment)
                } else {
                    Snackbar.make(
                        view,
                        "Для выхода нажмите ещё раз",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                backPressedTime = System.currentTimeMillis()
            }
        }
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, callback)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onItemClickListener(testName: String) {
        vm.saveTestName(testName)
        findNavController().navigate(R.id.action_catalogFragment_to_testStartFragment)
    }
}