package com.practice.bmi

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.READ_SMS
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.res.ResourcesCompat
import com.practice.bmi.databinding.ActivityMainBinding
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

class MainActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
      const val REQ_PERMISSION = 1
    }

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnRequest.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnRequest -> {
               if(checkPermission()){
                 //Toast.makeText(this, "Permission Already Granted!!", Toast.LENGTH_SHORT).show()
                 MotionToast.createColorToast(this, "","Permission Already Granted!!", MotionToastStyle.SUCCESS, MotionToast.GRAVITY_BOTTOM, MotionToast.SHORT_DURATION, ResourcesCompat.getFont(this, R.font.atkinson_medium))
               }else{
                 ActivityCompat.requestPermissions(this, arrayOf(READ_SMS, ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION), REQ_PERMISSION)
               }
            }
        }
    }

    private fun checkPermission() : Boolean {
      val permissions = arrayOf(READ_SMS, ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION)
      return permissions.all { permission ->
        ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
      }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == REQ_PERMISSION){
          if(grantResults.isNotEmpty()){
            val sms = grantResults[0]
            val fineLoc = grantResults[1]
            val coarseLoc = grantResults[2]

            val checkSms = sms == PackageManager.PERMISSION_GRANTED
            val checkFL = fineLoc == PackageManager.PERMISSION_GRANTED
            val checkCL = coarseLoc == PackageManager.PERMISSION_GRANTED
              
             if(checkSms && checkFL && checkCL){
                 //Toast.makeText(this, "Permissions Granted!!", Toast.LENGTH_SHORT).show()
                 MotionToast.createColorToast(this, "","Permission Granted!!", MotionToastStyle.SUCCESS, MotionToast.GRAVITY_BOTTOM, MotionToast.SHORT_DURATION, ResourcesCompat.getFont(this, R.font.atkinson_medium))
             }else {
                 //Toast.makeText(this, "Permissions Denied!!", Toast.LENGTH_SHORT).show()
                 MotionToast.createToast(this, "", "Permission Denied!!", MotionToastStyle.WARNING, MotionToast.GRAVITY_BOTTOM, MotionToast.SHORT_DURATION, ResourcesCompat.getFont(this, R.font.atkinson_medium))
             } 
          }
        }
    }
}