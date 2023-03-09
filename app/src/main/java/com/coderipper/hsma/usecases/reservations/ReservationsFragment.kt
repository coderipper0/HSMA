package com.coderipper.hsma.usecases.reservations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.coderipper.hsma.R
import com.coderipper.hsma.databinding.FragmentHomeBinding
import com.coderipper.hsma.databinding.FragmentReservationsBinding

/**
 * A simple [Fragment] subclass.
 * Use the [ReservationsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReservationsFragment : Fragment() {

    private var _binding: FragmentReservationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentReservationsBinding.inflate(inflater, container, false)
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
}