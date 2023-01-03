package menu1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import brand.CartActivity
import chat.ChatFragment
import com.example.frippy_v_fin.R
import com.example.frippy_v_fin.login.MappAff
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.navigation.NavigationView
import fragment.HomeFragment
import fragment.SettingsFragment

class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout = findViewById(R.id.drawerLayout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val headerView: View = navView.getHeaderView(0)
        var user_name = headerView.findViewById<TextView>(R.id.user_name)
        var user_email = headerView.findViewById<TextView>(R.id.user_email)
        var user_id = headerView.findViewById<TextView>(R.id.user_id)


        val sharedPreferences = getSharedPreferences("FRIPPY", MODE_PRIVATE)
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        user_name.text = sharedPreferences.getString("name", "Error Occured")
        user_email.text = sharedPreferences.getString("email", "Error Occured")

        replaceFragment(HomeFragment(), title.toString())
        navView.setNavigationItemSelectedListener {
            it.isChecked = true
            when (it.itemId) {
                R.id.nav_home -> replaceFragment(HomeFragment(), it.title.toString())
                R.id.nav_message ->  replaceFragment(ChatFragment(), it.title.toString())
                R.id.nav_sync -> Toast.makeText(
                    applicationContext,
                    "cliked synch",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.nav_trash -> navigateTopanier()
                R.id.nav_setting -> replaceFragment(SettingsFragment(), it.title.toString())
                R.id.nav_login -> onBackPressed()
                R.id.nav_share -> Toast.makeText(
                    applicationContext,
                    "cliked share",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.nav_rate_us -> navigateToMap()

            }
            true
        }

    }
    fun navigateToMap() {
        val intent = Intent(this,MappAff::class.java)

        startActivity(intent)
    }


    fun navigateTopanier() {
        val intent = Intent(this,CartActivity::class.java)

        startActivity(intent)
    }
    override fun onBackPressed() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Logout?")
            .setMessage("you are about to logout, are you sure?")
            .setPositiveButton("Yes") { dialog, which ->
                getSharedPreferences("FRIPPY", MODE_PRIVATE).edit().clear().apply()
                this.finish()
            }
            .setNegativeButton("Cancel") { dialog, which ->
                dialog.cancel()
            }.show()
    }

    private fun replaceFragment(fragment: Fragment, title: String) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
        drawerLayout.closeDrawers()
        setTitle(title)

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}