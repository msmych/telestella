package dev.msmych.telestella.bot.update.chat

import com.pengrad.telegrambot.model.Update

fun interface ChatIdProvider {

    fun chatId(update: Update): Long

    companion object {

        val MESSAGE_CHAT: ChatIdProvider = ChatIdProvider { it.messageChatId() }

        fun Update.messageChatId(): Long = message().chat().id()
    }
}