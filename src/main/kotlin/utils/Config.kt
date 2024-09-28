package org.example.utils

object Config {
    const val BOT_USERNAME = "@best_ton_bot"
    val BOT_TOKEN = System.getenv("BOT_TOKEN") ?: ""
    const val APP_NAME = "RockeTGram"
}