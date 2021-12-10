package com.example.foody

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.fragment.app.Fragment
import com.example.foody.fragments.FavouritesFragment
import com.example.foody.fragments.JokeFragment
import com.example.foody.fragments.RecipesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private val recipesFragment = RecipesFragment()
    private val favouritesFragment = FavouritesFragment()
    private val jokeFragment = JokeFragment()
    private var actionBar: Menu?? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //on initial app load, display the recipe screen
        replaceFragment(recipesFragment)


        //Get the bottom navigation and set a click listener
        var bottomNav: BottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        bottomNavClickListener(bottomNav,recipesFragment,favouritesFragment,jokeFragment)

    }


    /**
     * @method onCreateOptionsMenu
     * @description initializes actionbar
     * @param {Menu} menu The menu of the action bar
     * @return Boolean
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        actionBar = menu
        actionBar?.clear()
        setTitle("Recipes")
        menuInflater.inflate(R.menu.recipes_menu, menu)
        return true

    }

    /**
     * @method bottomNavClickListener
     * @description Sets the appropriate fragment in the fragment frame based on
     * button that has been clicked
     * @param {BottomNavigationView} bottomNavBar The bottom navigation view
     * @param {Fragment} recipesFragment  The recipe Fragment object
     * @param {Fragment} favouriteFragment  The favourites Fragment object
     * @param {Fragment} jokeFragment  The joke Fragment object
     * @return {Boolean} true if item has been selected (highlights selected item)
     */
    private fun bottomNavClickListener(
        bottomNavBar:BottomNavigationView,
        recipesFragment: RecipesFragment,
        favouritesFragment: FavouritesFragment,
        jokeFragment: JokeFragment
    ){
        //When a bottom nav bar item is selected
        bottomNavBar.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.bottom_nav_item_favourites -> {
                    // Respond to navigation item favourites click
                    replaceFragment(favouritesFragment)
                    setTitle("Favourites")
                    actionBar?.clear()
                    menuInflater.inflate(R.menu.favourites_menu, actionBar)


                }
                R.id.bottom_nav_item_joke -> {
                    // Respond to navigation item joke click
                    replaceFragment(jokeFragment)
                    setTitle("Joke")
                    actionBar?.clear()
                    menuInflater.inflate(R.menu.joke_menu, actionBar)

                }
                //sets frame to recipe fragment
                else -> {
                    replaceFragment(recipesFragment)
                    setTitle("Recipes")
                    actionBar?.clear()
                    menuInflater.inflate(R.menu.recipes_menu, actionBar)
                }
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