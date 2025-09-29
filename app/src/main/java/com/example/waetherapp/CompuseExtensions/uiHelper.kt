package com.example.waetherapp.CompuseExtensions

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomText(text : String,
               size: TextUnit = 20.sp,
//             modifier: Modifier = Modifier.background(Color.White)
){
    Text(
        text = text,
        fontSize = size,
//        modifier = modifier
        color = Color.White
    )
}

@Composable
fun Height(height : Int){
    Spacer(modifier = Modifier.height(height.dp))
}