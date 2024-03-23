package org.example

data class Game(val title: String, val thumb: String, val steamAppID: String) {

    override fun toString(): String {
        return "Game Description: \n" +
                "Title: $title, \n" +
                "Thumb: $thumb, \n" +
                "SteamAppID: $steamAppID,"
    }
}