package fei.uv.mx.primeraapp.data.model

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedInUser(
    val success: Boolean,
    val origin: String,
    val data: Any
)