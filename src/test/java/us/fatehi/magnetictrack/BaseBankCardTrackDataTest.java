/*
 *
 * Magnetic Track Parser
 * https://github.com/sualeh/magnetictrackparser
 * Copyright (c) 2014-2026, Sualeh Fatehi.
 *
 */
package us.fatehi.magnetictrack;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import org.junit.jupiter.api.Test;

import us.fatehi.creditcardnumber.AccountNumber;
import us.fatehi.creditcardnumber.AccountNumbers;
import us.fatehi.creditcardnumber.ExpirationDate;
import us.fatehi.creditcardnumber.ServiceCode;

public class BaseBankCardTrackDataTest {

  private class TestBankCardTrackData extends BaseBankCardTrackData {

    private static final long serialVersionUID = 1L;

    TestBankCardTrackData(
        final String rawTrackData,
        final AccountNumber pan,
        final ExpirationDate expirationDate,
        final ServiceCode serviceCode,
        final String discretionaryData) {
      super(rawTrackData, pan, expirationDate, serviceCode, discretionaryData);
    }

    @Override
    public boolean exceedsMaximumLength() {
      return false;
    }
  }

  @Test
  public void testBankCardTrackData() {
    final String rawTrackData = "raw data";
    final String discretionaryData = "discretionary data";

    final TestBankCardTrackData testMock =
        new TestBankCardTrackData(rawTrackData, null, null, null, discretionaryData);

    assertThat(testMock.hasAccountNumber(), is(false));
    assertThat(testMock.getAccountNumber(), is(AccountNumbers.emptyAccountNumber()));

    assertThat(testMock.hasExpirationDate(), is(false));
    assertThat(testMock.getExpirationDate(), is(new ExpirationDate()));

    assertThat(testMock.hasServiceCode(), is(false));
    assertThat(testMock.getServiceCode(), is(new ServiceCode()));

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
