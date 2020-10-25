package dev.msmych.telestella.bot.update.dispatcher

import com.pengrad.telegrambot.model.Update
import dev.msmych.telestella.bot.Bot
import dev.msmych.telestella.bot.update.processor.UpdateProcessor
import java.util.function.BiFunction

typealias UpdateDispatcher = BiFunction<Update, Bot, UpdateProcessor>