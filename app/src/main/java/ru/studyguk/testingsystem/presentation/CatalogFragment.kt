package ru.studyguk.testingsystem.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import data.models.Test
import ru.studyguk.testingsystem.R
import ru.studyguk.testingsystem.databinding.FragmentCatalogBinding
import ru.studyguk.testingsystem.presentation.adapter.TestAdapter
import ru.studyguk.testingsystem.presentation.viewmodel.MainViewModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CatalogFragment : Fragment(), TestAdapter.OnItemClickListener {
    private lateinit var vm: MainViewModel
    private var tests: ArrayList<String> = ArrayList()
    private lateinit var binding: FragmentCatalogBinding
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
        if (tests.size == 0) {
            tests.add("<u>Математика</u>")
            tests.add("<u>Русский язык</u>")
            tests.add("<u>История</u>")
            tests.add("<u>Литература</u>")
            tests.add("<u>Биология</u>")
            tests.add("<u>Политические координаты</u>")
        }
        val adapter = TestAdapter(tests, this)
        binding = FragmentCatalogBinding.inflate(layoutInflater)
        binding.recyclerViewTests.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerViewTests.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        vm.userName.observe(activity as LifecycleOwner) {
            binding.textViewHelloUser.text = "Здравствуйте, $it!"
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onItemClickListener(testName: String) {
        findNavController().navigate(R.id.action_catalogFragment_to_testStartFragment)
    }
}