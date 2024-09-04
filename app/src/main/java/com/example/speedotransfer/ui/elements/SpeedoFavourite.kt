package com.example.speedotransfer.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.theme.Gray100
import com.example.speedotransfer.ui.theme.Gray900
import com.example.speedotransfer.ui.theme.Primary50

@Composable
fun SpeedoFavourite(name: String,account:String,onUpdateClick:()->Unit={},onDeleteClick:()->Unit={},modifier: Modifier = Modifier) {
    Surface(modifier=modifier, color = Primary50, shape = RoundedCornerShape(8.dp)) {
        Row (verticalAlignment = Alignment.CenterVertically,modifier=Modifier.padding(16.dp)){
            Image(painter = painterResource(id = R.drawable.bank), contentDescription = "Bank Icon")
            Column(modifier=Modifier.padding(start=8.dp)) {
                Text(text = name ,fontSize=16.sp,fontWeight= FontWeight.W500, color = Gray900,modifier=Modifier.padding(bottom=8.dp))
                Text(text = account ,fontSize=16.sp, fontWeight = FontWeight.W400,color= Gray100)
            }
            Spacer(modifier = Modifier.weight(1f))
            Image(painter = painterResource(id = R.drawable.edit), contentDescription = "Edit",modifier=Modifier.padding(end=16.dp).clickable { onUpdateClick() })
            Image(painter = painterResource(id = R.drawable.delete), contentDescription = "Delete",modifier=Modifier.padding(start=16.dp).clickable { onDeleteClick() })
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun SpeedoFavouritePreview() {
    SpeedoFavourite("Asmaa Dosuky","Account xxxx7890",modifier=Modifier.fillMaxWidth(0.9f))
}