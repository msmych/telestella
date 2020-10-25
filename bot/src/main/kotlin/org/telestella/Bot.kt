package org.telestella

import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.model.Update
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
     * Executes [SendMessage] request with chatId equal the one where [update] message came from
     */
    fun answerMessage(
        update: Update,
        text: String,
        silent: Boolean? = null,
        keyboard: Keyboard? = null,
        parseMode: ParseMode? = null,
        noPreview: Boolean? = null
    ): SendResponse {
        val rq = SendMessage(update.message().chat().id(), text)
        if (silent != null) {
            rq.disableNotification(silent)
        }
        if (keyboard != null) {
            rq.replyMarkup(keyboard)
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