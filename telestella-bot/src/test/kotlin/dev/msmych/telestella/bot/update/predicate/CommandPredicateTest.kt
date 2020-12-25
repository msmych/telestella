package dev.msmych.telestella.bot.update.predicate

import com.pengrad.telegrambot.model.Message
import com.pengrad.telegrambot.model.Update
import dev.msmych.telestella.bot.Bot
import dev.msmych.telestella.bot.update.predicate.CommandPredicate.Companion.command
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class CommandPredicateTest {

    private val bot = mockk<Bot>()

    private val predicate = bot.command("command")

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

        assertThat(predicate.appliesTo(update)).isTrue
    }

    @Test
    fun `should return true for slash-command-at-bot-username`() {
        every { message.text() } returns "/command@TelestellaBot"

        assertThat(predicate.appliesTo(update)).isTrue
    }

    @Test
    fun `should return false if not slash-command`() {
        every { message.text() } returns "command"

        assertThat(predicate.appliesTo(update)).isFalse
    }

    @Test
    fun `should return false for wrong command`() {
        every { message.text() } returns "/wrong"

        assertThat(predicate.appliesTo(update)).isFalse
    }

    @Test
    fun `should trim leading slash`() {
        every { message.text() } returns "/command"

        assertThat(predicate.appliesTo(update)).isTrue
    }

    @Test
    fun `should return true if starts with slash-command`() {
        every { message.text() } returns "/command A"

        assertThat(predicate.appliesTo(update)).isTrue
    }

    @Test
    fun `should return true if starts with slash-command-at-bot-username`() {
        every { message.text() } returns "/command@TelestellaBot hamburger"

        assertThat(predicate.appliesTo(update)).isTrue
    }
}