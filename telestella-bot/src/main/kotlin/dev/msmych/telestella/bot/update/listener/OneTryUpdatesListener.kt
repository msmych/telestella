package dev.msmych.telestella.bot.update.listener

import com.pengrad.telegrambot.UpdatesListener
import com.pengrad.telegrambot.UpdatesListener.CONFIRMED_UPDATES_ALL
import com.pengrad.telegrambot.model.Update
import dev.msmych.telestella.bot.update.dispatcher.UpdateDispatcher
import dev.msmych.telestella.bot.update.predicate.UpdatePredicate

/**
 * Processes every update with processors provided by [dispatcher]
 *
 * Catches exceptions and returns [CONFIRMED_UPDATES_ALL] in any case
 */
class OneTryUpdatesListener(
    private val dispatcher: UpdateDispatcher,
    private val preCheck: UpdatePredicate = UpdatePredicate { true },
    private val onError: (e: Exception) -> Unit = {}
) : UpdatesListener {

    override fun process(updates: List<Update>): Int {
        updates.forEach { update ->
            try {
                if (preCheck.appliesTo(update)) {
                    val processor = dispatcher.dispatch(update)
                    processor.process(update)
                }
            } catch (e: Exception) {
                onError(e)
            }
        }
        return CONFIRMED_UPDATES_ALL
    }

}