/*
 *
 * Magnetic Track Parser
 * https://github.com/sualeh/magnetictrackparser
 * Copyright (c) 2014-2021, Sualeh Fatehi.
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
import us.fatehi.magnetictrack.Track1FormatB;

public class Track1FormatBNoSentinelTest {

  @Test
  public void track1FormatB1() {
    final String track1FormatBData =
        "B5266092201416174^FATEHI/SUALEH^16042010000000000000000000000000000567001000";
    final Track1FormatB track1FormatB = Track1FormatB.from(track1FormatBData);
    checkCardData(track1FormatB);
  }

  private void checkCardData(final Track1FormatB track1FormatB) {
    assertThat(
        track1FormatB.getRawData(),
        is("B5266092201416174^FATEHI/SUALEH^16042010000000000000000000000000000567001000"));
    assertThat(track1FormatB.getFormatCode(), is("B"));
    assertThat(track1FormatB.getAccountNumber().getAccountNumber(), is("5266092201416174"));
    assertThat(track1FormatB.getAccountNumber().getCardBrand(), is(CardBrand.MasterCard));
    assertThat(
        track1FormatB.getAccountNumber().getMajorIndustryIdentifier(),
        is(MajorIndustryIdentifier.mii_5));
    assertThat(track1FormatB.getName().toString(), is("Sualeh Fatehi"));
    assertThat(track1FormatB.getName().getFirstName(), is("Sualeh"));
    assertThat(track1FormatB.getName().getLastName(), is("Fatehi"));
    assertThat(track1FormatB.getExpirationDate().toString(), is("2016-04"));
    assertThat(track1FormatB.getServiceCode().toString(), is("201"));
    assertThat(track1FormatB.getServiceCode().getServiceCode1(), is(ServiceCode1.v_2));
    assertThat(track1FormatB.getServiceCode().getServiceCode2(), is(ServiceCode2.v_0));
    assertThat(track1FormatB.getServiceCode().getServiceCode3(), is(ServiceCode3.v_1));
    assertThat(track1FormatB.getDiscretionaryData(), is("0000000000000000000000000000567001000"));
  }
}
