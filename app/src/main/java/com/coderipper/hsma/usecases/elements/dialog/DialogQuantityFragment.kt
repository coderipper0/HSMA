package com.coderipper.hsma.usecases.elements.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.coderipper.hsma.databinding.FragmentDialogQuantityBinding
import com.coderipper.hsma.usecases.home.HomeFragment

/**
 * A simple [Fragment] subclass.
 * Use the [DialogQuantityFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DialogQuantityFragment : DialogFragment() {

    private var _binding: FragmentDialogQuantityBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDialogQuantityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = DialogQuantityFragment()
    }
}