package dev.msmych.telestella.bot.update.dispatcher

import com.pengrad.telegrambot.model.Update
import dev.msmych.telestella.bot.update.predicate.UpdatePredicate
import dev.msmych.telestella.bot.update.processor.UpdateProcessor
import dev.msmych.telestella.bot.update.processor.UpdateProcessor.Companion.NO_ACTION

/**
 * Finds appropriate [UpdateProcessor] for [Update] based on [config]
 *
 * In case number of appropriate processors is different from one, returns [NO_ACTION]
 */
class SingleProcessorUpdateDispatcher(private val config: Map<UpdatePredicate, UpdateProcessor>) : UpdateDispatcher {

    constructor(vararg config: Pair<UpdatePredicate, UpdateProcessor>) : this(config.toMap())

    override fun dispatch(update: Update): UpdateProcessor {
        return config.filter { (p, _) -> p.appliesTo(update) }
            .map { (_, p) -> p }
            .takeIf { it.size == 1 }
            ?.first()
            ?: NO_ACTION
    }
}