package com.example.cozystitchapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cozystitchapp.R
import com.example.cozystitchapp.databinding.FragmentHomePageBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomePageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomePageFragment : Fragment() {

    private lateinit var binding: FragmentHomePageBinding

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
    ): View? {
        binding = FragmentHomePageBinding.inflate(inflater, container, false)
        val view = binding.root

        //handling the counter
        binding.incrementCounterButton.setOnClickListener{
            increment()
        }
        binding.decrementCounterButton.setOnClickListener {
            decrement()
        }
        binding.resetCounterButton.setOnClickListener{
            binding.counterTextView.text = "00"
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
         * @return A new instance of fragment HomePageFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomePageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun increment(){
        val value = binding.counterTextView.text.toString()
        val valueInt = value.toInt()
        val valueToDisplay = valueInt + 1
        binding.counterTextView.text = valueToDisplay.toString()
    }
    fun decrement(){
        val value = binding.counterTextView.text.toString()
        val valueInt = value.toInt()
        val valueToDisplay = valueInt - 1
        binding.counterTextView.text = valueToDisplay.toString()
    }
}