package ru.studyguk.testingsystem.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import data.models.Test
import ru.studyguk.testingsystem.R
import ru.studyguk.testingsystem.databinding.FragmentCatalogBinding
import ru.studyguk.testingsystem.presentation.adapter.TestAdapter

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CatalogFragment : Fragment(), TestAdapter.OnItemClickListener {
    private var tests: ArrayList<Test> = ArrayList()
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
        if (tests.size == 0) {
            tests.add(Test(1, "<u>Математика</u>",""))
            tests.add(Test(2, "<u>Русский язык</u>",""))
            tests.add(Test(3, "<u>История</u>",""))
            tests.add(Test(4, "<u>Литература</u>",""))
            tests.add(Test(5, "<u>Биология</u>",""))
            tests.add(Test(6, "<u>Политические координаты</u>",""))
        }
        val adapter = TestAdapter(tests, this)
        binding = FragmentCatalogBinding.inflate(layoutInflater)
        binding.recyclerViewTests.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerViewTests.adapter = adapter

        return binding.root
    }

    override fun onItemClickListener(test: Test) {
        findNavController().navigate(R.id.action_catalogFragment_to_testStartFragment)
    }
}