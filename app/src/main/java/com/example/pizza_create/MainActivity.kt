package com.example.pizza_create

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {
    var savedPizzas : MutableSet<String> = mutableSetOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        var ing = arrayOf(
            //ingredient button, group,
            arrayOf("Olive", olives, olive_group),
            arrayOf("Mushroom", mushroom, mush_group),
            arrayOf("Tomato", tomatoes, tom_group),
            arrayOf("Mozarella", mozzarella, moz_group),
            arrayOf("Onion", onion, oni_group),
            arrayOf("Pepperoni", pepperoni, pep_group)
        )

        for (x in ing)
        {
            var item = x[1] as ImageView
            var ingredient = x[2] as ImageView
            ingredient.visibility = View.INVISIBLE
            item.setOnClickListener {
                if (ingredient.visibility == View.INVISIBLE) {
                    ingredient.visibility = View.VISIBLE
                }
                else {
                    ingredient.visibility = View.INVISIBLE
                }
            }
        }

        back.setOnClickListener {
            buttons_layout.visibility = View.VISIBLE
            finish.visibility = View.VISIBLE
            spinnerContainer.visibility = View.VISIBLE
            saveitm.visibility= View.INVISIBLE
            fin_text.visibility = View.INVISIBLE
            back.visibility = View.INVISIBLE
        }

        finish.setOnClickListener {
            buttons_layout.visibility = View.INVISIBLE
            finish.visibility = View.INVISIBLE
            spinnerContainer.visibility = View.INVISIBLE
            saveitm.visibility= View.VISIBLE
            fin_text.visibility = View.VISIBLE
            back.visibility = View.VISIBLE

            var output = ""
            for (x in ing)
            {
                var ingredient = x[2] as ImageView
                if (ingredient.visibility == View.VISIBLE) output += "${x[0]} "
            }
            output += "Pizza"
            fin_text.text = output
        }

        val pref = "App_pre"
        val key = "SavedPizzas"

        //reset preferences
        //val editor = getSharedPreferences("App_pre", 0).edit()
        //editor.putStringSet(key, savedPizzas)
        //editor.commit()

        val sp = getSharedPreferences(pref, 0)
        var value = sp.getStringSet(key, mutableSetOf()) as MutableSet<String>
        savedPizzas.addAll(value)

        saveitm.setOnClickListener {
            val editor = getSharedPreferences("App_pre", 0).edit()
            savedPizzas.add(fin_text.text.toString())
            editor.putStringSet(key, savedPizzas)
            editor.commit()
            Toast.makeText(this, "Saved Pizza: ${fin_text.text}", Toast.LENGTH_SHORT).show()

            setSpinner(ing)
        }
        setSpinner(ing)
    }

    fun setSpinner(ing : Array<Array<Any>>)
    {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, savedPizzas.toTypedArray())
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)

        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                setPizza(p2, ing)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}

        }

        adapter.notifyDataSetChanged()
    }

    fun setPizza(position : Int, ing : Array<Array<Any>>)
    {
        val pizzas = savedPizzas.toTypedArray()
        val pizza = pizzas[position]

        for (x in ing)
        {
            val ingname = x[0] as String
            var ingredient = x[2] as ImageView
            if (pizza.contains(ingname)) {
                ingredient.visibility = View.VISIBLE
            }
            else {
                ingredient.visibility = View.INVISIBLE
            }
        }
    }
}