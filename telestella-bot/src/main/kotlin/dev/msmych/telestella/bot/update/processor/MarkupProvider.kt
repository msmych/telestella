package dev.msmych.telestella.bot.update.processor

import com.pengrad.telegrambot.model.Update
import com.pengrad.telegrambot.model.request.Keyboard
import dev.msmych.telestella.bot.Bot

fun interface MarkupProvider {

    fun markup(update: Update, bot: Bot): Keyboard

    companion object {

        fun markup(markup: Keyboard) = MarkupProvider { _, _ -> markup }
    }
}