package org.example.handlers

import io.github.dehuckakpyt.telegrambot.factory.keyboard.inlineKeyboard
import io.github.dehuckakpyt.telegrambot.handling.BotHandling
import io.github.dehuckakpyt.telegrambot.model.telegram.*
import org.example.db.Database
import org.example.dto.UserDto
import org.example.utils.Command
import org.example.utils.Config
import org.example.utils.bold

fun BotHandling.startCommand() {
    command(Command.Start.value) {
        val user = message.from ?: return@command
        if (!user.isBot) {

            sendMessage(
                text = "Привет, ${user.firstName}, я бот авторизации ${bold(Config.APP_NAME)}!",
                parseMode = "Markdown"
            )

            // Записываем в БД. Если вернулся объект userDto, значит запись прошла в БД.
            val userDto = Database.createUser(user.toUserDto())
            if (userDto == null) {
                sendMessage(
                    text = "Аккаунт зарегистрирован! Для завершения авторизации нажмите на кнопку ниже",
                    replyMarkup = inlineKeyboard(
                        InlineKeyboardButton(
                            text = "Завершить регистрацию",
                            url = "http://vk.com"
                        )
                    )
                )
            } else {
                // Получаем ранее уже зарегистрированного пользователя.
                val userFromDB = Database.readUser(user.id)
                if (userFromDB != null) {
                    sendMessage(
                        text = "Аккаунт найден! Для завершения авторизации нажмите на кнопку ниже",
                        replyMarkup = inlineKeyboard(
                            InlineKeyboardButton(
                                text = "Войти в ${Config.APP_NAME}",
                                url = "http://vk.com"
                            )
                        )
                    )
                }
            }
        }
    }
}

fun User.toUserDto(): UserDto = UserDto(
    id = this.id,
    firstName = this.firstName,
    lastName = this.lastName,
    username = this.username,
)