package com.example.cookbook.data

import com.example.cookbook.model.CookBook
import com.example.cookbook.R

object LocalCookBookData {
    val defaultRecipe = getRecipesData()[0]

    fun getRecipesData(): List<CookBook>{
        return listOf(
            CookBook(
                id = 1,
                titleResourceId = R.string.classic_stuffed_peppers,
                imageResourceId = R.drawable.classic_stuffed_peppers,
                imageBanner = R.drawable.classic_stuffed_peppers_banner,
                ingredients = R.string.ingredients,
                recipeDetails = R.string.recipe_detail_text
            ),
            CookBook(
                id = 2,
                titleResourceId = R.string.creamy_tuscan_salmon,
                imageResourceId = R.drawable.creamy_tuscan_salmon,
                imageBanner = R.drawable.creamy_tuscan_salmon_banner,
                ingredients = R.string.ingredients,
                recipeDetails = R.string.recipe_detail_text
            ),
            CookBook(
                id = 3,
                titleResourceId = R.string.frittata_affogato,
                imageResourceId = R.drawable.frittata_affogato,
                imageBanner = R.drawable.frittata_affogato_banner,
                ingredients = R.string.ingredients,
                recipeDetails = R.string.recipe_detail_text
            ),
            CookBook(
                id = 4,
                titleResourceId = R.string.grilled_tomatoes,
                imageResourceId = R.drawable.grilled_tomatoes,
                imageBanner = R.drawable.grilled_tomatoes_banner,
                ingredients = R.string.ingredients,
                recipeDetails = R.string.recipe_detail_text
            ),
            CookBook(
                id = 5,
                titleResourceId = R.string.hawaiian_garlic_shrimp,
                imageResourceId = R.drawable.hawaiian_garlic_shrimp,
                imageBanner = R.drawable.hawaiian_garlic_shrimp_banner,
                ingredients = R.string.ingredients,
                recipeDetails = R.string.recipe_detail_text
            ),
            CookBook(
                id = 6,
                titleResourceId = R.string.key_lime_pie,
                imageResourceId = R.drawable.key_lime_pie_mousse,
                imageBanner = R.drawable.key_lime_pie_mousse_banner,
                ingredients = R.string.ingredients,
                recipeDetails = R.string.recipe_detail_text
            ),
            CookBook(
                id = 7,
                titleResourceId = R.string.pumpkin_cheesecake,
                imageResourceId = R.drawable.pumpkin_cheesecake,
                imageBanner = R.drawable.pumpkin_cheesecake_banner,
                ingredients = R.string.ingredients,
                recipeDetails = R.string.recipe_detail_text
            ),
            CookBook(
                id = 8,
                titleResourceId = R.string.caramel_apple_pie,
                imageResourceId = R.drawable.salted_caramel_apple_pie_1,
                imageBanner = R.drawable.salted_caramel_apple_pie_1_banner,
                ingredients = R.string.ingredients,
                recipeDetails = R.string.recipe_detail_text
            ),
            CookBook(
                id = 9,
                titleResourceId = R.string.caramel_chocolate,
                imageResourceId = R.drawable.salted_caramel_chocolate_fudge_cake,
                imageBanner = R.drawable.salted_caramel_chocolate_fudge_cake_banner,
                ingredients = R.string.ingredients,
                recipeDetails = R.string.recipe_detail_text
            ),
            CookBook(
                id = 10,
                titleResourceId = R.string.skillet_enchilada,
                imageResourceId = R.drawable.skillet_enchilada_meatballs,
                imageBanner = R.drawable.skillet_enchilada_meatballs_banner,
                ingredients = R.string.ingredients,
                recipeDetails = R.string.recipe_detail_text
            ),
            CookBook(
                id = 11,
                titleResourceId = R.string.vegan_lasagna,
                imageResourceId = R.drawable.vegan_lasagna,
                imageBanner = R.drawable.vegan_lasagna_banner,
                ingredients = R.string.ingredients,
                recipeDetails = R.string.recipe_detail_text
            ),
        )
    }
}