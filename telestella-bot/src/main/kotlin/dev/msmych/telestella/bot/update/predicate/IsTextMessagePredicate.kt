package dev.msmych.telestella.bot.update.predicate

import com.pengrad.telegrambot.model.Update
import dev.msmych.telestella.bot.Bot

/**
 * Checks weather an update is a text message
 */
open class IsTextMessagePredicate protected constructor() : IsMessagePredicate() {

    override fun test(update: Update, bot: Bot): Boolean {
        return super.test(update, bot) && update.message().text() != null
    }

    companion object {

        val IS_TEXT_MESSAGE = IsTextMessagePredicate()
    }
}