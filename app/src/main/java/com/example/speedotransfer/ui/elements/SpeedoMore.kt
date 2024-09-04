package com.example.speedotransfer.ui.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import com.example.speedotransfer.ui.theme.Gray200
import com.example.speedotransfer.ui.theme.Gray40

@Composable
fun SpeedoMore(icon:Int,text:String,modifier: Modifier = Modifier,onClick:()->Unit= {},isFinal:Boolean = false) {
    Column(modifier=modifier.clickable { onClick() }) {
        Row(verticalAlignment = Alignment.CenterVertically,modifier=Modifier.padding(vertical = 16.dp)) {
            //TODO add font Poppins
            Icon(painter = painterResource(id = icon), contentDescription = text,modifier=Modifier.size(24.dp), tint = Gray200)
            Text(text = text,fontSize=16.sp,fontWeight= FontWeight.W500,modifier=Modifier.padding(start = 8.dp),color= Gray200)
            Spacer(modifier = Modifier.weight(1f))
            if(!isFinal)
                Icon(painter = painterResource(id = R.drawable.next_arrow), contentDescription = "Next" , tint = Gray200)
        }
        if (!isFinal)
            HorizontalDivider(color = Gray40 )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun SpeedoMoreComponent() {
    Column(horizontalAlignment = Alignment.CenterHorizontally,modifier=Modifier.fillMaxWidth()) {
        SpeedoMore(icon = R.drawable.website, text = "Transfer From Website",modifier=Modifier.fillMaxWidth(0.9f))

    }
}
