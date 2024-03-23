package br.com.eduardobrdev.aluragames.model

import java.util.Scanner
import kotlin.random.Random

data class Gamer(var name: String, var email: String) {
    var birthDate: String? = null
    var userName: String? = null
        set(value) {
            field = value
            if(iD.isNullOrBlank()) {
                generateId()
            }
        }
    var iD: String? = null
        private set

    var gamesSearch = mutableListOf<Game?>()

    constructor(name: String, email: String, birthDate: String, userName: String): this(name, email) {
        this.birthDate = birthDate
        this.userName = userName
        generateId()
    }

    init {
        if(name.isBlank()) {
            throw IllegalArgumentException("Name cannot be null.")
        }
        this.email = emailValidator()
    }

    override fun toString(): String {
        return "Gamer File: \n" +
                "ID: $iD, \n" +
                "Name: $name, \n" +
                "Email: $email, \n" +
                "Birth Date: $birthDate, \n" +
                "UserName: $userName"
    }

    private fun generateId() {
        val numberRandom = Random.nextInt(10000)
        // Guarantees that it will be a 4-digit number
        val tag = String.format("%04d", numberRandom)
        iD = "$userName#$tag"
    }

    private fun emailValidator(): String {
        val regex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}\$")
        if(regex.matches(email)) {
            return email
        } else {
            throw IllegalArgumentException("Email invalid.")
        }
    }

    companion object {
        fun registerGamer(reader: Scanner): Gamer {
            println("Welcome to AluraGames! Let's get you registered. Enter your name:")
            val name = reader.nextLine()
            println("Enter your email:")
            val email = reader.nextLine()
            println("Would you like to complete your registration with username and date of birth? (Y/N)")
            val choose = reader.nextLine()

            if (choose.equals("y", true)) {
                println("Enter your date of birth (MM/DD/YYYY):")
                val birth = reader.nextLine()
                println("Enter your UserName:")
                val userName = reader.nextLine()

                return Gamer(name, email, birth, userName)
            } else {
                return Gamer (name, email)
            }
        }
    }
}
