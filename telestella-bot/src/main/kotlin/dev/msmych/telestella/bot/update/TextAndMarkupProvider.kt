package dev.msmych.telestella.bot.update

import com.pengrad.telegrambot.model.Update
import com.pengrad.telegrambot.model.request.Keyboard

fun interface TextAndMarkupProvider {

    fun textAndMarkup(update: Update): Pair<String, Keyboard>
}