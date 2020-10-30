package dev.msmych.telestella.bot.update.predicate

import com.pengrad.telegrambot.model.Update
import dev.msmych.telestella.bot.Bot

/**
 * Checks weather an update is a message
 */
open class IsMessagePredicate protected constructor() : UpdatePredicate {

    override fun appliesTo(update: Update, bot: Bot): Boolean {
        return update.message() != null
    }

    companion object {

        val IS_MESSAGE = IsMessagePredicate()
    }
}