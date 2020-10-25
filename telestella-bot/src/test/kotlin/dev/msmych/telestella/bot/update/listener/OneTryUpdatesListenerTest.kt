package dev.msmych.telestella.bot.update.listener

import com.pengrad.telegrambot.UpdatesListener.CONFIRMED_UPDATES_ALL
import com.pengrad.telegrambot.model.Update
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import dev.msmych.telestella.bot.Bot
import dev.msmych.telestella.bot.update.dispatcher.UpdateDispatcher
import dev.msmych.telestella.bot.update.processor.UpdateProcessor

internal class OneTryUpdatesListenerTest {

    private val bot = mockk<Bot>()
    private val dispatcher = mockk<UpdateDispatcher>()

    private val listener = OneTryUpdatesListener(bot, dispatcher)

    private val u1 = mockk<Update>()
    private val p1 = mockk<UpdateProcessor>()
    private val u2 = mockk<Update>()
    private val p2 = mockk<UpdateProcessor>()

    @BeforeEach
    fun setUp() {
        every { p1.accept(u1, bot) } answers { callOriginal() }
        every { dispatcher.apply(u1, bot) } returns p1
        every { u1.updateId() } returns 111
        every { p2.accept(u2, bot) } answers { callOriginal() }
        every { dispatcher.apply(u2, bot) } returns p2
        every { u2.updateId() } returns 222
    }

    @Test
    fun `should process all updates`() {
        val rs = listener.process(listOf(u1, u2))

        assertThat(rs).isEqualTo(CONFIRMED_UPDATES_ALL)
        verify { p1.accept(u1, bot) }
        verify { p2.accept(u2, bot) }
    }

    @Test
    fun `should confirm all if failed processing update`() {
        every { p2.accept(u2, bot) } throws Exception()

        val rs = listener.process(listOf(u1, u2))

        assertThat(rs).isEqualTo(CONFIRMED_UPDATES_ALL)
        verify { p1.accept(u1, bot) }
    }

    @Test
    fun `should confirm all if failed during dispatch`() {
        every { dispatcher.apply(u1, bot) } throws Exception()

        val rs = listener.process(listOf(u1, u2))

        assertThat(rs).isEqualTo(CONFIRMED_UPDATES_ALL)
        verify(exactly = 0) { p1.accept(u1, bot) }
    }
}