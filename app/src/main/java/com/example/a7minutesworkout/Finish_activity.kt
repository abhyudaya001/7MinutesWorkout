package com.example.a7minutesworkout

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.a7minutesworkout.databinding.ActivityFinishBinding

class Finish_activity : AppCompatActivity() {
    private var binding:ActivityFinishBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.button?.setOnClickListener {
            val intent=Intent(this@Finish_activity,MainActivity::class.java)
            startActivity(intent)
        }
    }

//    override fun onBackPressed() {
//        var builder:AlertDialog.Builder=AlertDialog.Builder(this@Finish_activity)
//        builder.setMessage("Do yo want to exit?")
//        builder.setTitle("Alert!")
//        builder.setCancelable(false)
//        builder.setPositiveButton("Yes",DialogInterface.OnClickListener(){
//            dialog,which->finish()
//        })
//        builder.setNegativeButton("No",DialogInterface.OnClickListener(){
//            dialog,which->dialog.cancel()
//        })
//        var alertDialog=builder.create()
//        alertDialog.show()
//        super.onBackPressed()
//    }
}