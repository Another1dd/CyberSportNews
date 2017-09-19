package com.another1dd.cybersportnews.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

//Fragment inflate extension
fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

//Activity extension
//val Activity.app: Application
//    get()=application as Application
