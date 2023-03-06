package com.coderipper.hsma.usecases.hotels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.coderipper.hsma.databinding.FragmentHotelsBinding
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*
import androidx.core.util.Pair

/**
 * A simple [Fragment] subclass.
 * Use the [HotelsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HotelsFragment : Fragment() {

    private var _binding: FragmentHotelsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var currentDates: Pair<Long, Long>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHotelsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            hotelsSearchBar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }

            datesInput.setOnClickListener {
                val constraintsBuilder =
                    CalendarConstraints.Builder()
                        .setValidator(DateValidatorPointForward.now())

                val dateRangePickerBuilder =
                    MaterialDatePicker.Builder.dateRangePicker()
                        .setTitleText("Selecciona las fechas")
                        .setCalendarConstraints(constraintsBuilder.build())

                currentDates?.let { dateRangePickerBuilder.setSelection(currentDates) }
                val dateRangePicker = dateRangePickerBuilder.build()

                dateRangePicker.addOnPositiveButtonClickListener {
                    currentDates = it
                    val firstDate = Date(it.first)
                    val secondDate = Date(it.second)

                    val dateFormat = SimpleDateFormat("MMM d", Locale.US)
                    val startDate = dateFormat.format(firstDate)
                    val endDate = dateFormat.format(secondDate)
                    val dates = "$startDate - $endDate"
                    datesInput.setText(dates)
                }

                dateRangePicker.show(parentFragmentManager, "Dates picker")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}