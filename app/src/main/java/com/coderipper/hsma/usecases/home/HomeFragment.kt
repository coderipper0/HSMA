package com.coderipper.hsma.usecases.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.drawerlayout.widget.DrawerLayout
import com.coderipper.hsma.R
import com.coderipper.hsma.databinding.FragmentHomeBinding
import com.coderipper.hsma.databinding.FragmentLoginBinding
import com.coderipper.hsma.utils.displayDimens
import com.coderipper.hsma.utils.linearInterpolation
import com.coderipper.hsma.utils.pixelsToDp
import com.coderipper.hsma.utils.startAnimatedIcon
import com.google.android.material.bottomsheet.BottomSheetBehavior

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var maxHeight = 1
    private var minHeight = 0

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            val displayMetrics = displayDimens(requireContext())
            val sheet = BottomSheetBehavior.from(homeSheet)

            homeImg.addOnLayoutChangeListener(object: View.OnLayoutChangeListener {
                override fun onLayoutChange(view: View?, p1: Int, p2: Int, p3: Int, p4: Int, p5: Int, p6: Int, p7: Int, p8: Int) {
                    maxHeight = homeImg.height
                    minHeight = (maxHeight / 3) + pixelsToDp(requireContext(), 35F).toInt()
                    homeSheet.layoutParams.height = displayMetrics.heightPixels - (homeImg.height / 3)
                    homeSheet.requestLayout()
                    sheet.peekHeight =
                        displayMetrics.heightPixels - (homeImg.height - pixelsToDp(requireContext(), 35F).toInt())
                    view?.removeOnLayoutChangeListener(this)
                }
            })

            val sheetCallback = object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {}
                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    val y = linearInterpolation(0F, 1F, 1F, 0F, slideOffset)
                    homeImg.layoutParams.height = (((maxHeight - minHeight) * y) + minHeight).toInt()
                    homeImg.requestLayout()
                }
            }

            sheet.addBottomSheetCallback(sheetCallback)

            homeToolbar.setNavigationOnClickListener { drawerLayout.open() }

            drawerLayout.addDrawerListener(object: DrawerLayout.DrawerListener {
                override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                    contentLayout.translationX = slideOffset * menuNav.width
                    val y = linearInterpolation(0F, 1F, 1F, 0.8F, slideOffset)
                    //contentLayout.scaleX = y
                    //contentLayout.scaleY = y
                }

                override fun onDrawerOpened(drawerView: View) {
//                    homeToolbar.setNavigationIcon(R.drawable.menu_to_close)
//                    startAnimatedIcon(homeToolbar.navigationIcon!!)
                }

                override fun onDrawerClosed(drawerView: View) {
//                    homeToolbar.setNavigationIcon(R.drawable.close_to_menu)
//                    startAnimatedIcon(homeToolbar.navigationIcon!!)
                }

                override fun onDrawerStateChanged(newState: Int) {}
            })

            menuNav.setNavigationItemSelectedListener { menuItem ->
                menuItem.isChecked = true
                drawerLayout.close()

                when (menuItem.itemId) {
//                    R.id.home -> {
//
//                    }
//                    R.id.settings -> {
//
//                    }
                }

                true
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}