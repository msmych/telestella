package dev.msmych.telestella.bot.update.predicate

import com.pengrad.telegrambot.model.Message
import com.pengrad.telegrambot.model.Update
import dev.msmych.telestella.bot.update.predicate.IsTextMessagePredicate.Companion.IS_TEXT_MESSAGE
import dev.msmych.telestella.bot.update.predicate.TextMessagePredicate.Companion.regex
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class IsTextMessagePredicateTest {

    private val update = mockk<Update>()
    private val message = mockk<Message>()

    @BeforeEach
    fun setUp() {
        every { update.message() } returns message
    }

    @Test
    fun `should return true if update is text message`() {
        every { message.text() } returns "Ciao"

        assertThat(IS_TEXT_MESSAGE.appliesTo(update)).isTrue
    }

    @Test
    fun `should return false if update is not text message`() {
        every { message.text() } returns null

        assertThat(IS_TEXT_MESSAGE.appliesTo(update)).isFalse
    }

    @Test
    fun `should return true for matching regex`() {
        every { message.text() } returns "2020"

        assertThat(regex("[0-9]{4}").appliesTo(update)).isTrue
    }
}