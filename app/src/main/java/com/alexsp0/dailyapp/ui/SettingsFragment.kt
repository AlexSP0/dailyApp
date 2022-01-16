package com.alexsp0.dailyapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Switch
import com.alexsp0.dailyapp.R
import com.alexsp0.dailyapp.contracts.MainContract


class SettingsFragment(private val presenter: MainContract.MainPresenter) : Fragment() {
    private lateinit var use_dark_theme_switch : Switch
    private lateinit var ok_button : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        use_dark_theme_switch = view.findViewById(R.id.use_dark_theme_switch)
        ok_button = view.findViewById(R.id.settings_ok_button)
        initSwitch()
        ok_button.setOnClickListener {
            presenter.setTheme(use_dark_theme_switch.isChecked)
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        }
        return view
    }

    private fun initSwitch() {
        use_dark_theme_switch.isChecked = presenter.isThemeDark()
    }


    companion object {

        @JvmStatic
        fun newInstance(presenter : MainContract.MainPresenter) =
            SettingsFragment(presenter)
    }
}