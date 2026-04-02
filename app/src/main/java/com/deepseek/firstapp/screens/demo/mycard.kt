package com.deepseek.firstapp.screens.demo
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.deepseek.firstapp.R

@Composable
fun MyCardExample(){
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(180.dp),
        colors= CardDefaults.cardColors(
            containerColor=Color.Cyan,
            contentColor = Color.Red,
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(16.dp)
    ){
        Box(){
            Image(painter = painterResource(id=R.drawable.company),
                contentDescription="logo",
                modifier= Modifier.fillMaxWidth(),
                )
            
        }
        Text("Hello")

    }
}
@Preview
@Composable
fun cardpreview(){
    MyCardExample()
}