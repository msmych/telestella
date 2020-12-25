package dev.msmych.telestella.bot.update.processor

import com.pengrad.telegrambot.model.Update
import com.pengrad.telegrambot.model.request.ParseMode.MarkdownV2
import dev.msmych.telestella.bot.Bot

open class SendMessageProcessor protected constructor(
    private val bot: Bot,
    private val chatIdProvider: ChatIdProvider,
    private val textProvider: TextProvider,
    private val markupProvider: MarkupProvider?
) : UpdateProcessor {

    override fun process(update: Update) {
        bot.sendMessage(
            chatIdProvider.chatId(update),
            textProvider.text(update),
            silent = true,
            markupProvider?.markup(update),
            MarkdownV2
        )
    }

    companion object {

        fun sendMessage(
            bot: Bot,
            chatIdProvider: ChatIdProvider,
            textProvider: TextProvider,
            markupProvider: MarkupProvider?
        ) = SendMessageProcessor(bot, chatIdProvider, textProvider, markupProvider)
    }
}