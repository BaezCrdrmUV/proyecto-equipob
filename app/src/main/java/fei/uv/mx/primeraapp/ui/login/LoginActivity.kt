package fei.uv.mx.primeraapp.ui.login

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import fei.uv.mx.primeraapp.*

import fei.uv.mx.primeraapp.data.LoginDataSource
import fei.uv.mx.primeraapp.data.LoginRepository
import fei.uv.mx.primeraapp.data.Result
import fei.uv.mx.primeraapp.data.model.LoggedInUser

class LoginActivity : AppCompatActivity() {
    lateinit var loading : ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        val username = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val login = findViewById<Button>(R.id.login)
        loading = findViewById<ProgressBar>(R.id.loading)

        login.setOnClickListener {

            LoginTask().execute(username.text.toString(), password.text.toString())

        }

        val register = findViewById<Button>(R.id.register);
        register.setOnClickListener{
            val registerTry = Intent(this, RegisterUserActivity::class.java)
            startActivity(registerTry)
        }
    }

    companion object {
        var usuario: LoggedInUser? = null

    }

    inner class LoginTask  : AsyncTask<String, Void, Boolean>() {
        var usuariox : LoggedInUser? = null
        override fun onPreExecute() {
            loading.visibility = View.VISIBLE
        }

        override fun doInBackground(vararg params: String): Boolean {
            val repo = LoginRepository(LoginDataSource())
            val resultado  = repo.login(params[0], params[1])
            if (resultado is Result.Success) {
                usuariox = resultado.data
                return true
            } else
                return   false
        }

        override fun onPostExecute(result: Boolean) {
            loading.visibility = View.GONE
            if (result) {
                LoginActivity.usuario = usuariox
                val intento = Intent(this@LoginActivity, MenuActivity::class.java)
                startActivity(intento)
            } else {
                val toast = Toast.makeText(this@LoginActivity, "Nombre de usuario y/o contraseña inválida", Toast.LENGTH_LONG)
                toast.show()
            }
        }

    }

}