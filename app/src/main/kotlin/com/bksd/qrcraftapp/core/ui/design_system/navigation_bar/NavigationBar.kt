package com.bksd.qrcraftapp.core.ui.design_system.navigation_bar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation3.runtime.NavKey
import com.bksd.qrcraftapp.app.navigation.CreateQRScreenRoute
import com.bksd.qrcraftapp.app.navigation.HistoryScreenRoute
import com.bksd.qrcraftapp.app.navigation.MainScreenRoute
import com.bksd.qrcraftapp.core.ui.design_system.theme.QRCraftAppTheme

@Composable
fun NavigationBar(
    modifier: Modifier = Modifier,
    onNavigate: (NavKey) -> Unit,
    current: NavKey = MainScreenRoute,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = MaterialTheme.colorScheme.contentColorFor(containerColor),
    tonalElevation: Dp = NavigationBarDefaults.Elevation,
    windowInsets: WindowInsets = NavigationBarDefaults.windowInsets,
    fabSize: Dp = 64.dp,
) {
    Box(
        modifier = modifier
            .wrapContentSize()
            .padding(horizontal = 24.dp, vertical = 16.dp)
            .navigationBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            color = containerColor,
            contentColor = contentColor,
            tonalElevation = tonalElevation,
            shape = CircleShape,
            shadowElevation = 2.dp,
            modifier = Modifier
                .wrapContentSize()
                .navigationBarsPadding()
        ) {
            Row(
                modifier = Modifier
                    .wrapContentSize()
                    .windowInsetsPadding(windowInsets)
                    .selectableGroup(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                IconButton(
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(CircleShape)
                        .then(
                            if (current == HistoryScreenRoute) {
                                Modifier.background(MaterialTheme.colorScheme.secondaryContainer)
                            } else {
                                Modifier.background(MaterialTheme.colorScheme.surface)
                            }
                        )
                        .wrapContentSize(),
                    onClick = { onNavigate(HistoryScreenRoute) },
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    Icon(
                        painterResource(
                            HistoryScreenRoute.iconRes
                        ),
                        contentDescription = HistoryScreenRoute.contentDescription,
                        tint = if (current == HistoryScreenRoute)
                            MaterialTheme.colorScheme.primaryContainer
                        else
                            MaterialTheme.colorScheme.onSurface
                    )
                }

                IconButton(onClick = {}) {}

                IconButton(
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(CircleShape)
                        .then(
                            if (current == CreateQRScreenRoute) {
                                Modifier.background(MaterialTheme.colorScheme.secondaryContainer)
                            } else {
                                Modifier.background(MaterialTheme.colorScheme.surface)
                            }
                        )
                        .wrapContentSize(),
                    onClick = { onNavigate(CreateQRScreenRoute) },
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    Icon(
                        painterResource(
                            CreateQRScreenRoute.iconRes
                        ),
                        contentDescription = CreateQRScreenRoute.contentDescription,
                        tint = if (current == CreateQRScreenRoute)
                            MaterialTheme.colorScheme.primaryContainer
                        else
                            MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }

        FloatingActionButton(
            onClick = { onNavigate(MainScreenRoute) },
            shape = CircleShape,
            modifier = Modifier
                .size(fabSize)
                .zIndex(1f)
                .align(Alignment.Center)
                .clickable { onNavigate(MainScreenRoute) },
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onSurface,
            interactionSource = remember { MutableInteractionSource() }
        ) {
            Icon(
                painter = painterResource(MainScreenRoute.iconRes),
                modifier = Modifier.size(28.dp),
                contentDescription = MainScreenRoute.contentDescription
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    QRCraftAppTheme {
        NavigationBar(
            current = HistoryScreenRoute,
            onNavigate = {},
        )
    }
}