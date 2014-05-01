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


import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.left;
import static org.apache.commons.lang3.StringUtils.trimToEmpty;
import us.fatehi.magnetictrack.BaseTrackData;

/**
 * Parses and represents the primary account number of the bank card.
 */
public class PrimaryAccountNumber
  extends BaseTrackData
{

  private static final long serialVersionUID = -7012531091389412459L;

  private final String accountNumber;
  private final CardBrand cardBrand;
  private final MajorIndustryIdentifier majorIndustryIdentifier;
  private final boolean passesLuhnCheck;

  /**
   * No primary account number of the bank card.
   */
  public PrimaryAccountNumber()
  {
    this(null);
  }

  /**
   * Parses the primary account number of the bank card. Can accept card
   * numbers with spaces or dashes.
   * 
   * @param rawAccountNumber
   *        Raw primary account number from the magnetic track data.
   */
  public PrimaryAccountNumber(final String rawAccountNumber)
  {
    super(rawAccountNumber);

    accountNumber = non_digit.matcher(trimToEmpty(rawAccountNumber))
      .replaceAll("");
    passesLuhnCheck = passesLuhnCheck();
    majorIndustryIdentifier = MajorIndustryIdentifier.from(accountNumber);
    cardBrand = CardBrand.from(accountNumber);
  }

  /**
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(final Object obj)
  {
    if (this == obj)
    {
      return true;
    }
    if (obj == null)
    {
      return false;
    }
    if (!(obj instanceof PrimaryAccountNumber))
    {
      return false;
    }
    final PrimaryAccountNumber other = (PrimaryAccountNumber) obj;
    if (accountNumber == null)
    {
      if (other.accountNumber != null)
      {
        return false;
      }
    }
    else if (!accountNumber.equals(other.accountNumber))
    {
      return false;
    }
    return true;
  }

  /**
   * @see us.fatehi.magnetictrack.TrackData#exceedsMaximumLength()
   */
  @Override
  public boolean exceedsMaximumLength()
  {
    return trimToEmpty(getRawTrackData()).length() > 19;
  }

  /**
   * Gets the primary account number of the bank card.
   * 
   * @return Primary account number.
   */
  public String getAccountNumber()
  {
    return accountNumber;
  }

  /**
   * Gets the the card brand.
   * 
   * @return Card brand.
   */
  public CardBrand getCardBrand()
  {
    return cardBrand;
  }

  /**
   * The first six digits of the PAN are taken from the IIN, or Issuer
   * Identification Number, belonging to the issuing bank (IINs were
   * previously known as BIN — Bank Identification Numbers — so you may
   * see references to that terminology in some documents). These six
   * digits are subject to an international standard, ISO/IEC 7812, and
   * can be used to determine the type of card from the number.
   *
   * @return IIN.s
   */
  public String getIssuerIdentificationNumber()
  {
    return left(accountNumber, 6);
  }

  /**
   * The first digit of a credit card number is the Major Industry
   * Identifier (MII) (see ISO/IEC 7812), which represents the category
   * of entity which issued the card.
   * 
   * @return MII.
   */
  public MajorIndustryIdentifier getMajorIndustryIdentifier()
  {
    return majorIndustryIdentifier;
  }

  /**
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result
             + (accountNumber == null? 0: accountNumber.hashCode());
    return result;
  }

  /**
   * Checks whether the primary account number for the card is
   * available.
   * 
   * @return True if the primary account number for the card is
   *         available.
   */
  public boolean hasPrimaryAccountNumber()
  {
    return !isBlank(accountNumber);
  }

  /**
   * Checks whether the primary account number passes the Luhn check.
   * 
   * @return True if the primary account number passes the Luhn check.
   */
  public boolean isPassesLuhnCheck()
  {
    return passesLuhnCheck;
  }

  /**
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString()
  {
    return accountNumber;
  }

  private boolean passesLuhnCheck()
  {

    final int length = accountNumber.length();
    int sum = 0;
    boolean alternate = false;
    for (int i = length - 1; i >= 0; i--)
    {
      int digit = Character.digit(accountNumber.charAt(i), 10);
      if (alternate)
      {
        digit = digit * 2;
        digit = digit > 9? digit - 9: digit;
      }
      sum = sum + digit;
      alternate = !alternate;
    }
    final boolean passesLuhnCheck = sum % 10 == 0;
    return passesLuhnCheck;
  }

}
