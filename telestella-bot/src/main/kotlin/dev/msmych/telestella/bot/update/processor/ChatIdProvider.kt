package dev.msmych.telestella.bot.update.processor

import com.pengrad.telegrambot.model.Update
import dev.msmych.telestella.bot.update.messageChatId

fun interface ChatIdProvider {

    fun chatId(update: Update): Long

    companion object {

        val MESSAGE_CHAT: ChatIdProvider = ChatIdProvider { it.messageChatId() }
    }
}