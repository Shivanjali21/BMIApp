package com.practice.bmi

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.practice.bmi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener(this)
        binding.btnSub.setOnClickListener(this)
        binding.btnMul.setOnClickListener(this)
        binding.btnDiv.setOnClickListener(this)
    }

    fun hideKeyboardFrom(context: Context, view: View) {
        val imm = context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnAdd -> {
                if (binding.etValueOne.text.toString() != "" && binding.etValueTwo.text.toString() != "") {
                    val userInputOne = binding.etValueOne.text.toString().toInt()
                    val userInputTwo = binding.etValueTwo.text.toString().toInt()
                    val sum = userInputOne + userInputTwo
                    Toast.makeText(this, "The sum is $sum", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Please fill all the required fields!", Toast.LENGTH_SHORT)
                        .show()
                }
                binding.etValueOne.setText("")
                binding.etValueTwo.setText("")
            }

            R.id.btnSub -> {
                if (binding.etValueOne.text.toString() != "" && binding.etValueTwo.text.toString() != "") {
                    val userInputOne = binding.etValueOne.text.toString().toInt()
                    val userInputTwo = binding.etValueTwo.text.toString().toInt()
                    val sub = if (userInputOne > userInputTwo) {
                        userInputOne - userInputTwo
                    } else {
                        userInputTwo - userInputOne
                    }
                    Toast.makeText(this, "The difference is $sub", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Please fill all the required fields!", Toast.LENGTH_SHORT)
                        .show()
                }
                binding.etValueOne.setText("")
                binding.etValueTwo.setText("")
            }

            R.id.btnMul -> {
                if (binding.etValueOne.text.toString() != "" && binding.etValueTwo.text.toString() != "") {
                    val userInputOne = binding.etValueOne.text.toString().toInt()
                    val userInputTwo = binding.etValueTwo.text.toString().toInt()
                    val mul = userInputOne * userInputTwo
                    Toast.makeText(this, "The product is $mul", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Please fill all the required fields!", Toast.LENGTH_SHORT)
                        .show()
                }
                binding.etValueOne.setText("")
                binding.etValueTwo.setText("")
            }

            R.id.btnDiv -> {
                val div : Int
                if (binding.etValueOne.text.toString() != "" && binding.etValueTwo.text.toString() != "") {
                    val userInputOne = binding.etValueOne.text.toString().toInt()
                    val userInputTwo = binding.etValueTwo.text.toString().toInt()
                    if (userInputTwo == 0) {
                        Toast.makeText(this, "A number can not de divided by 0 as it produce infinite result.", Toast.LENGTH_SHORT).show()
                    } else {
                        div = userInputOne / userInputTwo
                        Toast.makeText(this, "The sum is $div", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Please fill all the required fields!", Toast.LENGTH_SHORT).show()
                }
                binding.etValueOne.setText("")
                binding.etValueTwo.setText("")
            }
        }
    }
}