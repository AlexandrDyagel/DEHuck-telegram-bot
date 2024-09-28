package org.example.handlers

import io.github.dehuckakpyt.telegrambot.factory.keyboard.inlineKeyboard
import io.github.dehuckakpyt.telegrambot.handling.BotHandling

fun BotHandling.callbackCommand() {
    command("/callback") {
        sendMessage("Выбери покупку", replyMarkup = inlineKeyboard(
            callbackButton("Куртка 50 руб", next = "buy", content = "id123"),
            callbackButton("Сапоги 15 руб", next = "buy", content = "id456"),
            callbackButton("Техническая поддержка", next = "get_feedback_intro")))
    }

    callback("buy") {
        val itemId = transferred<String>()
        when(itemId) {
            "id123" -> sendMessage(text = "Ура, вы купили куртку")
            "id456" -> sendMessage(text = "Ура, вы купили сапоги")
            else -> sendMessage(text = "Вы ничего не купили")
        }
    }

    callback("get_feedback_intro", next = "get_feedback") {
        sendMessage("Пришлите нам свою жалобу")
        val k = "Это его жалоба"
        transfer(k)
    }

    step("get_feedback") {
        val k = transferred<String>()
        println(k)
        sendMessage("Спасибо за обращение")
    }
}