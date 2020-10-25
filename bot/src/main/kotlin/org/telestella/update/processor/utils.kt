package org.telestella.update.processor

import com.pengrad.telegrambot.model.Update
import org.telestella.Bot
import java.util.function.BiConsumer

typealias UpdateProcessor = BiConsumer<Update, Bot>

val NO_ACTION = UpdateProcessor { _, _ -> }