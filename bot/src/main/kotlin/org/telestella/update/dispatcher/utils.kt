package org.telestella.update.dispatcher

import com.pengrad.telegrambot.model.Update
import org.telestella.Bot
import org.telestella.update.processor.UpdateProcessor
import java.util.function.BiFunction

typealias UpdateDispatcher = BiFunction<Update, Bot, UpdateProcessor>