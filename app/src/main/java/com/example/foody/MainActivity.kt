package com.example.foody

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.foody.fragments.FavouritesFragment
import com.example.foody.fragments.JokeFragment
import com.example.foody.fragments.RecipesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private val recipesFragment = RecipesFragment()
    private val favouritesFragment = FavouritesFragment()
    private val jokeFragment = JokeFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Display the recipe screen on ap load
        replaceFragment(recipesFragment)

        //Get the bottom navigation
        var bottomNav: BottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav_view)

        //When a bottom nav bar item is selected
        bottomNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.bottom_nav_item_favourites -> {
                    // Respond to navigation item favourites click
                    replaceFragment(favouritesFragment)

                }
                R.id.bottom_nav_item_joke -> {
                    // Respond to navigation item joke click
                    replaceFragment(jokeFragment)

                }
                //sets frame to recipe fragment
                else -> replaceFragment(recipesFragment)
            }
            //Changes the color of icon and text on selected icon
            return@setOnItemSelectedListener true
        }


    }

    /**
     * @method replaceFragment
     * @description Switches out the the different fragments in the fragment frame
     * @param {Fragment} fragment The fragment to place in the frame
     * @return void
     */
    private fun replaceFragment(fragment: Fragment){
        if(fragment !=null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }
}