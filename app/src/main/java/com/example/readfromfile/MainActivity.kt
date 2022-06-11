package com.example.readfromfile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.io.File
import java.io.InputStream

class MainActivity : AppCompatActivity() {
    private lateinit var areTv: TextView
    private lateinit var areNotTv: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        areTv = findViewById(R.id.are_tv)
        areNotTv = findViewById(R.id.are_not_tv)
        findViewById<Button>(R.id.find_btn).setOnClickListener {
            val inputText = findViewById<EditText>(R.id.edit_text).text.toString()
            readFile(inputText)
        }
    }

    private fun readFile(input: String) {
        val fileContent: String = applicationContext.assets.open("input.txt").bufferedReader().use {
            it.readText()
        }
        var fileWordList = ArrayList<String>()
        val fileSize = fileContent.length
        var newWord = ""
        for (i in 0..fileSize - 1) {
            val letter: Char = fileContent.get(i)
            if (letter == ' ' || letter == '\n') {
                fileWordList.add(newWord)
                newWord = ""
            } else {
                newWord += letter

            }
        }
        fileWordList.add(newWord)
        areTv.text = fileWordList.size.toString()
    }
}