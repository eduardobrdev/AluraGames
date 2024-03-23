package br.com.eduardobrdev.aluragames.model

data class Game(val title: String, val thumb: String, val steamAppID: String) {

    var description:String? = null

    override fun toString(): String {
        return "Game Description: \n" +
                "Title: $title, \n" +
                "Thumb: $thumb, \n" +
                "SteamAppID: $steamAppID, \n" +
                "Description: $description"
    }
}