package org.telestella.update.predicate

import org.telestella.Bot

/**
 * Checks weather a message is a bot command like `/start` or `/help@TeleStellaBot`
 */
class CommandPredicate private constructor(private val command: String) : TextMessagePredicate() {

    override fun checkText(text: String, bot: Bot): Boolean {
        return text.startsWith("/$command") ||
                text.startsWith("/$command@${bot.info.username()}")
    }

    companion object {

        /**
         * Example: `help` command of a bot with username `TeleStellaBot`
         *
         * `command("help")` (or `command("/help")`) are true for all of the following:
         * ```
         * /help
         * /help@TeleStellaBot
         * /help please
         * /help@TeleStellaBot SOS
         * ```
         */
        fun command(command: String) = CommandPredicate(
            if (command.startsWith("/"))
                command.substring(1)
            else command
        )
    }
}