package com.example.draw_app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun BottomPanel(onClick: (Color) -> Unit,
                onLineWidthChange: (Float) -> Unit,
                onBackClick: () -> Unit,
                onCapClick: (StrokeCap) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.DarkGray),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ColorList{ color ->
            onClick(color)
        }
        CustomSlider { lineWidth ->
            onLineWidthChange(lineWidth)
        }
        ButtonPanel({
            onBackClick()
        }) {cap ->
            onCapClick(cap)
        }
    }
}

@Composable
fun ColorList(onClick: (Color) -> Unit){
    val colors = listOf(
        Color.Black,
        Color.Gray,
        Color.White,
        Color.Blue,
        Color.Cyan,
        Color.Green,
        Color.Red,
        Color.Yellow,
        Color.Magenta
    )

    LazyRow(
        modifier = Modifier.padding(10.dp)
    ){
        items(colors){ color ->
            Box(modifier = Modifier
                .padding(10.dp)
                .clickable {
                    onClick(color)
                }
                .size(40.dp)
                .background(color, CircleShape)
                .border(2.dp, Color.White, CircleShape)
            )
        }
    }
}

@Composable
fun CustomSlider(onChange: (Float) -> Unit){
    var position by remember{
        mutableStateOf(0.05f)
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Line width: ${(position * 100).toInt()}",
            color = Color.White)
        Slider(value = position, onValueChange = {
            val tempPos = if(it>0) it else 0.01f
            position = tempPos
            onChange(tempPos*100)
        })
    }
}

@Composable
fun ButtonPanel(onClick: () -> Unit, onCapClick: (StrokeCap) -> Unit){
    Row(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
        verticalAlignment = Alignment.CenterVertically) {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(start = 10.dp, end = 10.dp),
        ){
            IconButton( modifier = Modifier
                .clip(CircleShape)
                .background(color = Color.White),
                onClick = {
                    onClick()
                }) {
                Icon(Icons.Default.ArrowBack,
                    contentDescription = null)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            IconButton( modifier = Modifier
                .clip(CircleShape)
                .background(color = Color.White),
                onClick = {
                    onCapClick(StrokeCap.Square)
                }) {
                Icon(Icons.Default.ArrowDropDown,
                    contentDescription = null)
            }
            IconButton( modifier = Modifier
                .clip(CircleShape)
                .background(color = Color.White),
                onClick = {
                    onCapClick(StrokeCap.Square)
                }) {
                Icon(Icons.Default.ArrowDropDown,
                    contentDescription = null)
            }
            IconButton( modifier = Modifier
                .clip(CircleShape)
                .background(color = Color.White),
                onClick = {
                    onCapClick(StrokeCap.Butt)
                }) {
                Icon(Icons.Default.ArrowDropDown,
                    contentDescription = null)
            }
        }
    }


}