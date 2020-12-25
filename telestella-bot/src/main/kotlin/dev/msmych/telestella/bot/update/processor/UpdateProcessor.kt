package dev.msmych.telestella.bot.update.processor

import com.pengrad.telegrambot.model.Update

fun interface UpdateProcessor {

    fun process(update: Update)

    companion object {

        val NO_ACTION = UpdateProcessor {}

        fun process(vararg processors: UpdateProcessor) =
            UpdateProcessor { update ->
                processors.forEach { it.process(update) }
            }
    }
}