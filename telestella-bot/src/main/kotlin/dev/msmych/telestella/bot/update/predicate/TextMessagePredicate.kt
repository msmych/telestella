package dev.msmych.telestella.bot.update.predicate

import com.pengrad.telegrambot.model.Update
import dev.msmych.telestella.bot.Bot
import dev.msmych.telestella.bot.update.predicate.UpdatePredicate.Companion.allApply
import dev.msmych.telestella.bot.update.predicate.UpdatePredicate.Companion.anyApplies

/**
 * Tests specified check on message text
 */
abstract class TextMessagePredicate : IsTextMessagePredicate() {

    override fun appliesTo(update: Update, bot: Bot): Boolean {
        return super.appliesTo(update, bot) && checkText(update.message().text(), bot)
    }

    abstract fun checkText(text: String, bot: Bot): Boolean

    companion object {

        /**
         * Text equals one of check
         *
         * Example:  `textOneOf("Ciao", "Hello", "Salut")` is true for `Ciao`, `Hello`, or `Hola`
         */
        fun text(vararg text: String, ignoreCase: Boolean = false) =
            anyApplies(text.map {
                textThat { t ->
                    t.equals(it, ignoreCase)
                }
            })

        /**
         * Text contains one of check
         *
         * Example: `textContains("rain", "snow")` is true for `Is it going to rain today?`
         */
        fun textContains(vararg text: String, ignoreCase: Boolean = false) =
            anyApplies(text.map {
                textThat { t ->
                    t.contains(it, ignoreCase)
                }
            })

        /**
         * Text contains all of check
         *
         * Example: `textContainsAll("Stock", "Barrel", "Lock")` is true for `Lock, Stock and Two Smoking Barrels`
         */
        fun textContainsAll(vararg text: String, ignoreCase: Boolean = false) =
            allApply(text.map {
                textThat { t ->
                    t.contains(it, ignoreCase)
                }
            })

        /**
         * Regex check
         *
         * Example: `regex("[A-Z][a-z]+")` is true for `Robert`
         */
        fun regex(pattern: String) = textThat { it.equals(pattern.toRegex()) }

        /**
         * Predicate check
         *
         * Example: `textThat { it.endsWith("!") }` is true for `Cheers!`
         */
        fun textThat(predicate: (t: String) -> Boolean) = object : TextMessagePredicate() {
            override fun checkText(text: String, bot: Bot): Boolean {
                return predicate(text)
            }
        }
    }
}