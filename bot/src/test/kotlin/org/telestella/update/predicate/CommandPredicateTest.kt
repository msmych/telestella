package org.telestella.update.predicate

import com.pengrad.telegrambot.model.Message
import com.pengrad.telegrambot.model.Update
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.telestella.Bot
import org.telestella.update.predicate.CommandPredicate.Companion.command

internal class CommandPredicateTest {

    private val bot = mockk<Bot>()

    private val predicate = command("command")

    private val update = mockk<Update>()
    private val message = mockk<Message>()

    @BeforeEach
    fun setUp() {
        every { bot.info.username() } returns "TelestellaBot"
        every { update.message() } returns message
    }

    @Test
    fun `should return true for slash-command`() {
        every { message.text() } returns "/command"

        assertThat(predicate.test(update, bot)).isTrue
    }

    @Test
    fun `should return true for slash-command-at-bot-username`() {
        every { message.text() } returns "/command@TelestellaBot"

        assertThat(predicate.test(update, bot)).isTrue
    }

    @Test
    fun `should return false if not slash-command`() {
        every { message.text() } returns "command"

        assertThat(predicate.test(update, bot)).isFalse
    }

    @Test
    fun `should return false for wrong command`() {
        every { message.text() } returns "/wrong"

        assertThat(predicate.test(update, bot)).isFalse
    }

    @Test
    fun `should trim leading slash`() {
        every { message.text() } returns "/command"

        assertThat(command("/command").test(update, bot)).isTrue
    }

    @Test
    fun `should return true if starts with slash-command`() {
        every { message.text() } returns "/command A"

        assertThat(predicate.test(update, bot)).isTrue
    }

    @Test
    fun `should return true if starts with slash-command-at-bot-username`() {
        every { message.text() } returns "/command@TelestellaBot hamburger"

        assertThat(predicate.test(update, bot)).isTrue
    }
}