package com.alexsp0.dailyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.alexsp0.dailyapp.contracts.MainContract
import com.alexsp0.dailyapp.data.NasaImageResponse
import com.alexsp0.dailyapp.ui.MainFragment
import com.alexsp0.dailyapp.ui.SettingsFragment
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val app = application as App
        if(app.presenter.isThemeDark()) {
            setTheme(R.style.Dark)
        } else {
            setTheme(R.style.Light)
        }
        setContentView(R.layout.activity_main)
        loadFragment(MainFragment.newInstance(app.presenter))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_main_settings -> {
                val app = application as App
                loadFragment(SettingsFragment.newInstance(app.presenter))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun loadFragment(fragment : Fragment) {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, fragment)
            .addToBackStack(fragment.toString())
            .commit()
    }
    fun reloadAll() {
        var count = supportFragmentManager.backStackEntryCount
        if(count>0) {
            supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
        recreate()
    }
}