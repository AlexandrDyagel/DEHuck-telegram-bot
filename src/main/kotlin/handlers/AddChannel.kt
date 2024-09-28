package org.example.handlers

import io.github.dehuckakpyt.telegrambot.container.message.MessageType
import io.github.dehuckakpyt.telegrambot.exception.chat.ChatException
import io.github.dehuckakpyt.telegrambot.handling.BotHandling
import io.github.dehuckakpyt.telegrambot.model.telegram.Message
import io.github.dehuckakpyt.telegrambot.model.telegram.MessageOriginChannel
import kotlinx.coroutines.delay
import org.example.utils.Config

fun BotHandling.addChannel() {
    command("/parse", next = "prs") {
        sendMessage("Пришли мне сообщение из канала")
    }

    step(step = "prs", type = MessageType.PHOTO) {
        println("Тип PHOTO")
        parseMessage(message = message)
    }

    step(step = "prs", type = MessageType.CONTACT) {
        println("Тип CONTACT")
        parseMessage(message = message)
    }

    step(step = "prs", type = MessageType.AUDIO) {
        println("Тип AUDIO")
        parseMessage(message = message)
    }

    step(step = "prs", type = MessageType.VOICE) {
        println("Тип VOICE")
        parseMessage(message = message)
    }

    step(step = "prs", type = MessageType.DOCUMENT) {
        println("Тип DOCUMENT")
        parseMessage(message = message)
    }

    step(step = "prs", type = MessageType.LOCATION) {
        println("Тип LOCATION")
        parseMessage(message = message)
    }

    step(step = "prs") {
        println("Тип TEXT")
        parseMessage(message = message)
    }
}

private suspend fun BotHandling.parseMessage(message: Message) {
    val forwardMessage = message.forwardOrigin
    if (forwardMessage?.type == "channel" && forwardMessage is MessageOriginChannel) {
        val channelId = forwardMessage.chat.id
        try {
            bot.getChatAdministrators(channelId)
            val channelInfo = bot.getChat(channelId).toUserChannel()
            bot.sendMessage(
                chatId = message.chat.id,
                text = channelInfo.inviteLink ?: "Нет инвайта",
            )
            val channelPhotoPath = bot.getFile(channelInfo.photo?.bigFileId ?: throw ChatException("Нет фото")).filePath
            println("https://api.telegram.org/file/bot${Config.BOT_TOKEN}/$channelPhotoPath")
            // **************************
            // СОХРАНИТЬ КАНАЛ В БД
            // **************************
            try {
                delay(3000L)
                bot.sendMessage(
                    chatId = message.chat.id,
                    text = "Запись сохранилась в БД"
                )
            }catch (e: Exception){
                throw ChatException("Запись не сохранена в БД")
            }
            bot.sendMessage(
                chatId = message.chat.id,
                text = "Канал <b>${channelInfo.title}</b> успешно подключен!",
                parseMode = "HTML",
            )
        } catch (e: Exception) {
            throw ChatException("Бот не является администратором данного канала. Пожалуйста, добавьте его в канал администратором и повторите попытку.")
        } finally {
        }
    } else throw ChatException("Сообщение не принадлежит каналу. Отправьте сообщение из своего канала.")
}