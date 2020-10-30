package dev.msmych.telestella.bot.update.predicate

import com.pengrad.telegrambot.model.Update
import dev.msmych.telestella.bot.Bot

fun interface UpdatePredicate {

    fun appliesTo(update: Update, bot: Bot): Boolean
}