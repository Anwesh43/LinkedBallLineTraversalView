package com.anwesh.uiprojects.balllinetraversalview

/**
 * Created by anweshmishra on 10/08/19.
 */

import android.view.View
import android.view.MotionEvent
import android.app.Activity
import android.content.Context
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.Color

val nodes : Int = 5
val lines : Int = 4
val scGap : Float = 0.05f
val scDiv : Double = 0.51
val strokeFactor : Int = 90
val sizeFactor : Float = 2.9f
val foreColor : Int = Color.parseColor("#4A148C")
val backColor : Int = Color.parseColor("#BDBDBD")
val rFactor : Float = 1.9f
val wFactor : Float = 3f

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.scaleFactor() : Float = Math.floor(this / scDiv).toFloat()
fun Float.mirrorValue(a : Int, b : Int) : Float {
    val k : Float = scaleFactor()
    return (1 - k) * a.inverse() + k * b.inverse()
}
fun Float.updateValue(dir : Float, a : Int, b : Int) : Float = mirrorValue(a, b) * dir * scGap

fun Canvas.drawBall(x : Float, r : Float, scale : Float, paint : Paint) {
    drawCircle(x, 0f, r * scale, paint)
}

fun Canvas.drawBallLineTraversal(w : Float, size : Float, scale : Float, paint : Paint) {
    val r : Float = size / rFactor
    val sc1 : Float = scale.divideScale(0, 2)
    val sc2 : Float = scale.divideScale(1, 2)
    val sc21 : Float = sc2.divideScale(0, 2)
    val sc22 : Float = sc2.divideScale(1, 2)
    val x1 : Float = w * sc21
    val x2 : Float = w * sc22
    drawBall(w * sc21, r , sc1, paint)
    drawLine(x2, 0f, x1, 0f, paint)
}

fun Canvas.drawBLTNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    val gap : Float = w / (nodes + 1)
    val size : Float = gap / sizeFactor
    val offsetW : Float = w / (wFactor * nodes)
    paint.color = foreColor
    paint.strokeCap = Paint.Cap.ROUND
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    drawBallLineTraversal(w + offsetW * (i + 1), size, scale, paint)
}
