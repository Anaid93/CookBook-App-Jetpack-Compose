package com.example.cookbook.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.cookbook.R


val Lobster = FontFamily(
    Font(R.font.lobster_regular)
)

val LobsterTwo = FontFamily(
    Font(R.font.lobster_two_regular)
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = Lobster,
        fontWeight = FontWeight.Normal,
        fontSize = 35.sp
    ),
    displayMedium = TextStyle(
        fontFamily = Lobster,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp
    ),
    displaySmall = TextStyle(
        fontFamily = Lobster,
        fontWeight = FontWeight.Bold,
        fontSize = 27.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = LobsterTwo,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = LobsterTwo,
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp
    ),
    bodySmall = TextStyle(
        fontFamily = LobsterTwo,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp
    ),
)