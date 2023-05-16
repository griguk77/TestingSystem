package ru.studyguk.testingsystem.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import data.models.Test
import ru.studyguk.testingsystem.R
import ru.studyguk.testingsystem.databinding.FragmentCatalogBinding
import ru.studyguk.testingsystem.presentation.adapter.TestAdapter
import ru.studyguk.testingsystem.presentation.viewmodel.MainViewModel
import ru.studyguk.testingsystem.presentation.viewmodel.MainViewModelFactory

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CatalogFragment : Fragment(), TestAdapter.OnItemClickListener {
    private val vm: MainViewModel by activityViewModels{ MainViewModelFactory(requireActivity().application) }
    private var tests: List<String> = listOf()
    private lateinit var binding: FragmentCatalogBinding
    private var param1: String? = null
    private var param2: String? = null

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
            binding.textViewHelloUser.text = "Здравствуйте, $it!"
        }
        vm.showCatalog()
        vm.catalog.observe(viewLifecycleOwner) {
            if (it != null) {
                Log.d("RRR", "!null = $it")
                val listAdapter = TestAdapter(it, this)
                binding.recyclerViewTests.layoutManager = LinearLayoutManager(requireContext())
                binding.recyclerViewTests.adapter = listAdapter
            } else {
               Log.d("RRR", "null = $it")
            }
        }
//        val adapter = TestAdapter(vm.catalog.value!!, this)
//        binding.recyclerViewTests.layoutManager = LinearLayoutManager(this.context)
//        binding.recyclerViewTests.adapter = adapter
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onItemClickListener(testName: String) {
        findNavController().navigate(R.id.action_catalogFragment_to_testStartFragment)
    }
}