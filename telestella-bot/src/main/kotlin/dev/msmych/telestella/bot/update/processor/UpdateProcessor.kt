package dev.msmych.telestella.bot.update.processor

import com.pengrad.telegrambot.model.Update
import dev.msmych.telestella.bot.Bot

fun interface UpdateProcessor {

    fun process(update: Update, bot: Bot)

    companion object {

        val NO_ACTION = UpdateProcessor { _, _ -> }

        fun process(vararg processors: UpdateProcessor) =
            UpdateProcessor { u, b ->
                processors.forEach { it.process(u, b) }
            }
    }
}