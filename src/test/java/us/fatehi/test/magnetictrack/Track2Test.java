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

import us.fatehi.creditcardnumber.CardBrand;
import us.fatehi.creditcardnumber.MajorIndustryIdentifier;
import us.fatehi.creditcardnumber.ServiceCode1;
import us.fatehi.creditcardnumber.ServiceCode2;
import us.fatehi.creditcardnumber.ServiceCode3;
import us.fatehi.magnetictrack.Track2;

public class Track2Test {

  @Test
  public void track2__1() {
    final String track2Data = ";5266092201416174=16042010000056700100?";
    final Track2 track2 = Track2.from(track2Data);
    checkCardData(track2);
  }

  @Test
  public void track2__2() {
    final String track2Data =
        "%B5266092201416174^FATEHI/SUALEH^16042010000000000000000000000000000567001000?;5266092201416174=16042010000056700100?";
    final Track2 track2 = Track2.from(track2Data);
    checkCardData(track2);
  }

  @Test
  public void track2_dispose() {
    final String track2Data = ";5266092201416174=16042010000056700100somelong12345678901234567890";
    final Track2 track2 = Track2.from(track2Data);
    track2.disposeRawData();

    assertThat(track2.exceedsMaximumLength(), is(false));
  }

  private void checkCardData(final Track2 track2) {
    assertThat(track2.getRawData(), is(";5266092201416174=16042010000056700100?"));
    assertThat(track2.toString(), is(track2.getRawData()));
    assertThat(track2.getAccountNumber().getAccountNumber(), is("5266092201416174"));
    assertThat(track2.getAccountNumber().getCardBrand(), is(CardBrand.MasterCard));
    assertThat(
        track2.getAccountNumber().getMajorIndustryIdentifier(), is(MajorIndustryIdentifier.mii_5));
    assertThat(track2.getExpirationDate().toString(), is("2016-04"));
    assertThat(track2.getServiceCode().toString(), is("201"));
    assertThat(track2.getServiceCode().getServiceCode1(), is(ServiceCode1.v_2));
    assertThat(track2.getServiceCode().getServiceCode2(), is(ServiceCode2.v_0));
    assertThat(track2.getServiceCode().getServiceCode3(), is(ServiceCode3.v_1));
    assertThat(track2.getDiscretionaryData(), is("0000056700100"));
    assertThat(track2.exceedsMaximumLength(), is(false));
  }
}
