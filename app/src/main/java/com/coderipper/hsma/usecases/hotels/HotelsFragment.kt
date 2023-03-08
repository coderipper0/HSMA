package com.coderipper.hsma.usecases.hotels

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.util.Pair
import androidx.core.view.isVisible
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.coderipper.hsma.R
import com.coderipper.hsma.databinding.FragmentHotelsBinding
import com.coderipper.hsma.utils.dpToPixels
import com.coderipper.hsma.utils.objectAnimation
import com.coderipper.hsma.utils.springAnimation
import com.google.android.material.bottomsheet.BottomSheetBehavior
import java.text.NumberFormat
import java.util.*


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
        binding.run {
            val section = arguments?.getInt("section")

            setSectionSearch(section)


            val filtersBottomSheet = BottomSheetBehavior.from(filtersBottomSheet)
            filtersBottomSheet.isDraggable = false
            filtersBottomSheet.state = BottomSheetBehavior.STATE_HIDDEN

            val format = NumberFormat.getCurrencyInstance()
            format.maximumFractionDigits = 0
            format.currency = Currency.getInstance("MXN")

            hotelsSearchBar.setNavigationOnClickListener {
                if (!scrim.isVisible) {
                    scrim.isVisible = true

                    val endPosition = backContainer.height.toFloat() - dpToPixels(requireContext(), 90F)
                    //frontContainer.springAnimation(DynamicAnimation.TRANSLATION_Y, finalPosition, 800F).start()
                    frontContainer.objectAnimation("translationY", 0F, endPosition, 300).start()

                    val drawable = ResourcesCompat.getDrawable(resources, R.drawable.arrow, null)
                    hotelsSearchBar.navigationIcon = drawable
                } else findNavController().popBackStack()
            }

            scrim.setOnClickListener {
                closeBackdrop()
            }

            hotelsBtn.setOnClickListener {
                closeBackdrop(0)
            }

            hotelsPackagesBtn.setOnClickListener {
                closeBackdrop(1)
            }

            hotelsActivitiesBtn.setOnClickListener {
                closeBackdrop(2)
            }

            filtersBar.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.filter -> {
                        filtersBottomSheet.state = BottomSheetBehavior.STATE_EXPANDED
                    }
                }
                true
            }

            closeBtn.setOnClickListener {
                filtersBottomSheet.state = BottomSheetBehavior.STATE_HIDDEN
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
                    packagesCheck.isVisible = true
                    starsTitle.text = "Estrellas"
                    hotelsSearchBar.hint = "Buscar hoteles"
                }
                1 -> {
                    packagesCheck.isVisible = false
                    starsTitle.text = "Puntuación"
                    hotelsSearchBar.hint = "Buscar paquetes"
                }
                2 -> {
                    activitiesCheck.isVisible = false
                    packagesCheck.isVisible = false
                    checksDivider.isVisible = false
                    checksTitle.isVisible = false
                    starsTitle.text = "Puntuación"
                    hotelsSearchBar.hint = "Buscar actividades"
                }
            }
        }
    }

    private fun closeBackdrop(section: Int? = null) {
        binding.run {
            scrim.isVisible = false
            val startPosition = backContainer.height.toFloat() - dpToPixels(requireContext(), 90F)
            frontContainer.objectAnimation("translationY", startPosition, 0F, 300).start()
            //frontContainer.springAnimation(DynamicAnimation.TRANSLATION_Y, 0F, 800F).start()

            setSectionSearch(section)

            val drawable = ResourcesCompat.getDrawable(resources, R.drawable.menu, null)
            hotelsSearchBar.navigationIcon = drawable
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}