package dev.msmych.telestella.bot.update.predicate

import com.pengrad.telegrambot.model.Message
import com.pengrad.telegrambot.model.Update
import dev.msmych.telestella.bot.Bot
import dev.msmych.telestella.bot.update.predicate.TextMessagePredicate.Companion.regex
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class TextMessagePredicateTest {

    private val bot = mockk<Bot>()

    private val update = mockk<Update>()
    private val message = mockk<Message>()

    @BeforeEach
    fun setUp() {
        every { update.message() } returns message
    }

    @Test
    fun `should return true for matching regex`() {
        every { message.text() } returns "2020-02-20"

        assertThat(regex("[0-9]{4}-[0-9]{2}-[0-9]{2}").appliesTo(update, bot)).isTrue
    }
}