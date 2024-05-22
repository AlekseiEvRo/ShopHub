package com.example.shophub

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.razorpay.Checkout
import org.json.JSONObject

class ItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        Checkout.preload(applicationContext)
        val co = Checkout()

        co.setKeyID("rzp_live_EgmQoGwAT33eJi")

        val title: TextView = findViewById(R.id.item_title)
        val text: TextView = findViewById(R.id.item_text)
        val button: Button = findViewById(R.id.item_button)

        title.text = intent.getStringExtra("ItemTitle")
        text.text = intent.getStringExtra("ItemText")

        button.setOnClickListener{
            startPayment()
        }
    }

    private fun startPayment() {
        val activity: Activity = this
        val co = Checkout()

        try {
            val options = JSONObject()
            options.put("name","ShopHub co.")
            options.put("description","Demoing Charges")
            //You can omit the image option to fetch the image from the dashboard
            options.put("image","http://example.com/image/rzp.jpg")
            options.put("theme.color", "#f00");
            options.put("currency","RUB");
            options.put("order_id", "order_1");
            options.put("amount",10000)//pass amount in currency subunits

            co.open(activity,options)
        }catch (e: Exception){
            Toast.makeText(activity,"Error in payment: "+ e.message,Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }
}