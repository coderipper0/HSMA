package com.coderipper.hsma.usecases.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.coderipper.hsma.R
import com.coderipper.hsma.databinding.FragmentLoginBinding
import com.coderipper.hsma.utils.saveValue

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            loginToolbar.setNavigationOnClickListener {
                findNavController().navigate(R.id.login_to_start)
            }

            loginBtn.setOnClickListener {
                findNavController().navigate(R.id.login_to_main)

                val email = emailInput.text.toString().trim()
                val password = passwordInput.text.toString().trim()

                if (email.isNotEmpty() and password.isNotEmpty())
                    saveValue(requireActivity(), hashMapOf("email" to email, "password" to password))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}