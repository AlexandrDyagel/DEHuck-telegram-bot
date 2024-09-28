package org.example.handlers

import io.github.dehuckakpyt.telegrambot.exception.chat.ChatException
import io.github.dehuckakpyt.telegrambot.handling.BotHandling

fun BotHandling.checkMemberChannel() {
    command(command = "/check", next = "check") {
        sendMessage("Пришлите ID пользователя")
    }

    step(step = "check") {
        val userId = text.toLong()
        try {
            val member = bot.getChatMember(chatId = -1001252665477L, userId = userId)
            sendMessage(text = "Пользователь <b>${member.user.firstName}</b> является членом канала", parseMode = "HTML")
        } catch (e: Exception) {
            throw ChatException("Пользователь не является членом канала")
        }
    }
}