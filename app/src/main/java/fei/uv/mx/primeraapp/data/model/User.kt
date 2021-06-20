package fei.uv.mx.primeraapp.data.model

class User (
    val accountId: String,
    val username: String,
    val email: String,
    val isUser: Int,
    val statusId: Int,
    val status : Array<Status>,
    val passwords : Array<Password>
)