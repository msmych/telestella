package dev.msmych.telestella.bot.update.processor

import com.pengrad.telegrambot.model.request.Keyboard
import dev.msmych.telestella.bot.update.processor.ChatIdProvider.Companion.MESSAGE_CHAT
import dev.msmych.telestella.bot.update.processor.MarkupProvider.Companion.markup
import dev.msmych.telestella.bot.update.processor.TextProvider.Companion.textProvider

/**
 * Sends message to the chat where given message came from
 */
open class AnswerMessageProcessor protected constructor(
    textProvider: TextProvider,
    markupProvider: MarkupProvider?
) : SendMessageProcessor(MESSAGE_CHAT, textProvider, markupProvider) {

    companion object {

        fun answer(text: String, markup: Keyboard) = answer(text, markup(markup))

        fun answer(text: String, markupProvider: MarkupProvider? = null) = answer(text.textProvider(), markupProvider)

        fun answer(
            textProvider: TextProvider,
            markupProvider: MarkupProvider? = null
        ) = AnswerMessageProcessor(textProvider, markupProvider)
    }
}