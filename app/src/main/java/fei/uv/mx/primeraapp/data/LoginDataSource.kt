package fei.uv.mx.primeraapp.data

import android.icu.lang.UCharacter.LineBreak.LINE_FEED
import fei.uv.mx.primeraapp.data.model.Alumno
import fei.uv.mx.primeraapp.data.model.LoggedInUser
import fei.uv.mx.primeraapp.data.model.User
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedWriter
import java.io.DataOutputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder


/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            var url: URL? = null
            url = URL( "http://192.168.1.100:4000/account/loginmobile")
            try {
                with(url.openConnection() as HttpURLConnection) {
                    requestMethod = "POST"
                    setRequestProperty("Accept", "application/json");
                    setRequestProperty("Content-Type", "application/json");
                    doInput = true
                    doOutput = true

                    outputStream.bufferedWriter().use {

                        var requestBody : JSONObject = JSONObject()
                        requestBody.put("Username", username)
                        requestBody.put("PasswordString", password)
                        it.write(requestBody.toString());
                        it.flush();
                    }
                    inputStream.bufferedReader().use {
                        val response = it.readLine();
                        var responseBody : JSONObject? = null
                        responseBody = JSONObject(response)
                        var responseJson  = LoggedInUser(responseBody.getBoolean("success"),
                            responseBody.getString("origin"),
                            responseBody.getString("data"))

                        if(responseJson.success){
                            return Result.Success(responseJson)
                        }
                        else{
                            return Result.Error(IOException("Error: Correo y/o contrseña invalida"))
                        }
                    }
                }
            } catch (e: Exception) {
                System.out.println(e);
                return Result.Error(IOException("Error de Inicio de sesión", e))
            }
        } catch (e: Throwable) {
            return Result.Error(IOException("Error de inicio de sesión", e))
        }
    }

    fun saveNewUser(newUser: User) : Result<LoggedInUser> {
        try {
            var url: URL? = null
            url = URL( "http://192.168.1.100:4000/account/registerNewAccountMobile")
            try {
                with(url.openConnection() as HttpURLConnection) {
                    requestMethod = "POST"
                    setRequestProperty("Accept", "application/json");
                    setRequestProperty("Content-Type", "application/json");
                    doInput = true
                    doOutput = true
                    outputStream.bufferedWriter().use {
                        val responseJson = JSONObject()
                        val passwordsArray = JSONArray()
                        val statusJson = JSONObject()
                        val passwordJson = JSONObject()

                        responseJson.put("Username", newUser.username)
                        responseJson.put("Email", newUser.email)
                        responseJson.put("IsUser", 1)
                        responseJson.put("StatusId", 1)
                        statusJson.put("StatusId", 1)
                        statusJson.put("Name", "Activo")
                        responseJson.put("Status", statusJson)
                        passwordJson.put("PasswordString", newUser.passwords[0].passwordString)
                        passwordsArray.put(passwordJson)
                        responseJson.put("Passwords", passwordsArray)

                        it.write(responseJson.toString());
                        it.flush();
                    }
                    inputStream.bufferedReader().use {
                        val response = it.readLine();
                        var responseBody : JSONObject? = null
                        responseBody = JSONObject(response)
                        var responseJson  = LoggedInUser(responseBody.getBoolean("success"),
                            responseBody.getString("origin"),
                            responseBody.getString("data"))

                        if (responseJson.success) {
                            return Result.Success(responseJson)
                        } else {
                            return Result.Error(IOException("Error: Este correo ya esta afiliado a otra cuenta. Debe usar otro"))
                        }
                    }
                }
            } catch (e: Exception) {
                System.out.println(e);
                return Result.Error(IOException("Error: No se logró registrar la cuenta", e))
            }
        } catch (e: Throwable) {
            return Result.Error(IOException("Error: No se logró registrar la cuenta", e))
        }
    }

    fun cargarAlumnos(listaAlumnos: ArrayList<Alumno>): Result<List<Alumno>> {
        try {
            var url: URL? = null
            url = URL( "http://192.168.3.2:5000/AlumnosServ/listaAlumnos")

            try {
                with(url.openConnection() as HttpURLConnection) {
                    requestMethod = "POST"

                    setRequestProperty("Accept", "application/json");
                    doInput = true
                    doOutput = true
                    outputStream.bufferedWriter().use {
                        it.flush();
                    }
                    inputStream.bufferedReader().use {
                        val respuesta = it.readLine();
                        val jsonRespuesta = JSONObject(respuesta)
                        val jsonAlumnos = jsonRespuesta.getJSONArray("data")
                        for(i in 0..jsonAlumnos.length()-1) {
                            val jsonAlumno  = jsonAlumnos[i] as JSONObject
                            var alumno  = Alumno(
                                jsonAlumno.getInt("id"),
                                jsonAlumno.getString("matricula"),
                                jsonAlumno.getString("nombre"),
                                jsonAlumno.getString("domicilio"),
                                jsonAlumno.getString("telefono"),
                                jsonAlumno.getString("sexo"),
                                jsonAlumno.getString("fecha"),
                                jsonAlumno.getString("matricula")+".jpg")
                            listaAlumnos.add(alumno)
                        }



                        return Result.Success(listaAlumnos)
                    }
                }
            } catch (e: Exception) {
                System.out.println(e);
                return Result.Error(IOException("Error en servicio Web", e))
            }

        } catch (e: Throwable) {
            return Result.Error(IOException("Error en servicio web", e))
        }
    }

    fun guardarAlumno(alumno: Alumno): Result<Boolean> {
        try {
            var url: URL? = null
            url = URL( "http://192.168.3.2:5000/AlumnosServ/GuardarDB")

            try {
                with(url.openConnection() as HttpURLConnection) {
                    requestMethod = "POST"

                    setRequestProperty("Accept", "application/json");
                    setRequestProperty("Content-Type", "application/json");
                    doInput = true
                    doOutput = true
                    outputStream.bufferedWriter().use {
                        var sb = StringBuilder()
                        sb.append("{");
                        sb.append("\"ID\": ");
                        sb.append(alumno.id);
                        sb.append(",");
                        sb.append("\"Matricula\": ");
                        sb.append("\"");
                        sb.append(alumno.matricula);
                        sb.append("\"");
                        sb.append(",");
                        sb.append("\"Nombre\": ");
                        sb.append("\"");
                        sb.append(alumno.nombre);
                        sb.append("\"");
                        sb.append(",");
                        sb.append("\"Domicilio\": ");
                        sb.append("\"");
                        sb.append(alumno.domicilio);
                        sb.append("\"");
                        sb.append(",");
                        sb.append("\"Telefono\": ");
                        sb.append("\"");
                        sb.append(alumno.telefono);
                        sb.append("\"");
                        sb.append(",");
                        sb.append("\"Sexo\": ");
                        sb.append("\"");
                        sb.append(alumno.sexo);
                        sb.append("\"");
                        sb.append(",");
                        sb.append("\"Fecha\": ");
                        sb.append("\"");
                        sb.append(alumno.fechaNac);
                        sb.append("\"");
                        sb.append("}");

                        var data: String = sb.toString()
                        it.write(data);
                        it.flush();
                    }
                    inputStream.bufferedReader().use {
                        val respuesta = it.readLine();
                        var jsonRespuesta : JSONObject? = null
                        jsonRespuesta = JSONObject(respuesta)
                        if (jsonRespuesta.getBoolean("correcto"))
                            return Result.Success(true)
                        else
                            return Result.Error(IOException(jsonRespuesta.getString("mensaje")))
                    }
                }
            } catch (e: Exception) {
                System.out.println(e);
                return Result.Error(IOException("Error de Inicio de sesión", e))
            }

        } catch (e: Throwable) {
            return Result.Error(IOException("Error de inicio de sesión", e))
        }
    }

    fun eliminarAlumno(idAlumno: Int): Result<Boolean> {
        try {
            var url: URL? = null
            url = URL( "http://192.168.3.2:5000/AlumnosServ/EliminarDB")

            try {
                with(url.openConnection() as HttpURLConnection) {
                    requestMethod = "POST"

                    setRequestProperty("Accept", "application/json");

                    doInput = true
                    doOutput = true
                    outputStream.bufferedWriter().use {


                        var data: String = "id="+idAlumno
                        it.write(data);
                        it.flush();
                    }
                    inputStream.bufferedReader().use {
                        val respuesta = it.readLine();
                        var jsonRespuesta : JSONObject? = null
                        jsonRespuesta = JSONObject(respuesta)
                        if (jsonRespuesta.getBoolean("correcto"))
                            return Result.Success(true)
                        else
                            return Result.Error(IOException(jsonRespuesta.getString("mensaje")))
                    }
                }
            } catch (e: Exception) {
                System.out.println(e);
                return Result.Error(IOException("Error de Inicio de sesión", e))
            }

        } catch (e: Throwable) {
            return Result.Error(IOException("Error de inicio de sesión", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }

    fun addFormField(name: String, value: String?, writer: BufferedWriter, boundary: String) {
        writer.append("--$boundary").append("\r\n")
        writer.append("Content-Disposition: form-data; name=\"$name\"")
            .append("\r\n")


        writer.append("\r\n")
        writer.append(value).append("\r\n")
        writer.flush()
    }

    fun enviarFoto(matricula: String, cadBase64: String): Result<Boolean> {
        try {
            var url: URL? = null
            url = URL( "http://192.168.3.2:5000/AlumnosServ/GuardarFoto")

            try {
                with(url.openConnection() as HttpURLConnection) {
                    requestMethod = "POST"

                    val bound = "==="+System.currentTimeMillis()+"==="
                    setRequestProperty("Accept", "application/json");
                    setRequestProperty("Content-Type", "multipart/form-data; boundary="+bound);
                    doInput = true
                    doOutput = true
                    outputStream.bufferedWriter().use {
                        //addFormField("matricula", matricula, it, bound)
                        //addFormField("foto", cadBase64, it, bound)

                        var data = String.format("matricula=%s&foto=%s", matricula, cadBase64)
                        it.write(data);
                        it.flush();
                    }
                    inputStream.bufferedReader().use {
                        val respuesta = it.readLine();
                        var jsonRespuesta : JSONObject? = null
                        jsonRespuesta = JSONObject(respuesta)
                        if (jsonRespuesta.getBoolean("correcto"))
                            return Result.Success(true)
                        else
                            return Result.Error(IOException(jsonRespuesta.getString("mensaje")))
                    }
                }
            } catch (e: Exception) {
                 System.out.println(e);
                return Result.Error(IOException("Error de Inicio de sesión", e))
            }

        } catch (e: Throwable) {
            return Result.Error(IOException("Error de inicio de sesión", e))
        }
    }
}