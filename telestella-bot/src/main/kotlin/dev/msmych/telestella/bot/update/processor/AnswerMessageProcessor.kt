package dev.msmych.telestella.bot.update.processor

import com.pengrad.telegrambot.model.request.Keyboard
import dev.msmych.telestella.bot.Bot
import dev.msmych.telestella.bot.update.processor.ChatIdProvider.Companion.MESSAGE_CHAT
import dev.msmych.telestella.bot.update.processor.MarkupProvider.Companion.markup
import dev.msmych.telestella.bot.update.processor.TextProvider.Companion.asTextProvider

/**
 * Sends message to the chat where given message came from
 */
open class AnswerMessageProcessor protected constructor(
    bot: Bot,
    textProvider: TextProvider,
    markupProvider: MarkupProvider?
) : SendMessageProcessor(bot, MESSAGE_CHAT, textProvider, markupProvider) {

    companion object {

        fun Bot.answer(text: String, markup: Keyboard) = answer(text, markup(markup))

        fun Bot.answer(text: String, markupProvider: MarkupProvider? = null) =
            answer(text.asTextProvider(), markupProvider)

        fun Bot.answer(textAndMarkupProvider: TextAndMarkupProvider): AnswerMessageProcessor {
            val textAndMarkup = TextAndMarkup(textAndMarkupProvider)
            return answer({ textAndMarkup.text(it) }, { textAndMarkup.markup(it) })
        }

        fun Bot.answer(
            textProvider: TextProvider,
            markupProvider: MarkupProvider? = null
        ) = AnswerMessageProcessor(this, textProvider, markupProvider)
    }
}