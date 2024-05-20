package com.example.shophub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val linkToReg: TextView = findViewById(R.id.link_to_reg)
        val userLogin: EditText = findViewById(R.id.user_login_auth)
        val userPassword: EditText = findViewById(R.id.user_password_auth)
        val button: Button = findViewById(R.id.button_auth)

        linkToReg.setOnClickListener{
            val intent = Intent(this, RegActivity::class.java)
            startActivity(intent)
        }

        button.setOnClickListener{
            val login = userLogin.text.toString()
            val password = userPassword.text.toString()

            val errorMessage = validateTextInput(login,password)
            if (errorMessage != ""){
                Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
            }
            else{
                val db = DbHelper(this, null)
                val isAuth = db.getUser(login,password)

                if (isAuth){
                    Toast.makeText(this, "Пользователь $login авторизован", Toast.LENGTH_LONG).show()

                    userLogin.text.clear()
                    userPassword.text.clear()
                }
                else{
                    Toast.makeText(this, "Пользователь не авторизован", Toast.LENGTH_LONG).show()
                }

            }
        }

    }

    private fun validateTextInput(login: String, password: String): String {
        var errorMessage = ""

        if (login == "")
            errorMessage += "Не введён логин\n"

        if (password == "")
            errorMessage += "Не введён пароль\n"

        return errorMessage
    }
}