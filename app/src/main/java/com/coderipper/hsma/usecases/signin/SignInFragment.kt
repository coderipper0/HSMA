package com.coderipper.hsma.usecases.signin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.coderipper.hsma.databinding.FragmentSigninBinding
import com.coderipper.hsma.utils.createAvatarsModal

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SignInFragment : Fragment() {

    private var _binding: FragmentSigninBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var avatarsDialog: AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSigninBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            avatarBtn.setOnClickListener {
                avatarsDialog = createAvatarsModal(requireContext(), ::selectedAvatar)
            }
        }
    }

    private fun selectedAvatar(imgId: Int) {
        binding.avatarSelected.setImageResource(imgId)
        avatarsDialog.dismiss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}