package dev.msmych.telestella.bot.update.text

import com.pengrad.telegrambot.model.Update

fun interface TextProvider {

    fun text(update: Update): String

    companion object {

        fun String.asTextProvider(vararg args: Any): TextProvider = TextProvider { format(args) }
    }
}