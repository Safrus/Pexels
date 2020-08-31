package kz.tinker.pexel.ui.main.view

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kz.tinker.pexel.R


class MainActivity : AppCompatActivity() {

    private val PERMISSION_REQUEST_CODE = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (checkPermission()) {
            // Code for above or equal 23 API Oriented Device
            // Your Permission granted already .Do next code
            permissionGranted()
        } else {
            requestPermission()
        }
    }

    private fun checkPermission(): Boolean {
        val result = ContextCompat.checkSelfPermission(
            this@MainActivity,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        return result == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this@MainActivity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        ) {
            Toast.makeText(
                this@MainActivity,
                "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.",
                Toast.LENGTH_LONG
            ).show()
            permissionDenied()

        } else {
            ActivityCompat.requestPermissions(
                this@MainActivity,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                PERMISSION_REQUEST_CODE
            )
            permissionGranted()
        }
    }

    private fun permissionGranted() {
        val sp =
            getSharedPreferences("checkbox", Context.MODE_PRIVATE)
        val et = sp.edit()
        et.putBoolean("access", true)
        et.apply()
    }

    private fun permissionDenied() {
        val sp =
            getSharedPreferences("checkbox", Context.MODE_PRIVATE)
        val et = sp.edit()
        et.putBoolean("access", false)
        et.apply()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.e("value", "Permission Granted, Now you can use local drive .")
                permissionGranted()
            } else {
                Log.e("value", "Permission Denied, You cannot use local drive .")
                permissionDenied()
            }
        }
    }
}