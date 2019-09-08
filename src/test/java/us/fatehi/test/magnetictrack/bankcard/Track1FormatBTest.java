/*
 *
 * Magnetic Track Parser
 * https://github.com/sualeh/magnetictrackparser
 * Copyright (c) 2014-2016, Sualeh Fatehi.
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
import us.fatehi.creditcardnumber.*;
import us.fatehi.magnetictrack.bankcard.Track1FormatB;

public class Track1FormatBTest
{

  @Test
  public void track1FormatB1()
  {
    final String track1FormatBData = "%B5266092201416174^FATEHI/SUALEH^16042010000000000000000000000000000567001000?";
    final Track1FormatB track1FormatB = Track1FormatB.from(track1FormatBData);
    checkCardData(track1FormatB);
  }

  @Test
  public void track1FormatB2()
  {
    final String track1FormatBData = "%B5266092201416174^FATEHI/SUALEH^16042010000000000000000000000000000567001000?\n;5266092201416174=16042010000056700100?";
    final Track1FormatB track1FormatB = Track1FormatB.from(track1FormatBData);
    checkCardData(track1FormatB);
  }

  @Test
  public void track1FormatB3()
  {
    final String track1FormatBData = "%B5266092201416174^FATEHI/SUALEH^16042010000000000000000000000000000567001000?;5266092201416174=16042010000056700100?";
    final Track1FormatB track1FormatB = Track1FormatB.from(track1FormatBData);
    checkCardData(track1FormatB);
  }

  private void checkCardData(final Track1FormatB track1FormatB)
  {
    assertThat(track1FormatB.getRawData(),
               is(
                 "%B5266092201416174^FATEHI/SUALEH^16042010000000000000000000000000000567001000?"));
    assertThat(track1FormatB.getFormatCode(), is("B"));
    assertThat(track1FormatB.getAccountNumber().getAccountNumber(),
               is("5266092201416174"));
    assertThat(track1FormatB.getAccountNumber().getCardBrand(),
               is(CardBrand.MasterCard));
    assertThat(track1FormatB.getAccountNumber().getMajorIndustryIdentifier(),
               is(MajorIndustryIdentifier.mii_5));
    assertThat(track1FormatB.getName().toString(), is("Sualeh Fatehi"));
    assertThat(track1FormatB.getName().getFirstName(), is("Sualeh"));
    assertThat(track1FormatB.getName().getLastName(), is("Fatehi"));
    assertThat(track1FormatB.getExpirationDate().toString(), is("2016-04"));
    assertThat(track1FormatB.getServiceCode().toString(), is("201"));
    assertThat(track1FormatB.getServiceCode().getServiceCode1(),
               is(ServiceCode1.v_2));
    assertThat(track1FormatB.getServiceCode().getServiceCode2(),
               is(ServiceCode2.v_0));
    assertThat(track1FormatB.getServiceCode().getServiceCode3(),
               is(ServiceCode3.v_1));
    assertThat(track1FormatB.getDiscretionaryData(),
               is("0000000000000000000000000000567001000"));
  }

}
