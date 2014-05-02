/*
 *
 * Magnetic Track Parser
 * https://github.com/sualeh/magnetictrackparser
 * Copyright (c) 2014, Sualeh Fatehi.
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


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.threeten.bp.YearMonth;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.temporal.ChronoUnit;

import us.fatehi.magnetictrack.bankcard.ExpirationDate;

public class IsExpiredTest
{

  private static final DateTimeFormatter formatter = DateTimeFormatter
    .ofPattern("yyMM");

  @Test
  public void isExpired1()
  {
    final YearMonth expirationDateNextMonth = YearMonth.now()
      .plus(1, ChronoUnit.MONTHS);
    check(expirationDateNextMonth, false);
  }

  @Test
  public void isExpired2()
  {
    final YearMonth expirationDateThisMonth = YearMonth.now();
    check(expirationDateThisMonth, false);
  }

  @Test
  public void isExpired3()
  {
    final YearMonth expirationDateLastMonth = YearMonth.now()
      .minus(1, ChronoUnit.MONTHS);
    check(expirationDateLastMonth, true);
  }

  private void check(final YearMonth expectedExpirationDate,
                     final boolean isExpired)
  {
    final String rawExpirationDate = formatter.format(expectedExpirationDate);
    final ExpirationDate expirationDate = new ExpirationDate(rawExpirationDate);
    assertEquals(rawExpirationDate, expirationDate.getRawTrackData());
    assertTrue("Should have expiration date",
               expirationDate.hasExpirationDate());
    assertTrue(isExpired? expirationDate.isExpired(): !expirationDate
      .isExpired());
    assertEquals(expectedExpirationDate, expirationDate.getExpirationDate());

    System.out.println(String.format("%s - %s",
                                     expectedExpirationDate,
                                     expirationDate.getExpirationDateAsDate()));
  }

}
