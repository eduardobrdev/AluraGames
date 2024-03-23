package org.example

import com.google.gson.annotations.SerializedName

class Game(@SerializedName("title") val title: String, @SerializedName("thumb") val thumb: String, val description: String) {

    override fun toString(): String {
        return "Game(title='$title', thumb='$thumb', description='$description')"
    }


}