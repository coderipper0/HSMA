package com.coderipper.hsma.usecases.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.coderipper.hsma.R
import com.coderipper.hsma.databinding.FragmentMainBinding
import com.coderipper.hsma.usecases.home.HomeViewModel
import com.coderipper.hsma.utils.clearPreferences
import com.coderipper.hsma.utils.linearInterpolation

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var maxHeight = 1
    private var minHeight = 0

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
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

                val directions = when (menuItem.itemId) {
                    R.id.home -> R.id.to_home
                    R.id.hotels -> {
                        val bundle = bundleOf("section" to 0)
                        findNavController().navigate(R.id.main_to_hotels, bundle)
                        null
                    }
                    R.id.packages -> {
                        val bundle = bundleOf("section" to 1)
                        findNavController().navigate(R.id.main_to_hotels, bundle)
                        null
                    }
                    R.id.activities -> {
                        val bundle = bundleOf("section" to 2)
                        findNavController().navigate(R.id.main_to_hotels, bundle)
                        null
                    }
                    R.id.details -> R.id.to_account
                    R.id.reservations -> R.id.to_reservations
                    R.id.report -> null
                    R.id.settings -> null
                    R.id.logout -> {
                        findNavController().navigate(R.id.main_to_login)
                        clearPreferences(requireActivity())
                        null
                    }
                    R.id.help -> null
                    else -> null
                }

                if (directions != null) {
                    val navMainFragment = childFragmentManager.findFragmentById(R.id.nav_main_fragment_content_main) as NavHostFragment
                    navMainFragment.navController.navigate(directions)
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