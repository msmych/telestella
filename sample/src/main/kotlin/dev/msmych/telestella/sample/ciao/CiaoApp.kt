package dev.msmych.telestella.sample.ciao

import dev.msmych.telestella.bot.Bot
import dev.msmych.telestella.bot.update.dispatcher.SingleProcessorUpdateDispatcher
import dev.msmych.telestella.bot.update.listener.OneTryUpdatesListener
import dev.msmych.telestella.bot.update.predicate.CommandPredicate.Companion.command
import dev.msmych.telestella.bot.update.predicate.TextMessagePredicate.Companion.text
import dev.msmych.telestella.bot.update.processor.AnswerMessageProcessor.Companion.answer

/**
 * A cute bot that is happy to say 'Ciao'
 *
 * To run:
 * ```
 * ./gradlew sample:build
 * java -jar sample/build/libs/sample.jar [bot token]
 * ```
 */
fun main(args: Array<String>) {
    val bot = Bot(args[0])
    val dispatcher = SingleProcessorUpdateDispatcher(
        bot.command("/help") to bot.answer("Just say *Ciao*"),
        text("Ciao", "Hello", "Salut", ignoreCase = true) to bot.answer("Ciao")
    )
    bot.setUpdatesListener(OneTryUpdatesListener(dispatcher))
}