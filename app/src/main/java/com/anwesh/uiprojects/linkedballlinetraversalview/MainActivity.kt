package com.anwesh.uiprojects.linkedballlinetraversalview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.anwesh.uiprojects.balllinetraversalview.BallLineTraversalView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BallLineTraversalView.create(this)
    }
}
