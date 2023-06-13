package com.example.gofit.activity.mo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.gofit.R
import com.example.gofit.fragment.mo.AkunMoFragment
import com.example.gofit.fragment.mo.HomeMoFragment
import com.example.gofit.fragment.mo.PresensiInstrukturFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainMoActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_mo_activity)
        getSupportActionBar()?.hide()

        setUpBottonNav()
    }

    fun setUpBottonNav() {
        changeFragment(HomeMoFragment())
        bottomNavigationView = findViewById(R.id.nav_mo) as BottomNavigationView
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    changeFragment(HomeMoFragment())
                    true
                }
                R.id.navigation_presensi_instruktur -> {
                    changeFragment(PresensiInstrukturFragment())
                    true
                }
                R.id.navigation_akun_mo -> {
                    changeFragment(AkunMoFragment())
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