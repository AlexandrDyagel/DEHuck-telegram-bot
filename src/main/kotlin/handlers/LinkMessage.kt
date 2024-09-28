package org.example.handlers

import io.github.dehuckakpyt.telegrambot.handling.BotHandling

fun BotHandling.linkMessage() {
    command(command = "/link", next = "link") {
        sendMessage("Пришли мне ссылку на канал или чат")
    }

    step(step = "link") {
        println(entities)

        entities?.forEach { messageEntity ->
            if (messageEntity.type == "text_link") {
                sendMessage(

                    text = "Ой нельзя скидывать ссылки"
                )
            }

        }
    }
}