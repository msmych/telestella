package dev.msmych.telestella.bot.update.processor

import com.pengrad.telegrambot.model.Update
import dev.msmych.telestella.bot.Bot

fun interface TextProvider {

    fun text(update: Update, bot: Bot): String

    companion object {

        fun String.asTextProvider(vararg args: Any): TextProvider = TextProvider { _, _ -> format(args) }
    }
}