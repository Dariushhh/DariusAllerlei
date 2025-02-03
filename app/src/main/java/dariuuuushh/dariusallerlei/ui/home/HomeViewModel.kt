package dariuuuushh.dariusallerlei.ui.home

import android.widget.TableLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "ToDo-List"
    }
    val text: LiveData<String> = _text

}