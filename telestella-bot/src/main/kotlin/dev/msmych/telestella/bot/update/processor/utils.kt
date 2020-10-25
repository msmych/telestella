package dev.msmych.telestella.bot.update.processor

import com.pengrad.telegrambot.model.Update
import dev.msmych.telestella.bot.Bot
import java.util.function.BiConsumer

typealias UpdateProcessor = BiConsumer<Update, Bot>

val NO_ACTION = UpdateProcessor { _, _ -> }