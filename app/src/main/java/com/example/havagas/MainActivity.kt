package com.example.havagas

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.AdapterView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.example.havagas.databinding.ActivityMainBinding
import com.example.havagas.models.Form

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null) {
            retrieveState(savedInstanceState)
        }

        addInputListeners()
        addButtonListeners()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        outState.putBoolean("shouldJoinMailListCheckBox", binding.shouldJoinMailListCheckBox.isChecked)
        outState.putString("mailInput", binding.mailInput.text.toString())
        outState.putInt("phoneRadioGroup", binding.phoneRadioGroup.checkedRadioButtonId)
        outState.putString("fullNameInput", binding.fullNameInput.text.toString())
        outState.putString("birthDateInput", binding.birthDateInput.text.toString())
        outState.putString("cellPhoneNumberInput", binding.cellPhoneNumberInput.text.toString())
        outState.putInt("educationSpinner", binding.educationSpinner.selectedItemPosition)
        outState.putString("phoneInput", binding.phoneInput.text.toString())
        outState.putString("monographInput", binding.monographInput.text.toString())
        outState.putString("vacancyInterest", binding.vacancyInterest.text.toString())
        outState.putInt("genderRadioGroup", binding.genderRadioGroup.checkedRadioButtonId)
        outState.putString("graduationYearInput", binding.graduationYearInput.text.toString())
        outState.putString("institutionInput", binding.institutionInput.text.toString())
        super.onSaveInstanceState(outState, outPersistentState)
    }

    private fun retrieveState(savedInstanceState: Bundle) {
        val fullNameInput = savedInstanceState.getString(
            "fullNameInput",
            binding.fullNameInput.text.toString()
        )
        val mailInput = savedInstanceState.getString(
            "mailInput",
            binding.mailInput.text.toString()
        )
        val shouldJoinMailListCheckBox = savedInstanceState.getBoolean(
            "shouldJoinMailListCheckBox",
            binding.shouldJoinMailListCheckBox.isChecked
        )
        val phoneInput = savedInstanceState.getString(
            "phoneInput",
            binding.phoneInput.text.toString()
        )
        val phoneRadioGroup = savedInstanceState.getInt(
            "phoneRadioGroup",
            binding.phoneRadioGroup.checkedRadioButtonId
        )
        val cellPhoneNumberInput = savedInstanceState.getString(
            "cellPhoneNumberInput",
            binding.cellPhoneNumberInput.text.toString()
        )
        val genderRadioGroup = savedInstanceState.getInt(
            "genderRadioGroup",
            binding.genderRadioGroup.checkedRadioButtonId
        )
        val birthDateInput = savedInstanceState.getString(
            "birthDateInput",
            binding.birthDateInput.text.toString()
        )
        val educationSpinner = savedInstanceState.getInt(
            "educationSpinner",
            binding.educationSpinner.selectedItemPosition
        )
        val graduationYearInput = savedInstanceState.getString(
            "graduationYearInput",
            binding.graduationYearInput.text.toString()
        )
        val institutionInput = savedInstanceState.getString(
            "institutionInput",
            binding.institutionInput.text.toString()
        )
        val monographInput = savedInstanceState.getString(
            "monographInput",
            binding.monographInput.text.toString()
        )
        val vacancyInterest = savedInstanceState.getString(
            "vacancyInterest",
            binding.vacancyInterest.text.toString()
        )

        binding.fullNameInput.append(fullNameInput)
        binding.mailInput.append(mailInput)
        binding.shouldJoinMailListCheckBox.isChecked = shouldJoinMailListCheckBox
        binding.phoneInput.append(phoneInput)
        binding.phoneRadioGroup.check(phoneRadioGroup)
        binding.cellPhoneNumberInput.append(cellPhoneNumberInput)
        binding.genderRadioGroup.check(genderRadioGroup)
        binding.birthDateInput.append(birthDateInput)
        binding.educationSpinner.setSelection(educationSpinner)
        binding.graduationYearInput.append(graduationYearInput)
        binding.institutionInput.append(institutionInput)
        binding.monographInput.append(monographInput)
        binding.vacancyInterest.append(vacancyInterest)
    }

    private fun addButtonListeners() {
        binding.clearButton.setOnClickListener {
            binding.fullNameInput.text.clear()
            binding.mailInput.text.clear()
            binding.addCellPhoneNumberRadioGroup.clearCheck()
            binding.phoneInput.text.clear()
            binding.cellPhoneNumberInput.visibility = View.GONE
            binding.birthDateInput.text.clear()
            binding.institutionInput.text.clear()
            binding.graduationYearInput.visibility = View.GONE
            binding.shouldJoinMailListCheckBox.isChecked = false
            binding.phoneRadioGroup.clearCheck()
            binding.genderRadioGroup.clearCheck()
            binding.educationSpinner.setSelection(0)
            binding.graduationYearInput.text.clear()
            binding.monographInput.visibility = View.GONE
            binding.cellPhoneNumberInput.text.clear()
            binding.monographInput.text.clear()
            binding.institutionInput.visibility = View.GONE
            binding.vacancyInterest.text.clear()
        }

        binding.confirmButton.setOnClickListener {
            val form = Form(
                fullNameInput = binding.fullNameInput.text.toString(),
                mailInput = binding.mailInput.text.toString(),
                cellPhoneNumberInput = binding.cellPhoneNumberInput.text.toString(),
                phoneInput = binding.phoneInput.text.toString(),
                phoneRadioGroup = getRadioGroupValue(binding.phoneRadioGroup),
                shouldJoinMailListCheckBox = binding.shouldJoinMailListCheckBox.isChecked.toString(),
                genderRadioGroup = getRadioGroupValue(binding.genderRadioGroup),
                birthDateInput = binding.birthDateInput.text.toString(),
                graduationYearInput = binding.graduationYearInput.text.toString(),
                educationSpinner = binding.educationSpinner.selectedItem.toString(),
                institutionInput = binding.institutionInput.text.toString(),
                monographInput = binding.monographInput.text.toString(),
                vacancyInterest = binding.vacancyInterest.text.toString(),
            )

            Toast.makeText(this, form.toString(), Toast.LENGTH_LONG).show()
        }
    }

    private fun getRadioGroupValue(radioGroup: RadioGroup): String {
        val id = radioGroup.checkedRadioButtonId
        return radioGroup.findViewById<RadioButton>(id).text.toString()
    }

    private fun addInputListeners() {
        binding.addCellPhoneNumberRb.setOnCheckedChangeListener { radioButton, _ ->
            if (radioButton.isChecked) {
                binding.cellPhoneNumberInput.visibility = View.VISIBLE
            }
        }

        binding.notAddCellPhoneNumberRb.setOnCheckedChangeListener { radioButton, _ ->
            if (radioButton.isChecked) {
                binding.cellPhoneNumberInput.visibility = View.GONE
            }
        }

        binding.educationSpinner.onItemSelectedListener =
            object : Activity(), AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    pos: Int,
                    id: Long
                ) {
                    val education = parent.getItemAtPosition(pos).toString()
                    val graduationYearInput = binding.graduationYearInput
                    val institutionInput = binding.institutionInput
                    val monographInput = binding.monographInput

                    when (education) {
                        "Fundamental", "Médio" -> {
                            graduationYearInput.visibility = View.VISIBLE
                            institutionInput.visibility = View.GONE
                            monographInput.visibility = View.GONE
                        }
                        "Graduação", "Especialização" -> {
                            graduationYearInput.visibility = View.VISIBLE
                            institutionInput.visibility = View.VISIBLE
                            monographInput.visibility = View.GONE
                        }
                        else -> {
                            graduationYearInput.visibility = View.VISIBLE
                            institutionInput.visibility = View.VISIBLE
                            monographInput.visibility = View.VISIBLE
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
    }
}