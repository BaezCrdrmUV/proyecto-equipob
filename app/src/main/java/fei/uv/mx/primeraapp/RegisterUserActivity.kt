package fei.uv.mx.primeraapp

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import fei.uv.mx.primeraapp.data.LoginDataSource
import fei.uv.mx.primeraapp.data.LoginRepository
import fei.uv.mx.primeraapp.data.Result
import fei.uv.mx.primeraapp.data.model.*
import fei.uv.mx.primeraapp.ui.login.LoginActivity

class RegisterUserActivity : AppCompatActivity() {
    lateinit var loading : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Registrar cuenta de usuario"
        setContentView(R.layout.activity_register_user)
        loading = findViewById<ProgressBar>(R.id.loading)

        val username = findViewById<EditText>(R.id.username)
        val userPassword = findViewById<EditText>(R.id.password)
        val userEmail = findViewById<EditText>(R.id.email)

        val botonRegistro = findViewById<Button>(R.id.register)
        botonRegistro.setOnClickListener {
            if (!username.text.isNullOrEmpty() &&
                !userPassword.text.isNullOrEmpty() &&
                !userEmail.text.isNullOrEmpty()){

                val newUser : User = User(
                    accountId = "",
                    username = username.text.toString(),
                    email = userEmail.text.toString(),
                    isUser = 1,
                    statusId = 1,
                    status = arrayOf(Status(StatusId = 1, Name = "Activo")),
                    passwords = arrayOf(Password(passwordString = userPassword.text.toString()))
                )

                RegisterAccountTask().execute(newUser)
            }else {
                val toast = Toast.makeText(
                    this@RegisterUserActivity,
                    "Por favor ingresa los campos solicitados",
                    Toast.LENGTH_LONG
                )
                toast.show()
            }
        }
    }

    inner class RegisterAccountTask  : AsyncTask<User, Void, Boolean>() {
        override fun onPreExecute() {
            loading.visibility = View.VISIBLE
        }

        override fun doInBackground(vararg params: User): Boolean {
            val repo = LoginRepository(LoginDataSource())
            val resultado  = repo.saveNewUser(params[0])
            if (resultado is Result.Success) {
                return resultado.data.success
            } else
                return   false
        }


        override fun onPostExecute(result: Boolean) {
            loading.visibility = View.GONE
            if (result) {
                val toast = Toast.makeText(this@RegisterUserActivity, "Cuenta registrada exitosamente", Toast.LENGTH_LONG)
                toast.show()

                val intento = Intent(this@RegisterUserActivity, LoginActivity::class.java)
                startActivity(intento)
            } else {
                val toast = Toast.makeText(this@RegisterUserActivity, "Error: No se logró crear su cuenta.", Toast.LENGTH_LONG)
                toast.show()
            }
        }

    }
}