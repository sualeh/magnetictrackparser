/*
 *
 * Magnetic Stripe Parser
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

import us.fatehi.magnetictrack.bankcard.CardBrand;
import us.fatehi.magnetictrack.bankcard.MajorIndustryIdentifier;
import us.fatehi.magnetictrack.bankcard.PrimaryAccountNumber;

public class PrimaryAccountNumberTest
{

  @Test
  public void pan_1()
  {
    final PrimaryAccountNumber pan = new PrimaryAccountNumber(null);
    assertTrue(!pan.hasPrimaryAccountNumber());
  }

  @Test
  public void pan_2()
  {
    final PrimaryAccountNumber pan = new PrimaryAccountNumber("\t\t");
    assertTrue(!pan.hasPrimaryAccountNumber());
  }

  @Test
  public void pan_3()
  {
    final String rawAccountNumber = "5266092201416173";
    final PrimaryAccountNumber pan = new PrimaryAccountNumber(rawAccountNumber);
    assertTrue("Should not pass Luhn check", !pan.isPassesLuhnCheck());
    check(pan, rawAccountNumber);
  }

  @Test
  public void pan1()
  {
    final String rawAccountNumber = "5266092201416174";
    final PrimaryAccountNumber pan = new PrimaryAccountNumber(rawAccountNumber);
    assertTrue("Does not pass Luhn check", pan.isPassesLuhnCheck());
    check(pan, rawAccountNumber);
  }

  @Test
  public void pan2()
  {
    final String rawAccountNumber = "  5266-0922-0141-6174  ";
    final PrimaryAccountNumber pan = new PrimaryAccountNumber(rawAccountNumber);
    assertTrue("Does not pass Luhn check", pan.isPassesLuhnCheck());
    final String accountNumber = "5266092201416174";
    check(pan, accountNumber);
  }

  private void check(final PrimaryAccountNumber pan, final String accountNumber)
  {
    assertEquals(accountNumber, pan.getAccountNumber());
    assertEquals(CardBrand.MasterCard, pan.getCardBrand());
    assertEquals(MajorIndustryIdentifier.mii_5,
                 pan.getMajorIndustryIdentifier());
  }

}
