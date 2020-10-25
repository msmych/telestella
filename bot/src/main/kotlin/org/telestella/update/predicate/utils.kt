package org.telestella.update.predicate

import com.pengrad.telegrambot.model.Update
import org.telestella.Bot
import java.util.function.BiPredicate

typealias UpdatePredicate = BiPredicate<Update, Bot>