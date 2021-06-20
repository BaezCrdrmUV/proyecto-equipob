package fei.uv.mx.primeraapp.ui.slideshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SlideshowViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Consulta de canciones de biblioteca aun no implementada"
    }
    val text: LiveData<String> = _text
}