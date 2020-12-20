package dev.msmych.telestella.bot.keyboard

import com.pengrad.telegrambot.model.request.InlineKeyboardButton

fun String.key(data: String): InlineKeyboardButton =
    InlineKeyboardButton(this)
        .callbackData(data)