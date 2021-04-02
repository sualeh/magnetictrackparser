/*
 *
 * Magnetic Track Parser
 * https://github.com/sualeh/magnetictrackparser
 * Copyright (c) 2014-2021, Sualeh Fatehi.
 *
 * This library is free software; you can redistribute it and/or modify it under the terms
 * of the GNU Lesser General Public License as published by the Free Software Foundation;
 * either version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * library; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA 02111-1307, USA.
 *
 */
package us.fatehi.test.magnetictrack.bankcard;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import us.fatehi.creditcardnumber.CardBrand;
import us.fatehi.creditcardnumber.MajorIndustryIdentifier;
import us.fatehi.creditcardnumber.ServiceCode1;
import us.fatehi.creditcardnumber.ServiceCode2;
import us.fatehi.creditcardnumber.ServiceCode3;
import us.fatehi.magnetictrack.bankcard.Track2;

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

  private void checkCardData(final Track2 track2) {
    assertThat(track2.getRawData(), is(";5266092201416174=16042010000056700100?"));
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
  }
}
