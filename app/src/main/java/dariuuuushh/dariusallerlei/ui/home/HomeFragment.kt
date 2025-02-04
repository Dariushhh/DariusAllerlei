package dariuuuushh.dariusallerlei.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dariuuuushh.dariusallerlei.R
import dariuuuushh.dariusallerlei.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

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

        val nameTextInput: TextInputLayout = binding.nameTextInput
        nameTextInput.apply {
            setPadding(16, 16, 16, 16)
            hint = "Test"
        }

        val toDoTable: TableLayout = binding.toDoList
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
        val addTableRow = binding.addTableRow

        val inflater = LayoutInflater.from(requireContext())
        val newRow = inflater.inflate(R.layout.todo_table_row, toDoTable, false) as TableRow

        val todoTextInputLayout = newRow.findViewById<TextInputLayout>(R.id.todo_input_layout)
        // val todoTextInputEditText = newRow.findViewById<TextInputEditText>(R.id.todo_input_edit_text)

        todoTextInputLayout.startIconDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_start_icon)

        toDoTable.addView(newRow)

        todoTextInputLayout.setStartIconOnClickListener {
            todoTextInputLayout.startIconDrawable = null
            todoTextInputLayout.post {
                todoTextInputLayout.endIconDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_end_icon)
            }

            Toast.makeText(requireContext(), "Start icon clicked", Toast.LENGTH_SHORT).show()
        }

        todoTextInputLayout.setEndIconOnClickListener {
            todoTextInputLayout.endIconDrawable = null
            todoTextInputLayout.post {
                todoTextInputLayout.startIconDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_start_icon)
            }
            Toast.makeText(requireContext(), "End icon clicked", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}