package org.example.handlers

import io.github.dehuckakpyt.telegrambot.factory.input.input
import io.github.dehuckakpyt.telegrambot.factory.keyboard.inlineKeyboard
import io.github.dehuckakpyt.telegrambot.handling.BotHandling
import io.github.dehuckakpyt.telegrambot.model.telegram.*
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.example.utils.Config
import java.awt.image.BufferedImage
import java.net.URL
import javax.imageio.ImageIO

fun BotHandling.forwardCommand() {
    command("/forward", next = "forward_message") {
        sendMessage("Пришли мне сообщение из канала")
    }

    step(step = "forward_message") {
        val forwardMessage = message.forwardOrigin
        if (forwardMessage !is MessageOriginChannel) {
            sendMessage("Прислали не из канала")
        } else {
            val channelId = forwardMessage.chat.id
            println("Все сообщение: $forwardMessage")
            val channelInfo = bot.getChat(channelId).toUserChannel()
            println(channelInfo)

            channelInfo.photo?.let {
                val file = getFile(fileId = it.smallFileId)
                val photoLink = "https://api.telegram.org/file/bot${Config.BOT_TOKEN}/${file.filePath}"
                println("Ссылка на фото: $photoLink")
                /*downloadImage(imageUrl = photoLink, outputDir = "./images", outputFileName = "${it.smallFileId}.jpg")?.let { imageFilePath ->
                    sendPhoto(
                        photo = imageFilePath,
                        caption = channelInfo.title
                    )
                }*/

                sendPhoto(
                    photo = "D:\\Dev\\kotlin-telegram-bot-api-dehuckakpyt\\.\\images\\AQADAgADNbMxG4qK8UgACAIAA3vHsOAW____7eKxkIUhZ-E1BA.jpg",
                    caption = channelInfo.title
                )

                /*sendMessage(
                    text = channelInfo.inviteLink ?: "Нет инвайта",
                    replyMarkup = inlineKeyboard(
                        InlineKeyboardButton(
                            text = "Открыть канал",
                            url = channelInfo.inviteLink
                        )
                    )
                )*/
            }


            println("Колическтво подписчиков: ${bot.getChatMemberCount(channelId)}")
        }
    }
}

fun downloadImage(imageUrl: String, outputDir: String, outputFileName: String): String? {
    return try {
        // Создание URL объекта
        val url = URL(imageUrl)

        // Чтение изображения из URL
        val image: BufferedImage = ImageIO.read(url)

        // Проверка и создание директории, если она не существует
        val dir = java.io.File(outputDir)
        if (!dir.exists()) {
            dir.mkdirs()
        }

        // Создание файла для сохранения изображения
        val outputFile = java.io.File(dir, outputFileName)

        // Запись изображения в файл
        ImageIO.write(image, "jpg", outputFile)

        val absoluteOutputFilePath = outputFile.absolutePath
        println("Изображение сохранено в: $absoluteOutputFilePath")
        absoluteOutputFilePath
    } catch (e: Exception) {
        e.printStackTrace()
        println("Ошибка при скачивании или сохранении изображения.")
        null
    }
}

data class UserChannel(
    val id: Long,
    val type: String,
    val title: String?,
    val description: String?,
    val username: String?,
    val photo: ChatPhoto?,
    val inviteLink: String?
)

fun ChatFullInfo.toUserChannel(): UserChannel = UserChannel(
    id = this.id,
    type = this.type,
    title = this.title,
    description = this.description,
    username = this.username,
    photo = ChatPhoto(
        smallFileId = this.photo?.smallFileId ?: "",
        smallFileUniqueId = this.photo?.smallFileUniqueId ?: "",
        bigFileId = this.photo?.bigFileId ?: "",
        bigFileUniqueId = this.photo?.bigFileUniqueId ?: ""
    ),
    inviteLink = this.inviteLink
)
