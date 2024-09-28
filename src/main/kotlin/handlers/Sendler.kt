package org.example.handlers

import io.github.dehuckakpyt.telegrambot.handling.BotHandling
import org.example.db.Database

fun BotHandling.sendMessages() {
    command("/send") {
        val users = Database.users
        for (user in users) {
            bot.sendMessage(
                chatId = user.id,
                text = "Рекламный текст"
            )
        }
    }
}