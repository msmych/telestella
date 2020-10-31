package dev.msmych.telestella.bot

import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.model.User
import com.pengrad.telegrambot.model.request.Keyboard
import com.pengrad.telegrambot.model.request.ParseMode
import com.pengrad.telegrambot.request.GetMe
import com.pengrad.telegrambot.request.SendMessage
import com.pengrad.telegrambot.response.SendResponse

/**
 * Extends standard [TelegramBot]
 */
class Bot(token: String) : TelegramBot(token) {

    /**
     * Retrieves information about self on initialization
     */
    val info: User = execute(GetMe()).user()

    /**
     * Executes [SendMessage] request
     */
    fun sendMessage(
        chatId: Long,
        text: String,
        silent: Boolean? = null,
        markup: Keyboard? = null,
        parseMode: ParseMode? = null,
        noPreview: Boolean? = null
    ): SendResponse {
        val rq = SendMessage(chatId, text)
        if (silent != null) {
            rq.disableNotification(silent)
        }
        if (markup != null) {
            rq.replyMarkup(markup)
        }
        if (parseMode != null) {
            rq.parseMode(parseMode)
        }
        if (noPreview != null) {
            rq.disableWebPagePreview(noPreview)
        }
        return execute(rq)
    }
}