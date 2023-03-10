package com.coderipper.hsma.usecases.elements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.coderipper.hsma.databinding.FragmentReserveDetailsBinding
import com.coderipper.hsma.usecases.elements.dialog.DialogQuantityFragment

/**
 * A simple [Fragment] subclass.
 * Use the [ReserveDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReserveDetailsFragment : Fragment() {

    private var _binding: FragmentReserveDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReserveDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            quantityInput.setOnClickListener {
                val fragmentTransition = parentFragmentManager.beginTransaction()
                val prev = parentFragmentManager.findFragmentByTag("qty_dialog")
                if (prev != null) {
                    fragmentTransition.remove(prev)
                }
                fragmentTransition.addToBackStack(null)

                val newFragment = DialogQuantityFragment.newInstance()
                newFragment.show(fragmentTransition, "qty_dialog")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}