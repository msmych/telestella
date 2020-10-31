package dev.msmych.telestella.bot.update.predicate

import com.pengrad.telegrambot.model.Update
import dev.msmych.telestella.bot.Bot

fun interface UpdatePredicate {

    fun appliesTo(update: Update, bot: Bot): Boolean

    companion object {

        fun allApply(vararg predicates: UpdatePredicate): UpdatePredicate =
            allApply(predicates.toList())

        fun allApply(predicates: List<UpdatePredicate>): UpdatePredicate =
            UpdatePredicate { u, b ->
                predicates.all { it.appliesTo(u, b) }
            }

        fun anyApplies(vararg predicates: UpdatePredicate): UpdatePredicate =
            anyApplies(predicates.toList())

        fun anyApplies(predicates: List<UpdatePredicate>): UpdatePredicate =
            UpdatePredicate { u, b ->
                predicates.any { it.appliesTo(u, b) }
            }
    }
}