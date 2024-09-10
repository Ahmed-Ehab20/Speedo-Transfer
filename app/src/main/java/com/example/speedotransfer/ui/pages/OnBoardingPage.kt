package com.example.speedotransfer.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.elements.SpeedoButton
import com.example.speedotransfer.ui.theme.Gray900
import com.example.speedotransfer.ui.theme.PinkGradientEnd
import com.example.speedotransfer.ui.theme.Primary300
import com.example.speedotransfer.ui.theme.Primary75
import com.example.speedotransfer.ui.theme.YellowGradientStart

@Composable
fun OnBoardingPage(modifier: Modifier = Modifier) {
    var index by remember { mutableStateOf(0) }
    val images= listOf(R.drawable.onboarding_1,R.drawable.onboarding_2,R.drawable.onboarding_3)
    val titles= listOf("Amount","Confirmation","Payment")
    val texts= listOf("Send money fast with simple steps. Create account, Confirmation, Payment. Simple.","Transfer funds instantly to friends and family worldwide, strong shield protecting a money.","Enjoy peace of mind with our secure platform Transfer funds instantly to friends.")
    Column(modifier= modifier
        .fillMaxSize()
        .background(Brush.linearGradient(0.0f to YellowGradientStart, 1.0f to PinkGradientEnd))
        .padding(top = 78.dp), horizontalAlignment = Alignment.CenterHorizontally) {

        TextButton(onClick = {  },modifier=Modifier.align(Alignment.End).padding(bottom=15.dp)) {
            Text(text = "Skip",color=Gray900)
        }
        Image(painter = painterResource(id = images[index]), contentDescription = "Onboarding Image",modifier=Modifier.padding(bottom=45.dp).size(350.dp))
        Row(modifier=Modifier.fillMaxWidth().padding(bottom=16.dp), horizontalArrangement = Arrangement.Center) {
            Surface(color = if(index==0) Primary300 else Primary75,shape= CircleShape,modifier=modifier.padding(end=8.dp).size(16.dp)) {

            }
            Surface(color = if(index==1) Primary300 else Primary75,shape= CircleShape,modifier=modifier.padding(end=8.dp).size(16.dp)) {

            }
            Surface(color = if(index==2) Primary300 else Primary75,shape= CircleShape,modifier=modifier.size(16.dp)) {

            }
        }
        Text(text = titles[index], fontSize = 24.sp, fontWeight = FontWeight.W600,modifier=Modifier.padding(bottom = 16.dp))
        Text(text = texts[index],textAlign = TextAlign.Center, fontSize = 16.sp, fontWeight = FontWeight.W400,modifier=Modifier.padding(bottom = 32.dp).fillMaxWidth(0.9f))
        SpeedoButton(label = "Next", onClick = { if(index<2) index++ else /*TODO*/index},modifier=Modifier.fillMaxWidth(0.9f))
    }
}

@Preview
@Composable
private fun OnBoardingPagePreview() {
    OnBoardingPage()
}