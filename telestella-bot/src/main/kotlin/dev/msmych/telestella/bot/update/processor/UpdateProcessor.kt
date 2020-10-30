package dev.msmych.telestella.bot.update.processor

import com.pengrad.telegrambot.model.Update
import dev.msmych.telestella.bot.Bot

fun interface UpdateProcessor {

    fun process(update: Update, bot: Bot)
}