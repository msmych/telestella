package org.telestella.update.listener

import com.pengrad.telegrambot.UpdatesListener
import com.pengrad.telegrambot.UpdatesListener.CONFIRMED_UPDATES_ALL
import com.pengrad.telegrambot.UpdatesListener.CONFIRMED_UPDATES_NONE
import com.pengrad.telegrambot.model.Update
import org.telestella.Bot
import org.telestella.update.dispatcher.UpdateDispatcher

/**
 * Processes every update with processors provided by [dispatcher]
 *
 * If dispatching or processing fails, returns last processed update id
 */
class RetryUpdatesListener(
    private val bot: Bot,
    private val dispatcher: UpdateDispatcher,
    private val onError: (e: Exception) -> Unit = {}
) : UpdatesListener {

    override fun process(updates: List<Update>): Int {
        var lastProcessed = CONFIRMED_UPDATES_NONE
        for (update in updates) {
            try {
                val processor = dispatcher.apply(update, bot)
                processor.accept(update, bot)
            } catch (e: Exception) {
                onError(e)
                return lastProcessed
            }
            lastProcessed = update.updateId()
        }
        return CONFIRMED_UPDATES_ALL
    }
}