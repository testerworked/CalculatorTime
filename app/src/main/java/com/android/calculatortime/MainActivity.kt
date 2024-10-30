package com.android.calculatortime

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
        resultText.setTextColor(ContextCompat.getColor(this, R.color.black))

        sumBTN.setBackgroundColor(ContextCompat.getColor(this, R.color.greenColor))
        difBTN.setBackgroundColor(ContextCompat.getColor(this, R.color.greenColor))


        this.sumBTN.setOnClickListener(this)
        this.difBTN.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.resetMenuMain -> {
                firstTimeET.text.clear()
                secondTimeET.text.clear()
                resultText.setTextColor(ContextCompat.getColor(this, R.color.black))
                resultText.text = "Результат"
                Toast.makeText(
                    applicationContext,
                    "Данные очищены",
                    Toast.LENGTH_LONG
                    ).show()
            }
            R.id.exitMenuMain -> {
                Toast.makeText(
                    applicationContext,
                    "Работа завершена",
                    Toast.LENGTH_LONG
                ).show()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {

        var first = firstTimeET.text.toString()
        var second = secondTimeET.text.toString()
        try {
            when(v?.id){
                R.id.buttonSum -> {
                    val resultSum = Operation(first, second).sum()
                    resultText.setTextColor(ContextCompat.getColor(this, R.color.textResultColor))
                    resultText.text = resultSum
                    Toast.makeText(this, "Результат: $resultSum", Toast.LENGTH_SHORT).show()
                }
                R.id.buttonDif -> {
                    val resultDif = Operation(first, second).dif()
                    resultText.setTextColor(ContextCompat.getColor(this, R.color.textResultColor))
                    resultText.text = resultDif
                    Toast.makeText(this, "Результат: $resultDif", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    resultText.setTextColor(ContextCompat.getColor(this, R.color.textResultColor))
                    resultText.text = "Результат обновлен"
                }
            }
        }catch (ex1:Exception){
            resultText.text = "Вызвано исключение"
        }

        return
    }

}