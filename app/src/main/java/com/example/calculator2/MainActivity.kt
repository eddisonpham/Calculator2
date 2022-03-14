package com.example.calculator2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    //----------------------------------Enum Class-----------------------------------//

    private enum class OPERATOR{
        PLUS, SUBTRACT, MULTIPLY, DIVIDE, EQUAL
    }

    //----------------------------------Instance Variables-----------------------------------//

    private var currentOperator: OPERATOR? = null
    private var stringNumberAtLeft: String? = null
    private var stringNumberAtRight: String? = null
    private var calculationsResult: Double = 0.0
    private var currentNumber: String? = ""
    private var calculationsString:String? = null

    //----------------------------------UI Widgets-----------------------------------//

    private lateinit var editNumber: EditText
    private lateinit var txtCalculation: TextView

    //----------------------------------On Create Method-------------------------------------//

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editNumber = findViewById(R.id.editNumber)
        txtCalculation = findViewById(R.id.txtCalculation)

        editNumber.isEnabled = false
        editNumber.inputType = InputType.TYPE_NULL
        calculationsString = ""

    }

    //----------------------------------Check Button Tapped-----------------------------------//

    fun buttonTapped(view: View){
        when (view.id){
            R.id.zero -> numberIsTapped(0)
            R.id.one -> numberIsTapped(1)
            R.id.two -> numberIsTapped(2)
            R.id.three -> numberIsTapped(3)
            R.id.four -> numberIsTapped(4)
            R.id.five -> numberIsTapped(5)
            R.id.six -> numberIsTapped(6)
            R.id.seven -> numberIsTapped(7)
            R.id.eight -> numberIsTapped(8)
            R.id.nine -> numberIsTapped(9)

            R.id.divide ->{
                operatorIsTapped(OPERATOR.DIVIDE)
                calculationsString += " / "
            }
            R.id.multiply ->{
                operatorIsTapped(OPERATOR.MULTIPLY)
                calculationsString += " * "
            }
            R.id.subtract ->{
                operatorIsTapped(OPERATOR.SUBTRACT)
                calculationsString += " - "
            }
            R.id.add ->{
                operatorIsTapped(OPERATOR.PLUS)
                calculationsString += " + "
            }
            R.id.equals ->{
                operatorIsTapped(OPERATOR.EQUAL)
            }
            R.id.percent-> implementPercent()
            R.id.clear-> clear()
        }
    }

    private fun operatorIsTapped(tappedOperator: OPERATOR){
        if (currentOperator != null){
            if (currentNumber != ""){
                stringNumberAtRight = currentNumber
                currentNumber = ""
                var numberLeft = stringNumberAtLeft!!.toDouble()
                var numberRight = stringNumberAtRight!!.toDouble()
                when(currentOperator){
                    OPERATOR.PLUS -> calculationsResult = numberLeft + numberRight
                    OPERATOR.SUBTRACT -> calculationsResult = numberLeft - numberRight
                    OPERATOR.DIVIDE -> calculationsResult = numberLeft/numberRight
                    OPERATOR.MULTIPLY -> calculationsResult = numberLeft*numberRight
                }
                stringNumberAtLeft = calculationsResult.toString()
                editNumber.setText(calculationsResult.toString())
                calculationsString = stringNumberAtLeft
            }
        }else{
            stringNumberAtLeft = currentNumber
            currentNumber = ""
        }
        currentOperator = tappedOperator
    }

    private fun implementPercent(){
        val percentValue = editNumber.text.toString().toDouble()/100
        currentNumber = percentValue.toString()
        editNumber.setText(currentNumber)
    }

    private fun clear(){
        stringNumberAtLeft = ""
        stringNumberAtRight = ""
        calculationsResult = 0.0
        calculationsString = ""
        currentNumber = ""
        currentOperator = null
        editNumber.setText("0")

    }

    private fun numberIsTapped(tappedNumber: Int){
        currentNumber += tappedNumber.toString()
        editNumber.setText(currentNumber)
        calculationsString = currentNumber
        txtCalculation.setText(calculationsString)
    }
}