package dev.msmych.telestella.bot.update.predicate

import com.pengrad.telegrambot.model.Update

/**
 * Checks weather an update is a text message
 */
open class IsTextMessagePredicate protected constructor() : IsMessagePredicate() {

    override fun appliesTo(update: Update): Boolean {
        return super.appliesTo(update) && update.message().text() != null
    }

    companion object {

        val IS_TEXT_MESSAGE = IsTextMessagePredicate()
    }
}