package dariuuuushh.dariusallerlei.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class TodoItem(
    val id: Int,
    var text: String,
    var isStartIconVisible: Boolean,
)

class HomeViewModel : ViewModel() {

    private val _todos = MutableLiveData<MutableList<TodoItem>>().apply {
        value = mutableListOf()
    }
    // val todos: LiveData<MutableList<TodoItem>> = _todos

    private val _text = MutableLiveData<String>().apply {
        value = "ToDo-List"
    }
    val text: LiveData<String> = _text

//    fun addTodoItem(todoItem: TodoItem) {
//        _todos.value?.add(todoItem)
//        _todos.value = _todos.value
//    }
//
//    fun updateTodoItem(todoItem: TodoItem) {
//        _todos.value = _todos.value
//    }
}