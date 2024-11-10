package com.amineaytac.englishdictionary.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.amineaytac.englishdictionary.databinding.FragmentOnBoardingBinding
import com.amineaytac.englishdictionary.ui.onboarding.screens.FirstFragment
import com.amineaytac.englishdictionary.ui.onboarding.screens.SecondFragment
import com.amineaytac.englishdictionary.ui.onboarding.screens.ThirdFragment

class OnBoardingFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentList = arrayListOf(
            FirstFragment(),
            SecondFragment(),
            ThirdFragment()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.viewPager.adapter = adapter
        binding.dotsIndicator.attachTo(binding.viewPager)
    }
}