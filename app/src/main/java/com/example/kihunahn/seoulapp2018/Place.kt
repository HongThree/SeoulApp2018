package com.example.kihunahn.seoulapp2018

import android.content.Context


class Place(val name: String, private val imageName: String, val isFav: Boolean = false) {
    fun getImageResourceId(context: Context): Int {
        return context.resources.getIdentifier(this.imageName, "drawable", context.packageName)
    }
}