package dev.msmych.telestella.bot.update.processor

import com.pengrad.telegrambot.model.Update
import com.pengrad.telegrambot.model.request.ParseMode.MarkdownV2
import dev.msmych.telestella.bot.Bot

open class SendMessageProcessor protected constructor(
    private val chatIdProvider: ChatIdProvider,
    private val textProvider: TextProvider,
    private val markupProvider: MarkupProvider?
) : UpdateProcessor {

    override fun process(update: Update, bot: Bot) {
        bot.sendMessage(
            chatIdProvider.chatId(update, bot),
            textProvider.text(update, bot),
            silent = true,
            markupProvider?.markup(update, bot),
            MarkdownV2
        )
    }

    companion object {

        fun sendMessage(
            chatIdProvider: ChatIdProvider,
            textProvider: TextProvider,
            markupProvider: MarkupProvider?
        ) = SendMessageProcessor(chatIdProvider, textProvider, markupProvider)
    }
}