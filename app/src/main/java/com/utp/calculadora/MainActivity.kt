package com.utp.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var accumulatorMemory: String
    private  lateinit var  operatorMemory: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializerListenerButton()
    }
    private fun initializerListenerButton() {
        val btn0 = findViewById<Button>(R.id.button0)
        val btn1 = findViewById<Button>(R.id.button1)
        val btn2 = findViewById<Button>(R.id.button2)
        val btn3 = findViewById<Button>(R.id.button3)
        val btn4 = findViewById<Button>(R.id.button4)
        val btn5 = findViewById<Button>(R.id.button5)
        val btn6 = findViewById<Button>(R.id.button6)
        val btn7 = findViewById<Button>(R.id.button7)
        val btn8 = findViewById<Button>(R.id.button8)
        val btn9 = findViewById<Button>(R.id.button9)

        val btnMinus = findViewById<Button>(R.id.butMinus)
        val btnPlus = findViewById<Button>(R.id.but_plus)
        val btnEquals = findViewById<Button>(R.id.but_equal)
        val btnMulti = findViewById<Button>(R.id.but_multi)
        val btnDiv = findViewById<Button>(R.id.but_div)
        val btnPoint = findViewById<Button>(R.id.but_point)
        val btnClean = findViewById<Button>(R.id.but_clear)

        val btnDelete = findViewById<Button>(R.id.btn_delete)

        val textResult = findViewById<TextView>(R.id.textView)

        clickBtnNumber(btn0, textResult)
        clickBtnNumber(btn1, textResult)
        clickBtnNumber(btn2, textResult)
        clickBtnNumber(btn3, textResult)
        clickBtnNumber(btn4, textResult)
        clickBtnNumber(btn5, textResult)
        clickBtnNumber(btn6, textResult)
        clickBtnNumber(btn7, textResult)
        clickBtnNumber(btn8, textResult)
        clickBtnNumber(btn9, textResult)
        clickBtnPoint(btnPoint, textResult)
        clickBtnClean(btnClean,  textResult)

        clickBtnOperator(btnMinus, textResult)
        clickBtnOperator(btnPlus, textResult)
        clickBtnOperator(btnMulti, textResult)
        clickBtnOperator(btnDiv, textResult)

        clickBtnDelete(btnDelete, textResult)
        clickBtnEqual(btnEquals, textResult)
    }
    private fun clickBtnNumber(button: Button, textView: TextView) {
        button.setOnClickListener {
            val accumulator = textView.text
            if (accumulator.equals("0")) {
                textView.text = button.text
            } else {
                "$accumulator${button.text}".also { textView.text = it }
            }
        }
    }
    private fun clickBtnPoint(button: Button, textView: TextView) {
        button.setOnClickListener {
            if (!textView.text.contains(".")) {
                "${textView.text}${button.text}".also { textView.text = it }
            }
        }
    }
    private fun clickBtnOperator(button: Button, textView: TextView) {
        button.setOnClickListener {
            Log.d("OPERATOR", "${button.text}")
            operatorMemory = button.text as String
            accumulatorMemory = textView.text as String
            textView.text = "0"
        }
    }
    private fun clickBtnEqual(button: Button, textView: TextView) {
        button.setOnClickListener {

            val result = when(operatorMemory) {
                "-" -> "${(accumulatorMemory.toFloat() - textView.text.toString().toFloat())}"
                "+" -> "${accumulatorMemory.toFloat() + textView.text.toString().toFloat()}"
                "x" -> "${accumulatorMemory.toFloat() * textView.text.toString().toFloat()}"
                "/" -> "${accumulatorMemory.toFloat() / textView.text.toString().toFloat()}"
                else -> "0"
            }
            textView.text = result.removeSuffix(".0")
        }
    }
    private  fun clickBtnClean(button: Button, textView: TextView) {
        button.setOnClickListener {
            textView.text = "0"
        }
    }
    private fun clickBtnDelete(button: Button, textView: TextView) {
        button.setOnClickListener {
            if (textView.text.length == 1) {
                textView.text = "0"
            } else if (!textView.text.equals("0")) {
                textView.text = textView.text.dropLast(1)
            }
        }
    }
}