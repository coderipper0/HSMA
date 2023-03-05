package com.coderipper.hsma.usecases.signin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.coderipper.hsma.R
import com.coderipper.hsma.databinding.FragmentSignInBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            signInToolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }

            continueBtn.setOnClickListener {
                findNavController().navigate(R.id.sign_in_to_details)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}