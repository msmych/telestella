package dev.msmych.telestella.bot.update.dispatcher

import com.pengrad.telegrambot.model.Update
import dev.msmych.telestella.bot.Bot
import dev.msmych.telestella.bot.update.predicate.UpdatePredicate
import dev.msmych.telestella.bot.update.processor.UpdateProcessor
import dev.msmych.telestella.bot.update.processor.UpdateProcessor.Companion.NO_ACTION
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SingleProcessorUpdateDispatcherTest {

    private val bot = mockk<Bot>()

    private val u1 = mockk<Update>()
    private val p1 = UpdateProcessor { _, _ -> }

    private val u2 = mockk<Update>()
    private val p2 = UpdateProcessor { _, _ -> }

    @Test
    fun `should dispatch updates`() {
        val dispatcher =
            SingleProcessorUpdateDispatcher(
                UpdatePredicate { u, _ -> u == u1 } to p1,
                UpdatePredicate { u, _ -> u == u2 } to p2)

        assertThat(dispatcher.dispatch(u1, bot)).isEqualTo(p1)
        assertThat(dispatcher.dispatch(u2, bot)).isEqualTo(p2)
    }

    @Test
    fun `should skip update if no compatible processors`() {
        val dispatcher =
            SingleProcessorUpdateDispatcher(
                UpdatePredicate { _, _ -> false } to p1,
                UpdatePredicate { u, _ -> u == u2 } to p2)

        assertThat(dispatcher.dispatch(u1, bot)).isEqualTo(NO_ACTION)
    }

    @Test
    fun `should skip update if more than one compatible processor`() {
        val dispatcher =
            SingleProcessorUpdateDispatcher(
                UpdatePredicate { u, _ -> u == u1 } to p1,
                UpdatePredicate { _, _ -> true } to p2)

        assertThat(dispatcher.dispatch(u1, bot)).isEqualTo(NO_ACTION)
    }
}