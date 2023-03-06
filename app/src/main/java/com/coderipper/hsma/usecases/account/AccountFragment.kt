package com.coderipper.hsma.usecases.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.coderipper.hsma.R
import com.coderipper.hsma.databinding.FragmentAccountBinding
import com.coderipper.hsma.databinding.FragmentLoginBinding
import com.coderipper.hsma.usecases.account.adapters.SectionsAdapter
import com.coderipper.hsma.usecases.account.address.AddressFragment
import com.coderipper.hsma.usecases.account.general.GeneralFragment
import com.coderipper.hsma.usecases.account.password.PasswordFragment
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass.
 * Use the [AccountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            val sectionsAdapter = SectionsAdapter(listOf(GeneralFragment(), PasswordFragment(), AddressFragment()), this@AccountFragment)
            sectionsPager.adapter = sectionsAdapter

            sectionsPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                    val id = when (position) {
                        0 -> R.id.general_btn
                        1 -> R.id.password_btn
                        2 -> R.id.address_btn
                        else -> R.id.general_btn
                    }
                    selectionGroup.check(id)
                }
            })

            generalBtn.setOnClickListener { sectionsPager.currentItem = 0 }
            passwordBtn.setOnClickListener { sectionsPager.currentItem = 1 }
            addressBtn.setOnClickListener { sectionsPager.currentItem = 2 }

            updateFab.setOnClickListener {
                Snackbar.make(binding.root, "Actualizado", Snackbar.LENGTH_LONG)
                    .setAnchorView(updateFab)
                    .setAction("Deshacer") {
                        // Responds to click on the action
                    }
                    .show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}