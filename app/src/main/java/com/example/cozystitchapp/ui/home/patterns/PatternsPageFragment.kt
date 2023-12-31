package com.example.cozystitchapp.ui.home.patterns

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cozystitchapp.databinding.FragmentPatternsPageBinding
import com.example.cozystitchapp.ui.home.patterns.adapter.PatternAdapter
import com.example.cozystitchapp.ui.home.patterns.viewmodel.PatternsViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PatternsPageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */


class PatternsPageFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    private lateinit var _binding: FragmentPatternsPageBinding
    private val viewModel: PatternsViewModel by viewModels()
    private lateinit var patternRecyclerView: RecyclerView
    lateinit var adapter: PatternAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPatternsPageBinding.inflate(inflater, container, false)
        val view = _binding.root

        //initializing RecyclerView and Adapter
        patternRecyclerView = _binding.patternRecyclerView
        patternRecyclerView.layoutManager = LinearLayoutManager(context)
        patternRecyclerView.setHasFixedSize(true)
        adapter = PatternAdapter()
        patternRecyclerView.adapter = adapter

        //Fetch patterns
        viewModel.getPatterns()


        adapter.onItemCLick = {
            //DECLARE INTENT
            val intent = Intent(requireContext(), PatternDetailsActivity::class.java)
            intent.putExtra("Crochet Pattern", it)
            //NAVIGATE TO DETAILS PAGE
            startActivity(intent)
        }

        // Inflate the layout for this fragment
        return view
    }

        companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PatternsPageFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PatternsPageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observe the LiveData
        viewModel.getPatternListLiveData().observe (viewLifecycleOwner) { patterns ->
            adapter.updatePatternList(patterns)
        }

    }
}