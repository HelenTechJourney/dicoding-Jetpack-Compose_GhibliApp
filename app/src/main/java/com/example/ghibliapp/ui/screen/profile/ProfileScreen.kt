package com.example.ghibliapp.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ghibliapp.R
import com.example.ghibliapp.ui.theme.GhibliAppTheme

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.helen_jiffri),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .paddingFromBaseline(top = 60.dp, bottom = 16.dp)
                .size(240.dp)
                .clip(CircleShape)
        )
        Text(
            text = stringResource(R.string.ownerName),
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.h5.copy(
                fontWeight = FontWeight.ExtraBold
            ),
        )
        Text(
            text = stringResource(R.string.ownerEmail),
            fontSize = 16.sp,
            style = MaterialTheme.typography.subtitle2.copy(
                fontWeight = FontWeight.Normal
            )
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ProfileScreenPreview() {
    GhibliAppTheme {
        ProfileScreen()
    }
}