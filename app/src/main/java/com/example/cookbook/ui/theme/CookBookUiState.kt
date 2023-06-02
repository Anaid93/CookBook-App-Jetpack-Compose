package com.example.cookbook.ui.theme

import com.example.cookbook.model.CookBook
import com.example.cookbook.data.LocalCookBookData

data class CookBookUiState (
    val recipesList: List<CookBook> = emptyList(),
    val currentRecipe: CookBook = LocalCookBookData.defaultRecipe,
    val isShowingListPage: Boolean = true
)