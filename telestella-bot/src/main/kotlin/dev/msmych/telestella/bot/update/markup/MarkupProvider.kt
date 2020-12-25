package dev.msmych.telestella.bot.update.markup

import com.pengrad.telegrambot.model.Update
import com.pengrad.telegrambot.model.request.Keyboard

fun interface MarkupProvider {

    fun markup(update: Update): Keyboard

    companion object {

        fun markup(markup: Keyboard) = MarkupProvider { markup }
    }
}