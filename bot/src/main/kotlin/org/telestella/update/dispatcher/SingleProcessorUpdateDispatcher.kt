package org.telestella.update.dispatcher

import com.pengrad.telegrambot.model.Update
import org.telestella.Bot
import org.telestella.update.predicate.UpdatePredicate
import org.telestella.update.processor.NO_ACTION
import org.telestella.update.processor.UpdateProcessor

/**
 * Finds appropriate [UpdateProcessor] for [Update] based on [config]
 *
 * In case number of appropriate processors is different from one, returns [NO_ACTION]
 */
class SingleProcessorUpdateDispatcher(private val config: Map<UpdatePredicate, UpdateProcessor>) : UpdateDispatcher {

    constructor(vararg config: Pair<UpdatePredicate, UpdateProcessor>) : this(config.toMap())

    override fun apply(update: Update, bot: Bot): UpdateProcessor {
        return config.filter { (p, _) -> p.test(update, bot) }
            .map { (_, p) -> p }
            .takeIf { it.size == 1 }
            ?.first()
            ?: NO_ACTION
    }
}