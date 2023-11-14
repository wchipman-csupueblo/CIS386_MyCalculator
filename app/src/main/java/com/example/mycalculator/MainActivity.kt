package com.example.mycalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.mycalculator.databinding.ActivityMainBinding
import java.math.BigDecimal
import java.math.RoundingMode

class MainActivity : ComponentActivity() {

    private var currentInput = StringBuilder()
    private var currentOperator = Operator.NONE
    private var operand1: BigDecimal? = null

    enum class Operator {
        NONE, ADD, SUBTRACT, MULTIPLY, DIVIDE
    }

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Click listeners for number buttons
        binding.btnOne.setOnClickListener { appendNumber("1") }
        binding.btnTwo.setOnClickListener { appendNumber("2") }
        binding.btnThree.setOnClickListener { appendNumber("3") }
        binding.btnFour.setOnClickListener { appendNumber("4") }
        binding.btnFive.setOnClickListener { appendNumber("5") }
        binding.btnSix.setOnClickListener { appendNumber("6") }
        binding.btnSeven.setOnClickListener { appendNumber("7") }
        binding.btnEight.setOnClickListener { appendNumber("8") }
        binding.btnNine.setOnClickListener { appendNumber("9") }
        binding.btnZero.setOnClickListener { appendNumber("0") }
        binding.btnDot.setOnClickListener { appendNumber(".") }

        //Click listeners for operator buttons
        binding.btnPlus.setOnClickListener { setOperator(Operator.ADD) }
        binding.btnMinus.setOnClickListener { setOperator(Operator.SUBTRACT) }
        binding.btnMultiply.setOnClickListener { setOperator(Operator.MULTIPLY) }
        binding.btnDivide.setOnClickListener { setOperator(Operator.DIVIDE) }

        // Click listener for evaluation
        binding.btnEqual.setOnClickListener { calculateResult() }

        // Click listeners for clear buttons
        binding.btnClear.setOnClickListener { clearInput() }
        binding.btnAllClear.setOnClickListener { allClearInput() }

        //Click Listener for backspace
        binding.btnBackspace.setOnClickListener { handleBackspace()}
    }

    private fun appendNumber(number: String) {}

    private fun handleBackspace() {}

    private fun setOperator(operator: Operator) {}

    private fun operatorToString(operator: Operator): String {
        return ""
    }

    private fun calculateResult() {}

    private fun allClearInput() {
        currentInput.clear()
        operand1 = null
        currentOperator = Operator.NONE
        binding.tvOldInput.text = ""
        binding.tvInput.text = "0"
        binding.tvCurrentOperand.text = ""
    }

    private fun clearInput() {
        currentInput.clear()
        currentOperator = Operator.NONE
        binding.tvInput.text = "0"
        operand1 = null
    }

    private fun updateDisplay() {
        binding.tvInput.text = currentInput.toString()
    }

}
