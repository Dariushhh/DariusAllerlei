package dariuuuushh.dariusallerlei.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
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

        val toDoTable: TableLayout = binding.toDoList
        val firstToDo = binding.firstToDo
        val firstAddButton = binding.firstAddButton
        firstAddButton.setOnClickListener{addNewTodoRow()}
        return root
    }

    private fun addNewTodoRow() {
        val toDoTable: TableLayout = binding.toDoList
        val newRow = TableRow(requireContext()).apply {  setPadding(16, 16, 16, 16) }

        val todoTextInputLayout = TextInputLayout(requireContext(), null, com.google.android.material.R.attr.textInputOutlinedStyle).apply {
            layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT,
                1f
            )
            hint = "Neue Aufgabe"
        }

        val todoTextInputEditText = TextInputEditText(requireContext()).apply {
            textSize = 18f
        }

        todoTextInputLayout.addView(todoTextInputEditText)
        val todoCheckBox = MaterialCheckBox(requireContext())

        newRow.addView(todoCheckBox)
        newRow.addView(todoTextInputLayout)

        toDoTable.addView(newRow)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}