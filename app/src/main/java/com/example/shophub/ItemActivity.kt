package com.example.shophub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        val title: TextView = findViewById(R.id.item_title)
        val text: TextView = findViewById(R.id.item_text)

        title.text = intent.getStringExtra("ItemTitle")
        text.text = intent.getStringExtra("ItemText")
    }
}