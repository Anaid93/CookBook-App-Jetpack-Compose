package com.example.cookbook.ui.theme

import androidx.lifecycle.ViewModel
import com.example.cookbook.data.LocalCookBookData
import com.example.cookbook.model.CookBook
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update


class CookBookViewModel : ViewModel(){

    private val _uiState = MutableStateFlow(
        CookBookUiState(
            recipesList = LocalCookBookData.getRecipesData(),
            currentRecipe = LocalCookBookData.getRecipesData(). getOrElse(0) {
                LocalCookBookData.defaultRecipe
            }
        )
    )
    val uiState: StateFlow<CookBookUiState> = _uiState

    fun updateCurrentRecipe(selectedRecipe: CookBook) {
        _uiState.update {
            it.copy(currentRecipe = selectedRecipe)
        }
    }

    fun navigateToListPage(){
        _uiState.update {
            it.copy(isShowingListPage = true)
        }
    }

    fun navigateToDetailPage(){
        _uiState.update {
            it.copy(isShowingListPage = false)
        }
    }
}
