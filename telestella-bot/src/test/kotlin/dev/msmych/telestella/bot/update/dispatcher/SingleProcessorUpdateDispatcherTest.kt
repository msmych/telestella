package dev.msmych.telestella.bot.update.dispatcher

import com.pengrad.telegrambot.model.Update
import dev.msmych.telestella.bot.update.predicate.UpdatePredicate
import dev.msmych.telestella.bot.update.processor.UpdateProcessor
import dev.msmych.telestella.bot.update.processor.UpdateProcessor.Companion.NO_ACTION
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SingleProcessorUpdateDispatcherTest {

    private val u1 = mockk<Update>()
    private val p1 = UpdateProcessor {}

    private val u2 = mockk<Update>()
    private val p2 = UpdateProcessor {}

    @Test
    fun `should dispatch updates`() {
        val dispatcher =
            SingleProcessorUpdateDispatcher(
                UpdatePredicate { it == u1 } to p1,
                UpdatePredicate { it == u2 } to p2)

        assertThat(dispatcher.dispatch(u1)).isEqualTo(p1)
        assertThat(dispatcher.dispatch(u2)).isEqualTo(p2)
    }

    @Test
    fun `should skip update if no compatible processors`() {
        val dispatcher =
            SingleProcessorUpdateDispatcher(
                UpdatePredicate { false } to p1,
                UpdatePredicate { it == u2 } to p2)

        assertThat(dispatcher.dispatch(u1)).isEqualTo(NO_ACTION)
    }

    @Test
    fun `should skip update if more than one compatible processor`() {
        val dispatcher =
            SingleProcessorUpdateDispatcher(
                UpdatePredicate { it == u1 } to p1,
                UpdatePredicate { true } to p2)

        assertThat(dispatcher.dispatch(u1)).isEqualTo(NO_ACTION)
    }
}