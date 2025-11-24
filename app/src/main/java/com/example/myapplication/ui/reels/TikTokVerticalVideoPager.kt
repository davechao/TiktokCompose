package com.example.myapplication.ui.reels

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.model.VideoItem
import com.example.myapplication.player.VideoPlayer
import com.example.myapplication.ui.theme.Gray20
import com.example.myapplication.ui.theme.GrayLight
import com.example.myapplication.util.IntentUtils.share
import com.example.myapplication.util.extension.Space
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TikTokVerticalVideoPager(
    modifier: Modifier = Modifier,
    videos: List<VideoItem>,
    initialPage: Int? = 0,
    showUploadDate: Boolean = false,
) {
    val pagerState = rememberPagerState(
        initialPage = initialPage ?: 0,
        pageCount = { videos.size }
    )
    val coroutineScope = rememberCoroutineScope()
    val localDensity = LocalDensity.current

    val fling = PagerDefaults.flingBehavior(
        state = pagerState, lowVelocityAnimationSpec = tween(
            easing = LinearEasing, durationMillis = 300
        )
    )

    val context: Context = LocalContext.current

    VerticalPager(
        state = pagerState,
        flingBehavior = fling,
        beyondBoundsPageCount = 1,
        modifier = modifier
    ) {
        var pauseButtonVisibility by remember { mutableStateOf(false) }
        var doubleTapState by remember {
            mutableStateOf(
                Triple(
                    Offset.Unspecified, // offset
                    false, // double tap anim start
                    0f // rotation angle
                )
            )
        }

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            VideoPlayer(
                url = videos[it].videoLink,
                pagerState = pagerState,
                pageIndex = it,
                onSingleTap = {
                    pauseButtonVisibility = it.isPlaying
                    it.playWhenReady = it.isPlaying.not()
                },
                onDoubleTap = { exoPlayer, offset ->
                    coroutineScope.launch {
                        videos[it].currentViewerInteraction.isLikedByYou = true
                        val rotationAngle = (-10..10).random()
                        doubleTapState = Triple(offset, true, rotationAngle.toFloat())
                        delay(400)
                        doubleTapState = Triple(offset, false, rotationAngle.toFloat())
                    }
                },
                onVideoDispose = { pauseButtonVisibility = false },
                onVideoGoBackground = { pauseButtonVisibility = false }
            )

            Column(
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.Bottom,
                ) {
                    FooterItemView(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        item = videos[it],
                        showUploadDate = showUploadDate,
                    )

                    SideItemView(
                        modifier = Modifier,
                        item = videos[it],
                        doubleTabState = doubleTapState,
                        onClickShare = {
                            context.share(text = it)
                        }
                    )
                }
                12.dp.Space()
            }

            AnimatedVisibility(
                visible = pauseButtonVisibility,
                enter = scaleIn(spring(Spring.DampingRatioMediumBouncy), initialScale = 1.5f),
                exit = scaleOut(tween(150)),
                modifier = Modifier.align(Alignment.Center)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_play),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(36.dp)
                )
            }

            val iconSize = 110.dp
            AnimatedVisibility(
                visible = doubleTapState.second,
                enter = scaleIn(spring(Spring.DampingRatioMediumBouncy), initialScale = 1.3f),
                exit = scaleOut(
                    tween(600),
                    targetScale = 1.58f
                ) + fadeOut(tween(600)) + slideOutVertically(tween(600)),
                modifier = Modifier.run {
                    when {
                        doubleTapState.first != Offset.Unspecified -> {
                            this.offset(
                                x = localDensity.run {
                                    doubleTapState.first.x.toInt().toDp().plus(-iconSize.div(2))
                                },
                                y = localDensity.run {
                                    doubleTapState.first.y.toInt().toDp().plus(-iconSize.div(2))
                                }
                            )
                        }

                        else -> this
                    }
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_like),
                    contentDescription = null,
                    tint = when {
                        doubleTapState.second -> Color.Red
                        else -> Color.Red.copy(alpha = 0.8f)
                    },
                    modifier = Modifier
                        .size(iconSize)
                        .rotate(doubleTapState.third)
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun FooterItemView(
    modifier: Modifier,
    item: VideoItem,
    showUploadDate: Boolean,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Bottom,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = item.authorDetails.fullName,
                style = MaterialTheme.typography.bodyMedium
            )
            if (showUploadDate) {
                Text(
                    text = " . ${item.createdAt} ago",
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.White.copy(alpha = 0.6f)
                )
            }
        }

        5.dp.Space()

        Text(
            text = item.description,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.fillMaxWidth(0.85f)
        )

        10.dp.Space()

        val audioInfo: String = item.audioModel
            ?.run {
                "Original sound - ${audioAuthor.uniqueUserName} - ${audioAuthor.fullName}"
            }
            ?: item.run {
                "Original sound - ${item.authorDetails.uniqueUserName} - ${item.authorDetails.fullName}"
            }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_music_note),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(12.dp)
            )
            Text(
                text = audioInfo,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .basicMarquee()
            )
        }
    }
}

@Composable
private fun SideItemView(
    modifier: Modifier,
    item: VideoItem,
    doubleTabState: Triple<Offset, Boolean, Float>,
    onClickShare: (url: String) -> Unit,
) {
    var isLiked by remember {
        mutableStateOf(item.currentViewerInteraction.isLikedByYou)
    }

    LaunchedEffect(
        key1 = doubleTabState
    ) {
        if (doubleTabState.first != Offset.Unspecified && doubleTabState.second) {
            isLiked = doubleTabState.second
        }
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            model = item.authorDetails.profilePic,
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .border(
                    BorderStroke(width = 1.dp, color = Color.White),
                    shape = CircleShape,
                )
                .clip(shape = CircleShape),
            contentScale = ContentScale.Crop
        )

        12.dp.Space()

        LikeIconButton(
            isLiked = isLiked,
            likeCount = item.videoStats.formattedLikeCount,
            onLikedClicked = {
                isLiked = it
                item.currentViewerInteraction.isLikedByYou = it
            }
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_comment),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(33.dp)
        )

        Text(
            text = item.videoStats.formattedCommentCount,
            style = MaterialTheme.typography.labelMedium
        )

        16.dp.Space()

        Icon(
            painter = painterResource(id = R.drawable.ic_bookmark),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(33.dp)
        )

        Text(
            text = item.videoStats.formattedFavouriteCount,
            style = MaterialTheme.typography.labelMedium
        )

        14.dp.Space()

        Icon(
            painter = painterResource(id = R.drawable.ic_share),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier
                .size(32.dp)
                .clickable {
                    onClickShare("https://www.android.com/")
                }
        )

        Text(
            text = item.videoStats.formattedShareCount,
            style = MaterialTheme.typography.labelMedium
        )

        20.dp.Space()

        RotatingAudioView(item.authorDetails.profilePic)
    }
}

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
private fun LikeIconButton(
    isLiked: Boolean,
    likeCount: String,
    onLikedClicked: (Boolean) -> Unit,
) {
    val maxSize = 38.dp
    val iconSize by animateDpAsState(
        targetValue = when {
            isLiked -> 33.dp
            else -> 32.dp
        },
        animationSpec = keyframes {
            durationMillis = 400
            24.dp.at(50)
            maxSize.at(190)
            26.dp.at(330)
            32.dp.at(400).with(FastOutLinearInEasing)
        },
        label = "",
    )

    Box(
        modifier = Modifier
            .size(maxSize)
            .clickable { onLikedClicked(isLiked.not()) },
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_heart),
            contentDescription = null,
            tint = when {
                isLiked -> Color.Red
                else -> Color.White
            },
            modifier = Modifier.size(iconSize)
        )
    }

    Text(
        text = likeCount,
        style = MaterialTheme.typography.labelMedium,
    )

    16.dp.Space()
}

@Composable
private fun RotatingAudioView(img: String) {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(animation = keyframes { durationMillis = 7000 }),
        label = ""
    )

    Box(
        modifier = Modifier.rotate(angle)
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(
                            Gray20,
                            Gray20,
                            GrayLight,
                            Gray20,
                            Gray20,
                        )
                    ),
                    shape = CircleShape
                )
                .size(46.dp),
            contentAlignment = Alignment.Center,
        ) {
            AsyncImage(
                model = img,
                contentDescription = null,
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }
    }
}