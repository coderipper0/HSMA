package com.coderipper.hsma.usecases.elements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.Pair
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.coderipper.hsma.R
import com.coderipper.hsma.databinding.FragmentElementsBinding
import com.coderipper.hsma.utils.displayDimens
import com.coderipper.hsma.utils.dpToPixels
import com.coderipper.hsma.utils.objectAnimation
import com.google.android.material.bottomsheet.BottomSheetBehavior
import java.text.NumberFormat
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [ElementsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ElementsFragment : Fragment() {

    private var _binding: FragmentElementsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var currentDates: Pair<Long, Long>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentElementsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.run {
            val section = arguments?.getInt("section")

            setSectionSearch(section)

            val searchBottomSheet = BottomSheetBehavior.from(searchBottomSheet)
            searchBottomSheet.isDraggable = false
            searchBottomSheet.state = BottomSheetBehavior.STATE_HIDDEN

            val format = NumberFormat.getCurrencyInstance()
            format.maximumFractionDigits = 0
            format.currency = Currency.getInstance("MXN")

            elementsSearchBar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }

            scrim.setOnClickListener {
                closeBackdrop()
            }

            filtersBar.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.filter -> {
                        scrim.isVisible = true
                        frontContainer.objectAnimation("translationY", 0F, backdropMotionPosition(), 300).start()
                    }
                }
                true
            }

            locationChip.setOnClickListener {
                searchSheetTitle.text = "Ubicación"
                searchBottomSheet.state = BottomSheetBehavior.STATE_EXPANDED
            }

            hotelChip.setOnClickListener {
                searchSheetTitle.text = "Hotel"
                searchBottomSheet.state = BottomSheetBehavior.STATE_EXPANDED
            }

            starsSlider.addOnChangeListener { _, value, _ ->
                val stars = value.toInt()
                starsText.text = "$stars"
            }

            priceSlider.setLabelFormatter { value: Float ->
                format.format(value.toDouble())
            }

            priceSlider.addOnChangeListener { rangeSlider, _, _ ->
                val values = rangeSlider.values
                val startPrice = format.format(values[0].toDouble())
                val endPrice = format.format(values[1].toDouble())
                priceText.text = "$startPrice - $endPrice"
            }

            closeBtn.setOnClickListener {
                searchBottomSheet.state = BottomSheetBehavior.STATE_HIDDEN
            }

//
//            datesInput.setOnClickListener {
//                val constraintsBuilder =
//                    CalendarConstraints.Builder()
//                        .setValidator(DateValidatorPointForward.now())
//
//                val dateRangePickerBuilder =
//                    MaterialDatePicker.Builder.dateRangePicker()
//                        .setTitleText("Selecciona las fechas")
//                        .setCalendarConstraints(constraintsBuilder.build())
//
//                currentDates?.let { dateRangePickerBuilder.setSelection(currentDates) }
//                val dateRangePicker = dateRangePickerBuilder.build()
//
//                dateRangePicker.addOnPositiveButtonClickListener {
//                    currentDates = it
//                    val firstDate = Date(it.first)
//                    val secondDate = Date(it.second)
//
//                    val dateFormat = SimpleDateFormat("MMM d", Locale.US)
//                    val startDate = dateFormat.format(firstDate)
//                    val endDate = dateFormat.format(secondDate)
//                    val dates = "$startDate - $endDate"
//                    datesInput.setText(dates)
//                }
//
//                dateRangePicker.show(parentFragmentManager, "Dates picker")
//          }
        }
    }

    private fun setSectionSearch(section: Int? = null) {
        binding.run {
            when (section) {
                0 -> {
                    hotelChip.isVisible = false
                    packagesCheck.isVisible = true
                    starsTitle.text = "Estrellas"
                    elementsSearchBar.hint = "Buscar hoteles"
                    elementsSearchView.hint = "Buscar hoteles"
                }
                1 -> {
                    hotelChip.isVisible = true
                    packagesCheck.isVisible = false
                    starsTitle.text = "Puntuación"
                    elementsSearchBar.hint = "Buscar paquetes"
                    elementsSearchView.hint = "Buscar paquetes"
                }
                2 -> {
                    hotelChip.isVisible = true
                    activitiesCheck.isVisible = false
                    packagesCheck.isVisible = false
                    checksDivider.isVisible = false
                    checksTitle.isVisible = false
                    starsTitle.text = "Puntuación"
                    elementsSearchBar.hint = "Buscar actividades"
                    elementsSearchView.hint = "Buscar actividades"
                }
            }
        }
    }

    private fun backdropMotionPosition(): Float {
        val dimens = displayDimens(requireContext())
        var endPosition = binding.backContainer.height.toFloat() - dpToPixels(requireContext(), 90F)
        val diff = dimens.heightPixels - endPosition
        if (diff < dpToPixels(requireContext(), 50F))
            endPosition = dimens.heightPixels - dpToPixels(requireContext(), 170F)
        return endPosition
    }

    private fun closeBackdrop(section: Int? = null) {
        binding.run {
            scrim.isVisible = false
            frontContainer.objectAnimation("translationY", backdropMotionPosition(), 0F, 300).start()

            setSectionSearch(section)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}