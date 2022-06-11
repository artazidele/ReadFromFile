package com.example.readfromfile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

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
        var inputWordList = ArrayList<String>()
        var newWord = ""
        for (i in 0..input.length - 1) {
            val letter: Char = input.get(i)
            if (letter == ' ' || letter == '\n') {
                inputWordList.add(newWord)
                newWord = ""
            } else {
                newWord += letter
            }
        }
        inputWordList.add(newWord)

        val fileContent: String = applicationContext.assets.open("input.txt").bufferedReader().use {
            it.readText()
        }

        var fileWordList = ArrayList<String>()
        newWord = ""
        for (i in 0..fileContent.length - 1) {
            val letter: Char = fileContent.get(i)
            if (letter == ' ' || letter == '\n') {
                fileWordList.add(newWord)
                newWord = ""
            } else {
                newWord += letter
            }
        }
        fileWordList.add(newWord)

        var areWordList = ""
        var areNotWordList = ""
        for (inputW in inputWordList) {
            var isInFile = false
            for (fileW in fileWordList) {
                if (inputW == fileW) {
                    isInFile = true
                    break
                }
            }
            if (isInFile == false) {
                areNotWordList += inputW + " "
            } else {
                areWordList += inputW + " "
            }
        }
        areTv.text = areWordList
        areNotTv.text = areNotWordList
    }
}
