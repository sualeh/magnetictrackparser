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


/**
 * The first digit of a credit card number is the Major Industry
 * Identifier (MII) (see ISO/IEC 7812), which represents the category of
 * entity which issued the card.
 * 
 * @see <a
 *      href="https://en.wikipedia.org/wiki/Bank_card_number#Major_Industry_Identifier_.28MII.29">Major
 *      Industry Identifier</a>
 * @author Sualeh Fatehi
 */
public enum MajorIndustryIdentifier
{

  unknown(-1, "unknown"),
  mii_0(0, "ISO/TC 68 and other future industry assignments"),
  mii_1(1, "Airlines"),
  mii_2(2, "Airlines and other future industry assignments"),
  mii_3(3, "Travel and entertainment and banking/financial"),
  mii_4(4, "Banking and financial"),
  mii_5(5, "Banking and financial"),
  mii_6(6, "Merchandising and banking/financial"),
  mii_7(7, "Petroleum and other future industry assignments"),
  mii_8(8,
    "Healthcare, telecommunications and other future industry assignments"),
  mii_9(9, "National assignment"), ;

  /**
   * Parses MII value.
   * 
   * @param accountNumber
   *        Card primary account number.
   * @return MII value.
   */
  public static MajorIndustryIdentifier from(final String accountNumber)
  {
    if (accountNumber != null && !accountNumber.isEmpty())
    {
      final int value = Character.digit(accountNumber.charAt(0), 10);
      for (final MajorIndustryIdentifier mii: values())
      {
        if (mii.getValue() == value)
        {
          return mii;
        }
      }
    }
    return unknown;
  }

  private final int value;
  private final String description;

  private MajorIndustryIdentifier(final int value, final String description)
  {
    this.value = value;
    this.description = description;
  }

  /**
   * Gets the description.
   * 
   * @return Description.
   */
  public String getDescription()
  {
    return description;
  }

  /**
   * Gets the MII value.
   * 
   * @return MII value.
   */
  public int getValue()
  {
    return value;
  }

  /**
   * @see java.lang.Enum#toString()
   */
  @Override
  public String toString()
  {
    return String.format("%d - %s", value, description);
  }

}
