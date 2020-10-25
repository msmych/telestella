package org.telestella.update.predicate

import com.pengrad.telegrambot.model.Message
import com.pengrad.telegrambot.model.Update
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.telestella.Bot
import org.telestella.update.predicate.IsTextMessagePredicate.Companion.IS_TEXT_MESSAGE

internal class IsTextMessagePredicateTest {

    private val bot = mockk<Bot>()

    private val update = mockk<Update>()
    private val message = mockk<Message>()

    @BeforeEach
    fun setUp() {
        every { update.message() } returns message
    }

    @Test
    fun `should return true if update is text message`() {
        every { message.text() } returns "Ciao"

        assertThat(IS_TEXT_MESSAGE.test(update, bot)).isTrue
    }

    @Test
    fun `should return false if update is not text message`() {
        every { message.text() } returns null

        assertThat(IS_TEXT_MESSAGE.test(update, bot)).isFalse
    }
}