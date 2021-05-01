package com.ptma

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.NavController.OnDestinationChangedListener
import androidx.navigation.NavDestination
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.snackbar.Snackbar
import com.ptma.databinding.ActivityMainBinding
import com.ptma.ui.login.LoginViewModel
import com.ptma.ui.util.getViewModel

class MainActivity : AppCompatActivity(), OnDestinationChangedListener {

    private lateinit var mAppBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginViewModel = getViewModel()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)
        mAppBarConfiguration = AppBarConfiguration.Builder(menuIconNeededDestinations)
            .setOpenableLayout(binding.drawerLayout)
            .build()

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration)
        NavigationUI.setupWithNavController(binding.navView, navController)
        navController.addOnDestinationChangedListener(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    fun onLogout(@Suppress("unused_parameter") item: MenuItem?) {
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        logout()
        Snackbar.make(binding.root, R.string.successful_logout, Snackbar.LENGTH_LONG).show()
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        val loginFragment = destination.id == controller.graph.startDestination
        supportActionBar?.setDisplayHomeAsUpEnabled(!loginFragment)
        if (!loginFragment && !loginViewModel.isUserLoggedIn) {
            logout()
            Snackbar.make(binding.root, R.string.sign_in_again, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun logout() {
        loginViewModel.logout()
        findNavController(this, R.id.nav_host_fragment).navigate(R.id.action_global_login)
    }

    private val menuIconNeededDestinations
        get() = setOf(
            R.id.nav_appointment_list,
            R.id.nav_workout_list,
            R.id.nav_logout
        )

}