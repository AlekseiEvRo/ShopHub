package com.example.shophub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class RegActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userLogin: EditText = findViewById(R.id.user_login)
        val userEmail: EditText = findViewById(R.id.user_email)
        val userPassword: EditText = findViewById(R.id.user_password)
        val button: Button = findViewById(R.id.button_reg)
        val linkToAuth: TextView = findViewById(R.id.link_to_auth)

        linkToAuth.setOnClickListener{
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }

        button.setOnClickListener{
            val login = userLogin.text.toString()
            val email = userEmail.text.toString()
            val password = userPassword.text.toString()

            val errorMessage = validateTextInput(login,email,password)
            if (errorMessage != ""){
                Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
            }
            else{
                val user = User(login,email,password)

                val db = DbHelper(this, null)
                db.addUser(user)
                Toast.makeText(this, "Пользователь $login добавлен", Toast.LENGTH_LONG).show()

                userLogin.text.clear()
                userEmail.text.clear()
                userPassword.text.clear()
            }
        }
    }

    private fun validateTextInput(login: String, email: String, password: String): String {
        var errorMessage = ""

        if (login == "")
            errorMessage += "Не введён логин\n"

        if (email == "")
            errorMessage += "Не введён email\n"

        if (password == "")
            errorMessage += "Не введён пароль\n"

        return errorMessage
    }
}