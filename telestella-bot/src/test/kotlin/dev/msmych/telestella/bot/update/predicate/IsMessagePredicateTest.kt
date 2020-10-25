package dev.msmych.telestella.bot.update.predicate

import com.pengrad.telegrambot.model.Update
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import dev.msmych.telestella.bot.Bot
import dev.msmych.telestella.bot.update.predicate.IsMessagePredicate.Companion.IS_MESSAGE

internal class IsMessagePredicateTest {

    private val bot = mockk<Bot>()

    private val update = mockk<Update>()

    @Test
    fun `should return true if update is message`() {
        every { update.message() } returns mockk()

        assertThat(IS_MESSAGE.test(update, bot)).isTrue
    }

    @Test
    fun `should return false if update is not message`() {
        every { update.message() } returns null

        assertThat(IS_MESSAGE.test(update, bot)).isFalse
    }
}