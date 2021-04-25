package com.example.studyonline.activitys

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.studyonline.R
import com.example.studyonline.ui.login.LoginActivity
import java.sql.Connection
import java.sql.DriverManager
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val thread = Thread {
            mysqlConnect()
        }
        thread.start()
        thread.join()
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
        ), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    @SuppressLint("InflateParams")
    fun loginOrRegister(view: View) {
        startActivityForResult(Intent(this, LoginActivity::class.java), 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1) {
            val navView: NavigationView = findViewById(R.id.nav_view)
            val navHeaderRoot: View = navView.findViewById(R.id.nav_header_root)
            val userIdentity: TextView = navHeaderRoot.findViewById(R.id.user_identity)
            val userName: TextView = navHeaderRoot.findViewById(R.id.user_name)
            val btn: Button = findViewById(R.id.login_register)
            if (data != null) {
                Companion.userName = data.getStringExtra("userName")!!
                userId = data.getStringExtra("userId")!!
                id = data.getIntExtra("id",-1)
                Companion.userIdentity = data.getStringExtra("userIdentity")!!
                userName.text = data.getStringExtra("userName")
                userIdentity.text = data.getStringExtra("userIdentity")
                userIdentity.visibility = VISIBLE
                btn.text = "注销"
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    companion object {
        lateinit var userName: String
        var userId : String = "-1"
        var id by Delegates.notNull<Int>()
        lateinit var userIdentity: String
        lateinit var cn: Connection
        private fun mysqlConnect() {
            Class.forName("com.mysql.jdbc.Driver")
            cn = DriverManager.getConnection(
                "jdbc:mysql://bj-cynosdbmysql-grp-4pz1e9su.sql.tencentcdb.com:22020/study_online",
                "sujiulou", "yan?545392961"
            )
        }
    }
}