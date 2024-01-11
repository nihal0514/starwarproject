package com.example.starwarproject.model


data class FilterState(
    val isNameChecked: Boolean,
    val isGenderChecked: Boolean,
    val isCreatedChecked: Boolean,
    val isUpdatedChecked: Boolean
)