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

    private fun appendNumber(number: String) {
        currentInput.append(number)
        updateDisplay()
    }

    private fun handleBackspace() {
        if (currentInput.isNotEmpty()) {
            currentInput.deleteCharAt(currentInput.length - 1)
            updateDisplay()
        }
    }

    private fun setOperator(operator: Operator) {
        if (operand1 == null) {
            operand1 = BigDecimal(currentInput.toString())
            currentInput.clear()
        }
        binding.tvOldInput.text = operand1.toString()
        binding.tvInput.text = ""
        currentOperator = operator
        binding.tvCurrentOperand.text = operatorToString(operator)
        currentInput.clear()
    }

    private fun operatorToString(operator: Operator): String {
        return when (operator) {
            Operator.ADD -> "+"
            Operator.SUBTRACT -> "-"
            Operator.MULTIPLY -> "x"
            Operator.DIVIDE -> "÷"
            Operator.NONE -> ""
        }
    }

    private fun calculateResult() {
        val operand2 = BigDecimal(currentInput.toString())
        var result: BigDecimal? = null

        when (currentOperator) {
            Operator.ADD -> result = operand1?.add(operand2)
            Operator.SUBTRACT -> result = operand1?.subtract(operand2)
            Operator.MULTIPLY -> result = operand1?.multiply(operand2)
            Operator.DIVIDE -> {
                if (operand2 != BigDecimal.ZERO)
                    operand1?.divide(operand2, 10, RoundingMode.HALF_UP).also {result = it}
            }
            Operator.NONE -> result = operand2
        }

        if (result != null) {
            var displayString =
                String.format("$operand1%s$operand2", operatorToString(currentOperator))
            if (operand1 == null)
                displayString = "$operand2"
            binding.tvOldInput.text = displayString
            binding.tvInput.text = String.format(result.toString())
            operand1 = result
            currentInput.clear()
            currentInput.append(result)
        }
    }

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
