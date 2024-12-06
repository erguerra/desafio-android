package com.picpay.desafio.android.ui.userList.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.picpay.desafio.android.ui.theme.PicPayTheme
import com.picpay.desafio.android.ui.theme.colorAccent
import com.picpay.desafio.android.ui.theme.colorPrimaryDark

@Composable
fun UserItem(
    modifier: Modifier = Modifier,
    name: String,
    username: String,
    avatar: String,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(colorPrimaryDark)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.size(52.dp)) {
            AsyncImage(
                model = avatar,
                contentDescription = null,
                // TODO: Add Loading State
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column (
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = username,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = name,
                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }

}

@Composable
@Preview
fun UserItemPreview() {

    PicPayTheme(darkTheme = false) {
        UserItem(
            name = "Toninho Rodrigues",
            username = "toninho_violeta_s2",
            avatar = "https://randomuser.me/api/portraits/men/1.jpg",
        )
    }
}