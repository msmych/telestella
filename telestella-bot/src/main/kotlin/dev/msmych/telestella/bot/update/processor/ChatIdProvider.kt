package dev.msmych.telestella.bot.update.processor

import com.pengrad.telegrambot.model.Update
import dev.msmych.telestella.bot.Bot
import dev.msmych.telestella.bot.update.messageChatId

fun interface ChatIdProvider {

    fun chatId(update: Update, bot: Bot): Long

    companion object {

        val MESSAGE_CHAT: ChatIdProvider = ChatIdProvider { u, _ -> u.messageChatId() }
    }
}