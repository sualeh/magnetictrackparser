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
package us.fatehi.magnetictrack.bankcard;


import java.util.regex.Pattern;

/**
 * @see <a
 *      href="http://www.regular-expressions.info/creditcard.html">Finding
 *      or Verifying Credit Card Numbers</a>
 * @author Sualeh Fatehi
 */
public enum CardBrand
{

  unknown(Pattern.compile("^unknown$")),
  Visa(Pattern.compile("^4[0-9]{6,}$")),
  // MasterCard numbers start with the numbers 51 through 55, but this
  // will only detect MasterCard credit cards; there are other cards
  // issued using the MasterCard system that do not fall into this IIN
  // range.
  MasterCard(Pattern.compile("^5[1-5][0-9]{5,}$")),
  AmericanExpress(Pattern.compile("^3[47][0-9]{5,}$")),
  // Diners Club card numbers begin with 300 through 305, 36 or 38.
  // There are Diners Club cards that begin with 5 and have 16 digits.
  // These are a joint venture between Diners Club and MasterCard, and
  // should be processed like a MasterCard.
  DinersClub(Pattern.compile("^3(?:0[0-5]|[68][0-9])[0-9]{4,}$")),
  Discover(Pattern.compile("^6(?:011|5[0-9]{2})[0-9]{3,}$")),
  JCB(Pattern.compile("^(?:2131|1800|35[0-9]{3})[0-9]{3,}$")), ;

  public static CardBrand from(final String accountNumber)
  {
    if (accountNumber == null)
    {
      return unknown;
    }
    for (final CardBrand cardBrand: values())
    {
      if (cardBrand.pattern.matcher(accountNumber).matches())
      {
        return cardBrand;
      }
    }
    return unknown;
  }

  private final Pattern pattern;

  private CardBrand(final Pattern pattern)
  {
    this.pattern = pattern;
  }

}
