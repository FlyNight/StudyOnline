package com.example.studyonline.activitys

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.*
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studyonline.R
import com.example.studyonline.data.adapter.ChatAdapter
import com.example.studyonline.ui.login.LoginActivity
import com.github.javiersantos.bottomdialogs.BottomDialog
import java.sql.Connection
import java.sql.DriverManager
import java.util.*
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
        fab.setOnClickListener {
            val view = getChatView()
            val dialog = BottomDialog.Builder(this)
                .setTitle("聊天窗口")
                .setNegativeText("关闭")
                .setPositiveText("新建聊天")
                .onNegative {
                    it.dismiss()
                }.onPositive {
                    it.dismiss()
                }
                .setCustomView(view)
                .build()
            dialog.show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
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

    @SuppressLint("InflateParams", "ResourceType")
    fun loginOrRegister(view: View) {
        val btn = view as Button
        if (btn.text.toString() == "登录/注册")
            startActivityForResult(Intent(this, LoginActivity::class.java), 1)
        else {
            val navView: NavigationView = findViewById(R.id.nav_view)
            val navHeaderRoot: View = navView.findViewById(R.id.nav_header_root)
            val userIdentity: TextView = navHeaderRoot.findViewById(R.id.user_identity)
            val userName: TextView = navHeaderRoot.findViewById(R.id.user_name)
            Companion.userName = "未登录"
            userId = null.toString()
            id = -1
            userName.text = "未登录"
            userIdentity.visibility = INVISIBLE
            btn.text = "登录/注册"
        }
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
                id = data.getIntExtra("id", -1)
                Companion.userIdentity = data.getStringExtra("userIdentity")!!
                userName.text = data.getStringExtra("userName")
                userIdentity.text = data.getStringExtra("userIdentity")
                userIdentity.visibility = VISIBLE
                btn.text = "注销"
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }


    private fun getChatView(): View {
        val root = LinearLayout(this)
        val chatContainer = RecyclerView(this)
        chatContainer.layoutManager = LinearLayoutManager(this)
        if (id == -1) {
            val text = TextView(this)
            text.text = "您尚未登陆，请登录后查看"
            text.gravity = Gravity.CENTER
            root.addView(text)
            return root
        }
        val adapter = ChatAdapter(this, id)
        if (adapter.itemCount == 0) {
            val text = TextView(this)
            text.text = "当前暂无消息"
            text.gravity = Gravity.CENTER
            root.addView(text)
            return root
        }
        chatContainer.adapter = adapter
        adapter.notifyDataSetChanged()
        root.addView(chatContainer)
        return root
    }

    companion object {
        lateinit var userName: String
        var userId: String = "-1"
        var id = -1
        lateinit var userIdentity: String
        lateinit var cn: Connection
        private fun mysqlConnect() {
            Class.forName("com.mysql.jdbc.Driver")
            val urlToDataBase1 =
                "jdbc:mysql://bj-cynosdbmysql-grp-4pz1e9su.sql.tencentcdb.com:22020/study_online"
            val username1 = "sujiulou"
            val password1 = "yan?545392961"
            val username2 = "root"
            val password2 = "545392"
            val urlToDataBase2 = "jdbc:mysql://82.156.194.22:3306/study_online"
            cn = DriverManager.getConnection(urlToDataBase2, username2, password2)
        }
    }
}