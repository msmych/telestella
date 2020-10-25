
package org.telestella.sample.ciao

import org.telestella.Bot
import org.telestella.update.dispatcher.SingleProcessorUpdateDispatcher
import org.telestella.update.listener.OneTryUpdatesListener
import org.telestella.update.predicate.CommandPredicate.Companion.command
import org.telestella.update.predicate.TextMessagePredicate.Companion.text
import org.telestella.update.processor.AnswerMessageProcessor.Companion.answer

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
        command("/help") to answer("Just say *Ciao*"),
        text("Ciao", "Hello", "Salut", ignoreCase = true) to answer("Ciao"))
    bot.setUpdatesListener(OneTryUpdatesListener(bot, dispatcher))
}