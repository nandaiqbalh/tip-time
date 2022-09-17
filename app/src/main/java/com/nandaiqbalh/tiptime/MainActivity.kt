package com.nandaiqbalh.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.nandaiqbalh.tiptime.databinding.ActivityMainBinding
import kotlin.math.cos

class MainActivity : AppCompatActivity() {

    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // calculate
        calculateTimeTipe()
    }

    private fun calculateTimeTipe(){

        // btn triggered
        binding.btnCalculate.setOnClickListener {

            // get user input value from edit text
            val edtValueString = binding.edtCostService.text.toString()

            // check if user already input value or not
            if (edtValueString == ""){
                displayTip(0.0)

            } else {
                // convert to double
                var costService = edtValueString.toDouble()

                // check if cost user entered 0 cost service
                if (costService.equals(0.0)){
                    displayTip(0.0)

                  // if user input is no 0
                } else {

                    // check radio button is checked or not
                    val tipPercent = when (binding.categoryService.checkedRadioButtonId){
                        R.id.rb_amazing -> 0.20
                        R.id.rb_good -> 0.18
                        R.id.rb_ok -> 0.15
                        else -> 0.00
                    }

                    // calculate cost
                    costService += (costService * tipPercent)

                    if (binding.swRoundUp.isChecked){
                        costService = kotlin.math.ceil(costService)
                    }

                    displayTip(costService)
                }
            }

        }
    }

    // display tip on text view
    private fun displayTip(value: Double){
        binding.tvTipAmount.text = "Tip amount: $value"
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }

}