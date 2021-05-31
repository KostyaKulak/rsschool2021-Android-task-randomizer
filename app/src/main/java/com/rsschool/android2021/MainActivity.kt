package com.rsschool.android2021

import com.rsschool.android2021.FirstFragment.Companion.newInstance
import com.rsschool.android2021.SecondFragment.Companion.newInstance
import androidx.appcompat.app.AppCompatActivity
import com.rsschool.android2021.OpenFragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.rsschool.android2021.R
import com.rsschool.android2021.FirstFragment
import com.rsschool.android2021.SecondFragment

class MainActivity : AppCompatActivity(), OpenFragment {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openFirstFragment(0)
    }

    override fun openFirstFragment(previousNumber: Int) {
        val firstFragment: Fragment = newInstance(previousNumber)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, firstFragment)
            .commit()
    }

    override fun openSecondFragment(min: Int, max: Int) {
        val secondFragment: Fragment = newInstance(min, max)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, secondFragment)
            .commit()
    }
}