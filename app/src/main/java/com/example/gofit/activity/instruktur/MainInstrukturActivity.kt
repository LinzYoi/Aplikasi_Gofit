package com.example.gofit.activity.instruktur

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.gofit.R
import com.example.gofit.fragment.instruktur.AkunInstrukturFragment
import com.example.gofit.fragment.instruktur.IzinInstrukturFragment
import com.example.gofit.fragment.instruktur.HomeInstrukturFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainInstrukturActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_instruktur)
        getSupportActionBar()?.hide()

        setUpBottonNav()
    }

    fun setUpBottonNav() {
        changeFragment(HomeInstrukturFragment())
        bottomNavigationView = findViewById(R.id.nav_instruktur) as BottomNavigationView
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    changeFragment(HomeInstrukturFragment())
                    true
                }
                R.id.navigation_izin_instruktur -> {
                    changeFragment(IzinInstrukturFragment())
                    true
                }
                R.id.navigation_akun_instruktur -> {
                    changeFragment(AkunInstrukturFragment())
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