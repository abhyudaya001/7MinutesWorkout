package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.a7minutesworkout.databinding.ActivityBmiBinding

class bmiActivity : AppCompatActivity() {
    private var binding:ActivityBmiBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.tbBmi)
        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title="CALCULATE BMI"
        }
        binding?.tbBmi?.setNavigationOnClickListener{
            onBackPressed()
        }
        binding?.btnCal?.setOnClickListener {
            if(binding?.tiHeight?.text?.length==0||binding?.tiWeight?.text?.length==0){
                Toast.makeText(this@bmiActivity, "Enter the field", Toast.LENGTH_SHORT).show()
            }else{
                var height=binding?.tiHeight?.text?.toString()?.toInt()
                var weight=binding?.tiWeight?.text?.toString()?.toInt()
                var ans=height!! + weight!!
                Toast.makeText(this@bmiActivity, "${ans}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}