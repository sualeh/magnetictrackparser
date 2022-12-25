/*
 *
 * Magnetic Track Parser
 * https://github.com/sualeh/magnetictrackparser
 * Copyright (c) 2014-2023, Sualeh Fatehi.
 *
 */
package us.fatehi.magnetictrack;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

public class BaseTrackDataTest {

  private class TestTrackData extends BaseTrackData {

    private static final long serialVersionUID = 1L;

    TestTrackData(final String rawTrackData, final String discretionaryData) {
      super(rawTrackData, discretionaryData);
    }

    @Override
    public boolean exceedsMaximumLength() {
      return false;
    }
  }

  @Test
  public void group() {
    assertThat(BaseTrackData.getGroup(null, 0), is(nullValue()));
    assertThat(BaseTrackData.getGroup(Pattern.compile("abc").matcher("xyz"), 0), is(nullValue()));
    assertThat(BaseTrackData.getGroup(Pattern.compile("abc").matcher("xyz"), 1), is(nullValue()));

    final Pattern twoGroups = Pattern.compile("(abc)(xyz)");
    assertThat(BaseTrackData.getGroup(twoGroups.matcher("abcxyz"), 0), is(nullValue()));
    assertThat(BaseTrackData.getGroup(twoGroups.matcher("abcxyz"), 1), is("abc"));
    assertThat(BaseTrackData.getGroup(twoGroups.matcher("abcxyz"), 2), is("xyz"));
    assertThat(BaseTrackData.getGroup(twoGroups.matcher("abcxyz"), 3), is(nullValue()));
  }

  @Test
  public void testBankCardTrackData1() {
    final String rawTrackData = "raw data";
    final String discretionaryData = "discretionary data";

    final TestTrackData testMock = new TestTrackData(rawTrackData, discretionaryData);

    check(rawTrackData, discretionaryData, testMock);
  }

  @Test
  public void testBankCardTrackData2() {
    final String rawTrackData = "\t\t";
    final String discretionaryData = "\t\t";

    final TestTrackData testMock = new TestTrackData(rawTrackData, discretionaryData);

    check(rawTrackData, discretionaryData, testMock);
  }

  private void check(
      final String rawTrackData, final String discretionaryData, final TestTrackData testMock) {
    assertThat(testMock.hasRawData(), is(true));
    assertThat(testMock.getRawData(), is(rawTrackData));

    testMock.disposeRawData();

    assertThat(testMock.hasRawData(), is(false));
    assertThat(testMock.getRawData(), is(nullValue()));

    assertThat(testMock.hasDiscretionaryData(), is(true));
    assertThat(testMock.getDiscretionaryData(), is(discretionaryData));

    testMock.disposeDiscretionaryData();

    assertThat(testMock.hasDiscretionaryData(), is(false));
    assertThat(testMock.getDiscretionaryData(), is(nullValue()));
  }
}
