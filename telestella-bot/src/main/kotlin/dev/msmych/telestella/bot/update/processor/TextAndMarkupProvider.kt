package dev.msmych.telestella.bot.update.processor

import com.pengrad.telegrambot.model.Update
import com.pengrad.telegrambot.model.request.Keyboard
import dev.msmych.telestella.bot.Bot

fun interface TextAndMarkupProvider {

    fun textAndMarkup(update: Update, bot: Bot): Pair<String, Keyboard>
}