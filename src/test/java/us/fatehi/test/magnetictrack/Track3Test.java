/*
 *
 * Magnetic Track Parser
 * https://github.com/sualeh/magnetictrackparser
 * Copyright (c) 2014-2024, Sualeh Fatehi.
 *
 */
package us.fatehi.test.magnetictrack;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import us.fatehi.magnetictrack.Track3;

public class Track3Test {

  @Test
  public void track3__1() {
    final String track3Data = "+6202408082356005=15046200000010000000000004976?";
    final Track3 track3 = Track3.from(track3Data);
    checkCardData(track3);
  }

  @Test
  public void track3__2() {
    final String track3Data =
        "%B378578692630345^ /                        ^1508121140165241?;378578692630345=150812114016524100000?+6202408082356005=15046200000010000000000004976?";
    final Track3 track3 = Track3.from(track3Data);
    checkCardData(track3);
  }

  private void checkCardData(final Track3 track3) {
    assertThat(track3.getRawData(), is("+6202408082356005=15046200000010000000000004976?"));
    assertThat(track3.toString(), is(track3.getRawData()));
    assertThat(track3.getDiscretionaryData(), is("6202408082356005=15046200000010000000000004976"));
    assertThat(track3.exceedsMaximumLength(), is(false));
  }
}
