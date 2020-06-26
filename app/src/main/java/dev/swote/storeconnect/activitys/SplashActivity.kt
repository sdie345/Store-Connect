package dev.swote.storeconnect.activitys

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import dev.swote.storeconnect.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val permissions : Array<String>
                = arrayOf(
            Manifest.permission.READ_CONTACTS, Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.INTERNET, Manifest.permission.READ_EXTERNAL_STORAGE)
        Handler().postDelayed(
            {
                if (permissions.map { checkSelfPermission(it) }.all { it == PackageManager.PERMISSION_GRANTED }) {
                    Log.v("permission","Permission is granted")
                    // 로그인 창으로
                    startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                    finish()
                }else{
                    Log.v("permission","Permission is revoked")
                    ActivityCompat.requestPermissions(this, permissions, 1)
                }
            },
            1000 // value in milliseconds
        )
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
            // 로그인 창으로
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            finish()
        }
        else {
            Toast.makeText(this@SplashActivity, "필요한 권한이 승인되지 않았습니다.\n 설정 -> 앱에 들어가서 권한을 설정해주시길 바랍니다.", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
