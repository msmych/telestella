package org.telestella.update.predicate

import com.pengrad.telegrambot.model.Update
import org.telestella.Bot

/**
 * Checks weather an update is a message
 */
open class IsMessagePredicate protected constructor() : UpdatePredicate {

    override fun test(update: Update, bot: Bot): Boolean {
        return update.message() != null
    }

    companion object {

        val IS_MESSAGE = IsMessagePredicate()
    }
}