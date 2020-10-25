package dev.msmych.telestella.bot.update.predicate

import com.pengrad.telegrambot.model.Update
import dev.msmych.telestella.bot.Bot
import java.util.function.BiPredicate

typealias UpdatePredicate = BiPredicate<Update, Bot>