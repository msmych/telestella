package org.telestella.update.processor

import com.pengrad.telegrambot.model.Update
import com.pengrad.telegrambot.model.request.Keyboard
import com.pengrad.telegrambot.model.request.ParseMode.MarkdownV2
import org.telestella.Bot

/**
 * Sends message to a chat where given message came from
 */
class AnswerMessageProcessor private constructor(
    private val text: String,
    private val keyboard: Keyboard? = null,
) : UpdateProcessor {

    override fun accept(update: Update, bot: Bot) {
        bot.answerMessage(update, text, silent = true, keyboard, MarkdownV2)
    }

    companion object {

        fun answer(
            text: String,
            keyboard: Keyboard? = null,
        ) = AnswerMessageProcessor(text, keyboard)
    }
}