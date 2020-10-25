package org.telestella.update.listener

import com.pengrad.telegrambot.UpdatesListener.CONFIRMED_UPDATES_ALL
import com.pengrad.telegrambot.UpdatesListener.CONFIRMED_UPDATES_NONE
import com.pengrad.telegrambot.model.Update
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.telestella.Bot
import org.telestella.update.dispatcher.UpdateDispatcher
import org.telestella.update.processor.UpdateProcessor

internal class RetryUpdatesListenerTest {

    private val bot = mockk<Bot>()
    private val dispatcher = mockk<UpdateDispatcher>()

    private val listener = RetryUpdatesListener(bot, dispatcher)

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
    fun `should return last processed if failed to process`() {
        every { p2.accept(u2, bot) } throws Exception()
        val rs = listener.process(listOf(u1, u2))

        assertThat(rs).isEqualTo(111)
        verify { p1.accept(u1, bot) }
    }

    @Test
    fun `should return last processed if failed to dispatch`() {
        every { dispatcher.apply(u2, bot) } throws Exception()

        val rs = listener.process(listOf(u1, u2))

        assertThat(rs).isEqualTo(111)
        verify { p1.accept(u1, bot) }
        verify(exactly = 0) { p2.accept(u2, bot) }
    }

    @Test
    fun `should process none if failed to process first update`() {
        every { p1.accept(u1, bot) } throws Exception()

        val rs = listener.process(listOf(u1, u2))

        assertThat(rs).isEqualTo(CONFIRMED_UPDATES_NONE)
    }
}