package com.example.calculator1209

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    //declare ui elements
    private lateinit var input1:TextInputEditText
    private lateinit var input2:TextInputEditText
    private lateinit var resultTextView: TextView
    private lateinit var plusButton: MaterialButton
    private lateinit var multiplyButton: MaterialButton
    private lateinit var minusButton: MaterialButton
    private lateinit var divideButton: MaterialButton
    //initialize ui elements


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        input1= findViewById(R.id.input1)
        input2 = findViewById(R.id.input2)
        resultTextView= findViewById(R.id.result)
        plusButton= findViewById(R.id.plus)
        minusButton= findViewById(R.id.minus)
        multiplyButton= findViewById(R.id.multiply)
        divideButton= findViewById(R.id.divide)

//This code sets a click listener for the "plus" button. When the button is clicked,
// it calls the performOperation function with the Operation.ADD enum.
        plusButton.setOnClickListener {
            performOperation(input1, input2, Operation.ADD)
        }

        minusButton.setOnClickListener {
            performOperation(input1, input2, Operation.SUBTRACT)
        }

        multiplyButton.setOnClickListener {
            performOperation(input1, input2, Operation.MULTIPLY)
        }

        divideButton.setOnClickListener {
            performOperation(input1, input2, Operation.DIVIDE)
        }
    }

    //This function takes two TextInputEditText inputs and an Operation enum parameter,
    // representing the mathematical operation to be performed.
    private fun performOperation(
        input1: TextInputEditText,
        input2: TextInputEditText,
        operation: Operation
    ) {
        //Extracts the numeric values from the input fields, applies the operation,
        // and sets the result in the resultTextView.

        //The text from input1 and input2 is converted to Double values. If the conversion fails
        // (toDoubleOrNull returns null), it defaults to 0.0
        val num1 = input1.text.toString().toDoubleOrNull() ?: 0.0
        val num2 = input2.text.toString().toDoubleOrNull() ?: 0.0

        //The when expression is used to perform the operation based on the provided Operation enum.
        val result = when (operation) {
            Operation.ADD -> num1 + num2
            Operation.SUBTRACT -> num1 - num2
            Operation.MULTIPLY -> num1 * num2

            // The purpose of this check is to ensure that division by zero is avoided,
            // as dividing any number by zero is undefined in mathematics.
            //If num2 is not equal to 0.0 (i.e., num2 != 0.0 is true), the code inside the if block is executed.
            Operation.DIVIDE -> if (num2 != 0.0)
                num1 / num2
            //If num2 is equal to 0.0, the code inside the else block is executed.
            //Double.NaN stands for "Not a Number" and is a special value in floating-point arithmetic to
            // represent undefined or unrepresentable results, such as the result of dividing zero by zero.
            else Double.NaN
        }
        // The result is  displayed in the resultTextView.
        resultTextView.text = formatDecimal(result)
    }

    //The use of an enum class (Operation) is a convenient and structured way to represent different mathematical operations.
    // Enum classes are especially useful when you have a fixed set of related constants that are expected to be used together.
    enum class Operation {
        ADD, SUBTRACT, MULTIPLY, DIVIDE
    }
    private fun formatDecimal(value: Double): String {
        //For Ex-   value=18.0 then  value == value.toInt() will give,18.0==(18.0) = 18
        //(18.toDouble) will give 18.0
        return if (value == value.toInt().toDouble()) {
            value.toInt().toString()
        } else {
            value.toString()
         }
}
}