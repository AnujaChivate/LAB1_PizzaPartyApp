package com.zybooks.pizzaparty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.core.text.set
import androidx.core.widget.addTextChangedListener
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var numAttendEditText: EditText
    private lateinit var numPizzasTextView: TextView
    private lateinit var howHungryRadioGroup: RadioGroup
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        var isEditMode = false;

        setContentView(R.layout.activity_main)
        numAttendEditText = findViewById(R.id.num_attend_edit_text)
        numPizzasTextView = findViewById(R.id.num_pizzas_text_view)
        howHungryRadioGroup = findViewById(R.id.hungry_radio_group)

//        numAttendEditText.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                if(!isEditMode && numAttendEditText.text.isNotEmpty()) {
//                    numAttendEditText.removeTextChangedListener(this);
//                    numAttendEditText.setText("");
//                    numAttendEditText.addTextChangedListener(this);
//                    isEditMode = true;
//                }
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                  numAttendEditText.setText(s?.substring(start, count));
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//                isEditMode = false;
//            }
//        })
    }

    /**
     * calculateClick function will be called when a user presses the calculate button on the app
     * It reads the value of text input in the "Number of people" and the value of radio button "How Hungry?"
     * and calculate the total pizzas and sets the result text in Total Pizzas TextView which displays
     * the output in the app
     */
    fun calculateClick(view: View) {

        /** Get the text that was typed into the EditText */
        val numAttendStr = numAttendEditText.text.toString()

        /** Convert the text into an integer */
        val numAttend = numAttendStr.toIntOrNull() ?: 0

        // Get hunger level selection
        val hungerLevel = when (howHungryRadioGroup.checkedRadioButtonId) {
            R.id.light_radio_button -> PizzaCalculator.HungerLevel.LIGHT
            R.id.medium_radio_button -> PizzaCalculator.HungerLevel.MEDIUM
            else -> PizzaCalculator.HungerLevel.RAVENOUS
        }

        // Get the number of pizzas needed
        val calc = PizzaCalculator(numAttend, hungerLevel)
        val totalPizzas = calc.totalPizzas

        // Place totalPizzas into the string resource and display
        val totalText = getString(R.string.total_pizzas_num, totalPizzas)
        numPizzasTextView.text = totalText
    }
}