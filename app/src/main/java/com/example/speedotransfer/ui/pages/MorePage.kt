package com.example.speedotransfer.ui.pages

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.waterfall
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.speedotransfer.ui.elements.SpeedoMore
import com.example.speedotransfer.ui.elements.SpeedoTitleCard
import com.example.speedotransfer.R
import com.example.speedotransfer.navigation.Route
import com.example.speedotransfer.ui.elements.BottomNavigationBar
import com.example.speedotransfer.ui.theme.PinkGradientEnd
import com.example.speedotransfer.ui.theme.Primary300
import com.example.speedotransfer.ui.theme.YellowGradientStart
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MorePage(navController: NavController,modifier: Modifier = Modifier) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()
    var selectedItem by remember { mutableStateOf(4) }
    Scaffold(content={innerPadding->
        Log.d("trace", innerPadding.toString())
        Column(modifier= modifier
            .fillMaxSize()
            .background(Brush.linearGradient(0.0f to YellowGradientStart, 1.0f to PinkGradientEnd)).padding(innerPadding).padding(top=51.809525.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            SpeedoTitleCard(title = "More",navController,modifier=Modifier.fillMaxWidth(0.9f))
            SpeedoMore(icon = R.drawable.website, text = "Transfer From Website",modifier= Modifier
                .padding(top = 24.dp)
                .fillMaxWidth(0.9f))
            SpeedoMore(icon = R.drawable.favorite, text = "Favourites",modifier=Modifier.fillMaxWidth(0.9f).clickable { navController.navigate(Route.FAVOURITES) })
            SpeedoMore(icon = R.drawable.person, text = "Profile",modifier=Modifier.fillMaxWidth(0.9f).clickable { navController.navigate(
                Route.PROFILE) })
            SpeedoMore(icon = R.drawable.danger, text = "Help",modifier= Modifier.clickable { showBottomSheet = true }
                .fillMaxWidth(0.9f))
            SpeedoMore(icon = R.drawable.logout, text = "Logout", isFinal = true,modifier=Modifier.fillMaxWidth(0.9f).clickable { navController.navigate(Route.SIGN_IN) })
        }
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState,
                shape = RoundedCornerShape(
                    topStart = 50.dp,
                    topEnd = 50.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                ),
                windowInsets = WindowInsets.waterfall,
                containerColor = Color.White
            ){
                Row (modifier= Modifier
                    .padding(vertical = 100.dp)
                    .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
                    Card(colors = CardDefaults.cardColors(containerColor = Color.White), elevation = CardDefaults.elevatedCardElevation(2.dp),modifier=Modifier.clickable {  }) {
                        Column(modifier=Modifier.padding(start=21.dp,end=21.dp,top=14.dp,bottom=33.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                            Image(painter = painterResource(id = R.drawable.mail), contentDescription = "Mail us")
                            Text(text = "Send Email",modifier=Modifier.padding(top=15.dp))
                        }
                    }
                    Card(colors = CardDefaults.cardColors(containerColor = Color.White), elevation = CardDefaults.elevatedCardElevation(2.dp),modifier= Modifier
                        .padding(start = 32.dp)
                        .clickable { }) {
                        Column(modifier=Modifier.padding(start=32.5.dp,end=32.5.dp,top=14.dp,bottom=14.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                            Image(
                                painter = painterResource(id = R.drawable.call),
                                contentDescription = "Call us"
                            )
                            Text(text = "Call Us",modifier=Modifier.padding(top=15.dp))
                            Text(text = "000000", color = Primary300)
                        }
                    }
                }
            }}
    }, bottomBar = { BottomNavigationBar(navController,selectedItem = selectedItem, onItemSelected = { index -> selectedItem = index }) })}




@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun MorePagePreview() {
//    MorePage()
}