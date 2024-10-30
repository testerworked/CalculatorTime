package com.android.calculatortime

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var toolbarFor: Toolbar
    private lateinit var firstTimeET: EditText
    private lateinit var secondTimeET: EditText
    private lateinit var resultText: TextView

    private val sumBTN by lazy {
        findViewById<Button>(R.id.buttonSum)
    }

    private val difBTN by lazy {
        findViewById<Button>(R.id.buttonDif)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        toolbarFor = findViewById(R.id.toolbarMain)
        setSupportActionBar(toolbarFor)
        title = "Калькулятор"
        toolbarFor.subtitle = "Версия 1.2.1"
        toolbarFor.setLogo(R.drawable.baseline_coffee_24)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        firstTimeET = findViewById(R.id.editTextText)
        secondTimeET = findViewById(R.id.editTextText2)
        resultText = findViewById(R.id.resultText)

        sumBTN.setBackgroundColor(ContextCompat.getColor(this, R.color.greenColor))
        difBTN.setBackgroundColor(ContextCompat.getColor(this, R.color.greenColor))


        this.sumBTN.setOnClickListener(this)
        this.difBTN.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }



    override fun onClick(v: View?) {

        var first = firstTimeET.text.toString()
        var second = secondTimeET.text.toString()
        try {
            when(v?.id){
                R.id.buttonSum -> {
                    Toast.makeText(this, "Проверка: $first и $second", Toast.LENGTH_SHORT).show()
                    resultText.text = Operation(first, second).sum()
                }
                R.id.buttonDif -> {
                    Toast.makeText(this, "Проверка: $first и $second", Toast.LENGTH_SHORT).show()
                    resultText.text = Operation(first, second).dif()
                }
                else -> {
                    resultText.text = "Результат обновлен"
                }
            }
        }catch (ex1:Exception){
            resultText.text = "Вызвано исключение"
        }

        return
    }

}