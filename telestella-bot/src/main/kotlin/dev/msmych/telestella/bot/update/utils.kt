package dev.msmych.telestella.bot.update

import com.pengrad.telegrambot.model.Update

fun Update.messageChatId(): Long = message().chat().id()