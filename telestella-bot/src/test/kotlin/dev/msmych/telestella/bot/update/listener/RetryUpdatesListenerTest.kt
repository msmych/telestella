package dev.msmych.telestella.bot.update.listener

import com.pengrad.telegrambot.UpdatesListener.CONFIRMED_UPDATES_ALL
import com.pengrad.telegrambot.UpdatesListener.CONFIRMED_UPDATES_NONE
import com.pengrad.telegrambot.model.Update
import dev.msmych.telestella.bot.update.dispatcher.UpdateDispatcher
import dev.msmych.telestella.bot.update.processor.UpdateProcessor
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class RetryUpdatesListenerTest {

    private val dispatcher = mockk<UpdateDispatcher>()

    private val listener = RetryUpdatesListener(dispatcher)

    private val u1 = mockk<Update>()
    private val p1 = mockk<UpdateProcessor>()
    private val u2 = mockk<Update>()
    private val p2 = mockk<UpdateProcessor>()

    @BeforeEach
    fun setUp() {
        every { p1.process(u1) } answers { callOriginal() }
        every { dispatcher.dispatch(u1) } returns p1
        every { u1.updateId() } returns 111
        every { p2.process(u2) } answers { callOriginal() }
        every { dispatcher.dispatch(u2) } returns p2
        every { u2.updateId() } returns 222
    }

    @Test
    fun `should process all updates`() {
        val rs = listener.process(listOf(u1, u2))

        assertThat(rs).isEqualTo(CONFIRMED_UPDATES_ALL)
        verify { p1.process(u1) }
        verify { p2.process(u2) }
    }

    @Test
    fun `should return last processed if failed to process`() {
        every { p2.process(u2) } throws Exception()
        val rs = listener.process(listOf(u1, u2))

        assertThat(rs).isEqualTo(111)
        verify { p1.process(u1) }
    }

    @Test
    fun `should return last processed if failed to dispatch`() {
        every { dispatcher.dispatch(u2) } throws Exception()

        val rs = listener.process(listOf(u1, u2))

        assertThat(rs).isEqualTo(111)
        verify { p1.process(u1) }
        verify(exactly = 0) { p2.process(u2) }
    }

    @Test
    fun `should process none if failed to process first update`() {
        every { p1.process(u1) } throws Exception()

        val rs = listener.process(listOf(u1, u2))

        assertThat(rs).isEqualTo(CONFIRMED_UPDATES_NONE)
    }

    @Test
    fun `should apply pre-check`() {
        val rs = RetryUpdatesListener(dispatcher, preCheck = { false })
            .process(listOf(u1, u2))

        assertThat(rs).isEqualTo(CONFIRMED_UPDATES_ALL)
        verify(exactly = 0) { p1.process(u1) }
        verify(exactly = 0) { p2.process(u2) }
    }
}