package dev.msmych.telestella.bot.update.predicate

import com.pengrad.telegrambot.model.Update

fun interface UpdatePredicate {

    fun appliesTo(update: Update): Boolean

    companion object {

        fun allApply(vararg predicates: UpdatePredicate): UpdatePredicate =
            allApply(predicates.toList())

        fun allApply(predicates: List<UpdatePredicate>): UpdatePredicate =
            UpdatePredicate { update ->
                predicates.all { it.appliesTo(update) }
            }

        fun anyApplies(vararg predicates: UpdatePredicate): UpdatePredicate =
            anyApplies(predicates.toList())

        fun anyApplies(predicates: List<UpdatePredicate>): UpdatePredicate =
            UpdatePredicate { update ->
                predicates.any { it.appliesTo(update) }
            }
    }
}