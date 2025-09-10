package com.bksd.qrcraftapp.feature.qr.ui.camera.util

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke

enum class Corner { TOP_LEFT, TOP_RIGHT, BOTTOM_RIGHT, BOTTOM_LEFT }

fun DrawScope.drawFrameCorner(
    left: Float,
    top: Float,
    right: Float,
    bottom: Float,
    radius: Float,
    cornerLength: Float,
    stroke: Float,
    color: Color,
    corner: Corner,
) {
    val path = Path()

    when (corner) {
        Corner.TOP_LEFT -> {
            path.apply {
                moveTo(left + radius + cornerLength, top)
                lineTo(left + radius, top)
                arcTo(
                    Rect(left, top, left + 2 * radius, top + 2 * radius),
                    startAngleDegrees = -90f,
                    sweepAngleDegrees = -90f,
                    forceMoveTo = false
                )
                lineTo(left, top + radius + cornerLength)
            }
        }

        Corner.TOP_RIGHT -> {
            path.apply {
                moveTo(right - radius - cornerLength, top)
                lineTo(right - radius, top)
                arcTo(
                    Rect(right - 2 * radius, top, right, top + 2 * radius),
                    startAngleDegrees = -90f,
                    sweepAngleDegrees = 90f,
                    forceMoveTo = false
                )
                lineTo(right, top + radius + cornerLength)
            }
        }

        Corner.BOTTOM_RIGHT -> {
            path.apply {
                moveTo(right, bottom - radius - cornerLength)
                lineTo(right, bottom - radius)
                arcTo(
                    Rect(right - 2 * radius, bottom - 2 * radius, right, bottom),
                    startAngleDegrees = 0f,
                    sweepAngleDegrees = 90f,
                    forceMoveTo = false
                )
                lineTo(right - radius - cornerLength, bottom)
            }
        }

        Corner.BOTTOM_LEFT -> {
            path.apply {
                moveTo(left + radius + cornerLength, bottom)
                lineTo(left + radius, bottom)
                arcTo(
                    Rect(left, bottom - 2 * radius, left + 2 * radius, bottom),
                    startAngleDegrees = 90f,
                    sweepAngleDegrees = 90f,
                    forceMoveTo = false
                )
                lineTo(left, bottom - radius - cornerLength)
            }
        }
    }

    drawPath(path, color, style = Stroke(width = stroke, cap = StrokeCap.Round))
}