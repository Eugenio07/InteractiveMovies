package com.example.interactivemovies

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView

fun setFragmentBars(
    activity: AppCompatActivity?,
    AppBarVisible: Boolean,
    bottomBarVisible: Boolean,
) {
    val appBar: AppBarLayout? = activity?.findViewById(R.id.appBarLayout)
    val navBar: BottomNavigationView? = activity?.findViewById(R.id.nav_view)

    if (appBar != null) {
        if (AppBarVisible) {
            appBar.visibility = View.VISIBLE
        } else {
            appBar.visibility = View.INVISIBLE
        }
    }

    if (bottomBarVisible) {
        navBar?.visibility = View.VISIBLE
    } else {
        navBar?.visibility = View.INVISIBLE
    }
}