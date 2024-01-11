package com.example.starwarproject.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import com.example.starwarproject.R
import com.example.starwarproject.model.FilterState
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BottomSheetFragment (private val filterListener: FilterListener): BottomSheetDialogFragment() {

    private lateinit var checkBoxName: CheckBox
    private lateinit var checkBoxGender: CheckBox
    private lateinit var checkBoxCreated: CheckBox
    private lateinit var checkBoxUpdated: CheckBox
    private lateinit var buttonApply: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.bottom_sheet_layout, container, false)

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkBoxName= view.findViewById<View>(R.id.checkBoxName) as CheckBox
        checkBoxGender = view.findViewById<View>(R.id.checkBoxGender) as CheckBox
        checkBoxCreated = view.findViewById<View>(R.id.checkBoxCreated) as CheckBox
        checkBoxUpdated = view.findViewById<View>(R.id.checkBoxUpdated) as CheckBox
        buttonApply = view.findViewById<View>(R.id.buttonApply) as Button


        buttonApply.setOnClickListener {
            val isCheckBoxNameChecked = checkBoxName.isChecked
            val isCheckBoxGenderChecked = checkBoxGender.isChecked
            val isCheckBoxNameCreated = checkBoxCreated.isChecked
            val isCheckBoxNameUpdated = checkBoxUpdated.isChecked

            val filterState = FilterState(

                 isNameChecked = checkBoxName.isChecked,
                isGenderChecked = checkBoxGender.isChecked,
                isCreatedChecked = checkBoxCreated.isChecked,
                isUpdatedChecked = checkBoxUpdated.isChecked
            )
            filterListener.onFilterApplied(filterState)
            dismiss()
        }

    }
    interface FilterListener{
        fun onFilterApplied(filterState: FilterState)
    }

}
