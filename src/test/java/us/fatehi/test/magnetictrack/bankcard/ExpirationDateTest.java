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

import us.fatehi.magnetictrack.bankcard.ExpirationDate;

public class ExpirationDateTest
{

  @Test
  public void expirationDate_1()
  {
    final String rawExpirationDate = null;
    final ExpirationDate expirationDate = new ExpirationDate(rawExpirationDate);
    assertEquals(rawExpirationDate, expirationDate.getRawTrackData());
    assertTrue(!expirationDate.hasExpirationDate());
  }

  @Test
  public void expirationDate_2()
  {
    final String rawExpirationDate = "\t\t";
    final ExpirationDate expirationDate = new ExpirationDate(rawExpirationDate);
    assertEquals(rawExpirationDate, expirationDate.getRawTrackData());
    assertTrue("Should not have expiration date",
               !expirationDate.hasExpirationDate());
  }

  @Test
  public void expirationDate_3()
  {
    final String rawExpirationDate = "AQW";
    final ExpirationDate expirationDate = new ExpirationDate(rawExpirationDate);
    assertEquals(rawExpirationDate, expirationDate.getRawTrackData());
    assertTrue("Should not have expiration date",
               !expirationDate.hasExpirationDate());
  }

  @Test
  public void expirationDate_4()
  {
    final String rawExpirationDate = "11";
    final ExpirationDate expirationDate = new ExpirationDate(rawExpirationDate);
    assertEquals(rawExpirationDate, expirationDate.getRawTrackData());
    assertTrue("Should not have expiration date",
               !expirationDate.hasExpirationDate());
  }

  @Test
  public void expirationDate_5()
  {
    final String rawExpirationDate = "8888";
    final ExpirationDate expirationDate = new ExpirationDate(rawExpirationDate);
    assertEquals(rawExpirationDate, expirationDate.getRawTrackData());
    assertTrue("Should not have expiration date",
               !expirationDate.hasExpirationDate());
  }

  @Test
  public void expirationDate_6()
  {
    final String rawExpirationDate = "121212";
    final ExpirationDate expirationDate = new ExpirationDate(rawExpirationDate);
    assertEquals(rawExpirationDate, expirationDate.getRawTrackData());
    assertTrue("Should not have expiration date",
               !expirationDate.hasExpirationDate());
  }

  @Test
  public void expirationDate_7()
  {
    final String rawExpirationDate = "1313";
    final ExpirationDate expirationDate = new ExpirationDate(rawExpirationDate);
    assertEquals(rawExpirationDate, expirationDate.getRawTrackData());
    assertTrue("Should not have expiration date",
               !expirationDate.hasExpirationDate());
  }

  @Test
  public void expirationDate1()
  {
    final String rawExpirationDate = "1212";
    final ExpirationDate expirationDate = new ExpirationDate(rawExpirationDate);
    assertEquals(rawExpirationDate, expirationDate.getRawTrackData());
    assertTrue("Should have expiration date",
               expirationDate.hasExpirationDate());
    assertEquals(YearMonth.of(2012, 12), expirationDate.getExpirationDate());
  }

}
