package com.coderipper.hsma.usecases.hotels.filter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.coderipper.hsma.R
import com.coderipper.hsma.databinding.FragmentHotelFiltersBinding
import com.coderipper.hsma.databinding.FragmentHotelsBinding
import com.google.android.material.sidesheet.SideSheetDialog

/**
 * A simple [Fragment] subclass.
 * Use the [HotelFiltersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HotelFiltersFragment : Fragment() {

    private var _binding: FragmentHotelFiltersBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var sideSheetDialog: SideSheetDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sideSheetDialog = SideSheetDialog(requireContext())
        sideSheetDialog!!.setContentView(R.layout.fragment_hotel_filters)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHotelFiltersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {

        }
    }

    fun show() {
        sideSheetDialog?.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment AvatarsSheetFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = HotelFiltersFragment()
    }
}