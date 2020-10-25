package org.telestella.update.listener

import com.pengrad.telegrambot.UpdatesListener
import com.pengrad.telegrambot.UpdatesListener.CONFIRMED_UPDATES_ALL
import com.pengrad.telegrambot.model.Update
import org.telestella.Bot
import org.telestella.update.dispatcher.UpdateDispatcher

/**
 * Processes every update with processors provided by [dispatcher]
 *
 * Catches exceptions and returns [CONFIRMED_UPDATES_ALL] in any case
 */
class OneTryUpdatesListener(
    private val bot: Bot,
    private val dispatcher: UpdateDispatcher,
    private val onError: (e: Exception) -> Unit = {}
) : UpdatesListener {

    override fun process(updates: List<Update>): Int {
        updates.forEach { update ->
            try {
                val processor = dispatcher.apply(update, bot)
                processor.accept(update, bot)
            } catch (e: Exception) {
                onError(e)
            }
        }
        return CONFIRMED_UPDATES_ALL
    }

}