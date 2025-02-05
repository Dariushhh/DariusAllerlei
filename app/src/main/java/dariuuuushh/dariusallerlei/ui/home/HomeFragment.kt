package dariuuuushh.dariusallerlei.ui.home

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout
import dariuuuushh.dariusallerlei.R
import dariuuuushh.dariusallerlei.databinding.FragmentHomeBinding
import java.util.Calendar
import java.util.Date

class HomeFragment : Fragment() {

    data class TodoRow(
        val row: TableRow,
        val inputLayout: TextInputLayout,
        var isStartIconVisible: Boolean
    )

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val todoRows = mutableListOf<TodoRow>()
    private val date = Date()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }


        val toDoTable: TableLayout = binding.toDoList
        val todoDateButton = binding.todoDateButton
        todoDateButton.setOnClickListener {
            showDatePicker()
        }
        val addTableRow = binding.addTableRow
        val todoAddButton = binding.todoAddButton
        todoAddButton.setOnClickListener{
            addNewTodoRow()
            val index = toDoTable.indexOfChild(addTableRow)
            if (index < toDoTable.childCount - 1) {
                toDoTable.removeViewAt(index)
                toDoTable.addView(addTableRow, index + 1)
            }
        }
        return root
    }

    private fun addNewTodoRow() {
        val toDoTable: TableLayout = binding.toDoList

        val inflater = LayoutInflater.from(requireContext())
        val newRow = inflater.inflate(R.layout.todo_table_row, toDoTable, false) as TableRow

        val todoTextInputLayout = newRow.findViewById<TextInputLayout>(R.id.todo_input_layout)
        // val todoTextInputEditText = newRow.findViewById<TextInputEditText>(R.id.todo_input_edit_text)

        todoTextInputLayout.startIconDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_start_icon)
        // todoTextInputLayout.endIconMode = TextInputLayout.END_ICON_CUSTOM
        // todoTextInputLayout.endIconDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_end_icon)

        val todoRow = TodoRow(newRow, todoTextInputLayout, true)
        todoRows.add(todoRow)
        toDoTable.addView(newRow)
        setupIconListeners(todoRow)
    }

    private fun setupIconListeners(todoRow: TodoRow) {
        todoRow.inputLayout.setStartIconOnClickListener {
            toggleToEndIcon(todoRow)
        }
    }

    private fun toggleToStartIcon(todoRow: TodoRow) {
        todoRow.inputLayout.endIconMode = TextInputLayout.END_ICON_NONE
        todoRow.inputLayout.startIconDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_start_icon)
        todoRow.inputLayout.setStartIconOnClickListener {
            toggleToEndIcon(todoRow)
        }
    }

    private fun toggleToEndIcon(todoRow: TodoRow) {
        todoRow.inputLayout.startIconDrawable = null
        todoRow.inputLayout.endIconMode = TextInputLayout.END_ICON_CUSTOM
        todoRow.inputLayout.endIconDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_end_icon)
        todoRow.inputLayout.setEndIconOnClickListener {
            toggleToStartIcon(todoRow)
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
            val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
        }, year, month, day)

        datePickerDialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}