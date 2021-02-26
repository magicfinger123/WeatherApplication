package ng.com.cwg.weatherapplication.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ng.com.cwg.weatherapplication.R
import ng.com.cwg.weatherapplication.view.fragment.FavoriteListBottomSheet

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.decorView.visibility = View.INVISIBLE
        actionBar?.hide()
        val navView: BottomNavigationView =
            findViewById(R.id.customBottomBar)
        val navController = findNavController(R.id.nav_host_fragment_container)
        navView.setupWithNavController(navController)
    }
    fun viewFavorites(){
        FavoriteListBottomSheet().apply(fun FavoriteListBottomSheet.() {
            show(supportFragmentManager, tag)
        })
    }
}