package com.example.gofit.activity.member

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.gofit.R
import com.example.gofit.fragment.member.AkunMemberFragment
import com.example.gofit.fragment.member.BookingGymFragment
import com.example.gofit.fragment.member.BookingKelasFragment
import com.example.gofit.fragment.member.HomeMemberFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainMemberActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_member)
        getSupportActionBar()?.hide()

        setUpBottonNav()
    }

    fun setUpBottonNav() {
        changeFragment(HomeMemberFragment())
        bottomNavigationView = findViewById(R.id.nav_member) as BottomNavigationView
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    changeFragment(HomeMemberFragment())
                    true
                }
                R.id.navigation_booking_kelas -> {
                    changeFragment(BookingKelasFragment())
                    true
                }
                R.id.navigation_booking_gym -> {
                    changeFragment(BookingGymFragment())
                    true
                }
                R.id.navigation_akun_member -> {
                    changeFragment(AkunMemberFragment())
                    true
                }
                else -> false
            }
        }
    }

    fun changeFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}