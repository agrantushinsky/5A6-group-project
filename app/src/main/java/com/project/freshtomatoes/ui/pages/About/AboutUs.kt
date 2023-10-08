package com.project.freshtomatoes.ui.pages.About

import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.freshtomatoes.R
import com.project.freshtomatoes.ui.layout.MainLayout

@Composable
fun AboutUs(modifier : Modifier = Modifier) {
    MainLayout ()
    {
        Text(text ="About Us", fontSize = 48.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())

        Text(text = "Our goal is to provide users a dynamic application to rank movies they have watched.",
            modifier = Modifier.padding(16.dp))

        Spacer(Modifier.height(10.dp))

        Text(text = "Creators:", fontSize = 36.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())

        Spacer(Modifier.height(10.dp))

        Row(Modifier.padding(16.dp))
        {
            Column (horizontalAlignment = Alignment.CenterHorizontally){
                Text(text = "Aidan")
                Image(modifier = Modifier.size(100.dp).padding(5.dp) ,
                    painter = painterResource(id = R.drawable.aidan),
                    contentDescription = "Aidan",
                    contentScale = ContentScale.Crop)
            }
            Spacer(Modifier.width(20.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally){
                Text("Nitpreet")
                Image(modifier = Modifier.size(100.dp).padding(5.dp),
                    painter = painterResource(id = R.drawable.nit),
                    contentDescription = "Nitpreet",
                    contentScale = ContentScale.Crop)
            }
            Spacer(Modifier.width(20.dp))
            Column (horizontalAlignment = Alignment.CenterHorizontally){
                Text("Jose")
                Image(modifier = Modifier.size(100.dp).padding(5.dp),
                    painter = painterResource(id = R.drawable.jose),
                    contentDescription = "Jose",
                    contentScale = ContentScale.Crop)
            }
        }
    }

}