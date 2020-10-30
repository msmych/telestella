package dev.msmych.telestella.bot.update.dispatcher

import com.pengrad.telegrambot.model.Update
import dev.msmych.telestella.bot.Bot
import dev.msmych.telestella.bot.update.predicate.UpdatePredicate
import dev.msmych.telestella.bot.update.processor.NO_ACTION
import dev.msmych.telestella.bot.update.processor.UpdateProcessor

/**
 * Finds appropriate [UpdateProcessor] for [Update] based on [config]
 *
 * In case number of appropriate processors is different from one, returns [NO_ACTION]
 */
class SingleProcessorUpdateDispatcher(private val config: Map<UpdatePredicate, UpdateProcessor>) : UpdateDispatcher {

    constructor(vararg config: Pair<UpdatePredicate, UpdateProcessor>) : this(config.toMap())

    override fun dispatch(update: Update, bot: Bot): UpdateProcessor {
        return config.filter { (p, _) -> p.appliesTo(update, bot) }
            .map { (_, p) -> p }
            .takeIf { it.size == 1 }
            ?.first()
            ?: NO_ACTION
    }
}