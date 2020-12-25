package dev.msmych.telestella.bot.update.processor

import com.pengrad.telegrambot.model.Update
import com.pengrad.telegrambot.model.request.Keyboard

class TextAndMarkup(private val provider: TextAndMarkupProvider) {

    private var text: String? = null
    private var markup: Keyboard? = null

    fun text(update: Update): String {
        load(update)
        return this.text!!
    }

    fun markup(update: Update): Keyboard {
        load(update)
        return this.markup!!
    }

    private fun load(update: Update) {
        if (this.text == null) {
            val (text, markup) = provider.textAndMarkup(update)
            this.text = text
            this.markup = markup
        }
    }

}