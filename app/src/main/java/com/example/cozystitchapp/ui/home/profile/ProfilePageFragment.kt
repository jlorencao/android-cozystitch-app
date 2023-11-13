package com.example.cozystitchapp.ui.home.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cozystitchapp.databinding.FragmentProfilePageBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfilePageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfilePageFragment : Fragment() {


    private lateinit var _binding: FragmentProfilePageBinding

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
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentProfilePageBinding.inflate(inflater, container, false)
        val view = _binding.root

        _binding.userImageView.setOnClickListener {
            //open the camera for a photo

            //get the path of the image

            //save the path in the shared_preferences

            //put the photo as the source

        }

        //get the user name from database

        //get the user email from firebase auth

        _binding.personalDetailsButton.setOnClickListener {
            //open a pop up with user name, email, levelCrochet, country (offer to edit)

            //change password

        }
        _binding.logoutButton.setOnClickListener {
            //logout from firebase
            Firebase.auth.signOut()
            activity?.finish()
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfilePageFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfilePageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}