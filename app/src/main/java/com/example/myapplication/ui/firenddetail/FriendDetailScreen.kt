package com.example.myapplication.ui.firenddetail

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.myapplication.model.FriendItem
import com.example.myapplication.util.extension.Space
import com.example.myapplication.viewmodel.FriendDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FriendDetailScreen(
    navController: NavController,
    id: Int,
    viewModel: FriendDetailViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    val configuration = LocalConfiguration.current

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchFriend(id)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Friend Detail")
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                modifier = Modifier.statusBarsPadding()
            )
        },
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                if (uiState.value.isLoading) {
                    CircularProgressIndicator()
                } else {
                    if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                        DetailLandscapeScreen(friend = uiState.value.friend)
                    } else {
                        DetailPortraitScreen(friend = uiState.value.friend)
                    }
                }
            }
        }
    }
}


@Composable
private fun DetailPortraitScreen(
    friend: FriendItem?
) {
    val context: Context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        32.dp.Space()

        AsyncImage(
            model = friend?.avatarUrl ?: "",
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .border(
                    BorderStroke(width = 1.dp, color = Color.Black),
                    shape = CircleShape,
                )
                .clip(shape = CircleShape),
            contentScale = ContentScale.Crop
        )

        16.dp.Space()

        Text(
            text = friend?.name ?: "",
            style = MaterialTheme.typography.titleLarge
        )

        24.dp.Space()

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 4.dp,
                ),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
            ) {
                friend?.email?.takeIf { it.isNotBlank() }
                    ?.let { email ->
                        InfoRow(label = "Email", value = email)
                    }

                friend?.location?.takeIf { it.isNotBlank() }
                    ?.let { location ->
                        InfoRow(label = "Address", value = location)
                    }

                friend?.bio?.takeIf { it.isNotBlank() }
                    ?.let { bio ->
                        InfoRow(label = "Bio", value = bio)
                    }

                friend?.blog?.takeIf { it.isNotBlank() }
                    ?.let { blog ->
                        InfoRow(
                            label = "Blog",
                            value = blog,
                            onClick = { url ->
                                val intent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse(url)
                                )
                                context.startActivity(intent)
                            }
                        )
                    }

                friend?.htmlUrl?.takeIf { it.isNotBlank() }
                    ?.let { htmlUrl ->
                        InfoRow(
                            label = "Github",
                            value = htmlUrl,
                            onClick = { url ->
                                val intent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse(url)
                                )
                                context.startActivity(intent)
                            }
                        )
                    }

                FriendStats(
                    followers = friend?.followers,
                    following = friend?.following
                )
            }
        }
    }
}

@Composable
private fun DetailLandscapeScreen(
    friend: FriendItem?
) {
    val context: Context = LocalContext.current

    Row(
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AsyncImage(
                model = friend?.avatarUrl ?: "",
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .border(
                        BorderStroke(width = 1.dp, color = Color.Black),
                        shape = CircleShape,
                    )
                    .clip(shape = CircleShape),
                contentScale = ContentScale.Crop
            )

            16.dp.Space()

            Text(
                text = friend?.name ?: "",
                style = MaterialTheme.typography.titleLarge
            )
        }

        16.dp.Space()

        Column(
            modifier = Modifier.weight(1.5f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                ) {
                    friend?.email?.takeIf { it.isNotBlank() }
                        ?.let { email ->
                            InfoRow(label = "Email", value = email)
                        }

                    friend?.location?.takeIf { it.isNotBlank() }
                        ?.let { location ->
                            InfoRow(label = "Address", value = location)
                        }

                    friend?.bio?.takeIf { it.isNotBlank() }
                        ?.let { bio ->
                            InfoRow(label = "Bio", value = bio)
                        }

                    friend?.blog?.takeIf { it.isNotBlank() }
                        ?.let { blog ->
                            InfoRow(
                                label = "Blog",
                                value = blog,
                                onClick = { url ->
                                    val intent = Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(url)
                                    )
                                    context.startActivity(intent)
                                }
                            )
                        }

                    friend?.htmlUrl?.takeIf { it.isNotBlank() }
                        ?.let { htmlUrl ->
                            InfoRow(
                                label = "Github",
                                value = htmlUrl,
                                onClick = { url ->
                                    val intent = Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(url)
                                    )
                                    context.startActivity(intent)
                                }
                            )
                        }

                    FriendStats(
                        followers = friend?.followers,
                        following = friend?.following
                    )
                }
            }
        }
    }
}

@Composable
private fun InfoRow(
    label: String,
    value: String,
    onClick: ((String) -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(
                when {
                    onClick != null -> Modifier.clickable { onClick(value) }
                    else -> Modifier
                }
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$label:",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            color = when {
                onClick != null -> Color.Blue
                else -> Color.Unspecified
            },
            textDecoration = when {
                onClick != null -> TextDecoration.Underline
                else -> TextDecoration.None
            }
        )
    }
    8.dp.Space()
}

@Composable
private fun FriendStats(
    followers: Int?,
    following: Int?
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        StatItemView(
            count = followers ?: 0,
            label = "Followers",
        )
        StatItemView(
            count = following ?: 0,
            label = "Following",
        )
    }
}

@Composable
private fun StatItemView(
    count: Int,
    label: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = count.toString(),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
    }
}