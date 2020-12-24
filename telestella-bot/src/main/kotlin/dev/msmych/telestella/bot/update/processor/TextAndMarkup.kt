package dev.msmych.telestella.bot.update.processor

import com.pengrad.telegrambot.model.Update
import com.pengrad.telegrambot.model.request.Keyboard
import dev.msmych.telestella.bot.Bot

class TextAndMarkup(private val provider: TextAndMarkupProvider) {

    private var text: String? = null
    private var markup: Keyboard? = null

    fun text(update: Update, bot: Bot): String {
        if (this.text == null) {
            val (text, markup) = provider.textAndMarkup(update, bot)
            this.text = text
            this.markup = markup
        }
        return this.text!!
    }

    fun markup(update: Update, bot: Bot): Keyboard {
        if (this.markup == null) {
            val (text, markup) = provider.textAndMarkup(update, bot)
            this.text = text
            this.markup = markup
        }
        return this.markup!!
    }

}