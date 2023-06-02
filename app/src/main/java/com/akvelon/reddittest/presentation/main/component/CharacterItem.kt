package com.akvelon.reddittest.presentation.main.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun CharacterItem(
    modifier: Modifier = Modifier,
    character: com.akvelon.reddittest.domain.model.Character
) {
    Card(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier.size(48.dp),
                model = character.image,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .align(Alignment.Top),
                text = character.name
            )
            Column(
                modifier = Modifier.wrapContentWidth(),
                horizontalAlignment = Alignment.End
            ) {
                Text(text = character.status)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = character.gender)
            }
        }
    }
}