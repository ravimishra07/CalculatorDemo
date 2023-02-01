package com.ravi.calculatordemo

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ravi.calculatordemo.databinding.ActivityMainBinding
import com.ravi.mathlib.MathOps
import com.ravi.mathlib.MathOpsImpl

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mathOps: MathOps

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mathOps = MathOpsImpl()

        binding.btnAdd.setOnClickListener(this)
        binding.btnSubtract.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_add -> performOperation(Operation.ADDITION)
            R.id.btn_subtract -> performOperation(Operation.SUBTRACT)
        }
    }

    private fun performOperation(operationType: Operation) {
        binding.apply {
            if (etNumber1.hasValidInput() && etNumber2.hasValidInput()) {
                val result = when (operationType) {
                    Operation.ADDITION -> mathOps.addition(etNumber1.asNum(), etNumber2.asNum())
                    Operation.SUBTRACT -> mathOps.subtraction(etNumber1.asNum(), etNumber2.asNum())
                }
                startResultActivity(result)
            } else {
                showMessage(getString(R.string.enter_valid_number))
            }
        }
    }

    private fun startResultActivity(result: Double) {
        ResultActivity.launch(this,result)
    }
}

fun EditText.hasValidInput(): Boolean {
    return try {
        if (text.toString().isBlank()) {
            this.error = context.getString(R.string.field_empty)
        }
        this.text.toString().toDouble()

        true
    } catch (e: NumberFormatException) {
        false
    }
}

fun EditText.asNum(): Double {
    return try {
        this.text.toString().toDouble()
    } catch (e: NumberFormatException) {
        0.0
    }
}

fun Context.showMessage(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}