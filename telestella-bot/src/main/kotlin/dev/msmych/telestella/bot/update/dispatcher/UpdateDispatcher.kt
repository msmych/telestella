package dev.msmych.telestella.bot.update.dispatcher

import com.pengrad.telegrambot.model.Update
import dev.msmych.telestella.bot.update.processor.UpdateProcessor

fun interface UpdateDispatcher {

    fun dispatch(update: Update): UpdateProcessor
}