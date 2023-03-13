package com.coderipper.hsma.usecases.elements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.coderipper.hsma.R
import com.coderipper.hsma.databinding.DialogQuantityBinding
import com.coderipper.hsma.databinding.FragmentReserveDetailsBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


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
                createDialog()
            }
        }
    }

    private fun createDialog() {
        val dialogBinding = DialogQuantityBinding.inflate(LayoutInflater.from(context), null, false)

        val materialDialog = MaterialAlertDialogBuilder(requireContext())
        materialDialog.setView(dialogBinding.root)
        materialDialog.setTitle("Cantidad de huespedes")
        materialDialog.setPositiveButton("Ok") { dialog, which ->

        }

        dialogBinding.run {
            adultsDecrement.setOnClickListener {
                val qtyAdults = adultsQty.text.toString().toIntOrNull()

                if (qtyAdults != null) {
                    if (qtyAdults > 1) adultsQty.text = (qtyAdults - 1).toString()
                }
            }

            adultsIncrement.setOnClickListener {
                val qtyAdults = adultsQty.text.toString().toIntOrNull()

                if (qtyAdults != null) {
                    if (qtyAdults < 4) adultsQty.text = (qtyAdults + 1).toString()
                }
            }
        }

        materialDialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}