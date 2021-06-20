package fei.uv.mx.primeraapp.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GalleryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Gestión de biblioteca privada aun no implementado"
    }
    val text: LiveData<String> = _text
}