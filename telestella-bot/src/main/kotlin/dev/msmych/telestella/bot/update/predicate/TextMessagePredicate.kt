package dev.msmych.telestella.bot.update.predicate

import com.pengrad.telegrambot.model.Update
import dev.msmych.telestella.bot.update.predicate.UpdatePredicate.Companion.allApply
import dev.msmych.telestella.bot.update.predicate.UpdatePredicate.Companion.anyApplies

/**
 * Tests specified check on message text
 */
abstract class TextMessagePredicate : IsTextMessagePredicate() {

    override fun appliesTo(update: Update): Boolean {
        return super.appliesTo(update) && checkText(update.message().text())
    }

    abstract fun checkText(text: String): Boolean

    companion object {

        /**
         * Text equals one of check
         *
         * Example:  `text("Ciao", "Hello", "Salut")` is true for `Ciao`, `Hello`, or `Hola`
         */
        fun text(vararg texts: String, ignoreCase: Boolean = false) =
            anyApplies(texts.map {
                textThat { t ->
                    t.equals(it, ignoreCase)
                }
            })

        /**
         * Text contains one of check
         *
         * Example: `textContains("rain", "snow")` is true for `Is it going to rain today?`
         */
        fun textContains(vararg texts: String, ignoreCase: Boolean = false) =
            anyApplies(texts.map {
                textThat { t ->
                    t.contains(it, ignoreCase)
                }
            })

        /**
         * Text contains all of check
         *
         * Example: `textContainsAll("Stock", "Barrel", "Lock")` is true for `Lock, Stock and Two Smoking Barrels`
         */
        fun textContainsAll(vararg texts: String, ignoreCase: Boolean = false) =
            allApply(texts.map {
                textThat { t ->
                    t.contains(it, ignoreCase)
                }
            })

        /**
         * Regex check
         *
         * Example: `regex("[A-Z][a-z]+")` is true for `Robert`
         */
        fun regex(pattern: String) = textThat { pattern.toRegex().matches(it) }

        /**
         * Predicate check
         *
         * Example: `textThat { it.endsWith("!") }` is true for `Cheers!`
         */
        fun textThat(predicate: (t: String) -> Boolean) = object : TextMessagePredicate() {
            override fun checkText(text: String): Boolean {
                return predicate(text)
            }
        }
    }
}