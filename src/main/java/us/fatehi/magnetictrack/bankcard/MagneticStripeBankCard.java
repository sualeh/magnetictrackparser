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


import java.io.Serializable;
import java.util.Date;

import org.threeten.bp.format.DateTimeFormatter;

import us.fatehi.creditcardnumber.AccountNumberInfo;
import us.fatehi.creditcardnumber.BankCard;
import us.fatehi.creditcardnumber.ExpirationDate;
import us.fatehi.creditcardnumber.Name;
import us.fatehi.creditcardnumber.PrimaryAccountNumber;

/**
 * Represents a bank card, and contains information about the card
 * number, cardholder's name, expiration date, and service code.
 */
public class MagneticStripeBankCard
  implements Serializable
{

  private static final long serialVersionUID = 6253084852668206154L;

  protected static final DateTimeFormatter formatter = DateTimeFormatter
    .ofPattern("MMMM yyyy");

  private final BankCard bankCard;
  private final ServiceCode serviceCode;

  /**
   * No bank card.
   */
  public MagneticStripeBankCard()
  {
    this(null);
  }

  /**
   * Construct a bank card from the constituent parts.
   *
   * @param pan
   *        Primary account number
   */
  public MagneticStripeBankCard(final PrimaryAccountNumber pan)
  {
    this(pan, null);
  }

  /**
   * Construct a bank card from the constituent parts.
   *
   * @param pan
   *        Primary account number
   * @param expirationDate
   *        Card expiration date
   */
  public MagneticStripeBankCard(final PrimaryAccountNumber pan,
                                final ExpirationDate expirationDate)
  {
    this(pan, expirationDate, null);
  }

  /**
   * Construct a bank card from the constituent parts.
   *
   * @param pan
   *        Primary account number
   * @param expirationDate
   *        Card expiration date
   * @param name
   *        Cardholder name
   */
  public MagneticStripeBankCard(final PrimaryAccountNumber pan,
                                final ExpirationDate expirationDate,
                                final Name name)
  {
    this(pan, expirationDate, name, null);
  }

  /**
   * Construct a bank card from the constituent parts.
   *
   * @param pan
   *        Primary account number
   * @param expirationDate
   *        Card expiration date
   * @param name
   *        Cardholder name
   * @param serviceCode
   *        Service code
   */
  public MagneticStripeBankCard(final PrimaryAccountNumber pan,
                                final ExpirationDate expirationDate,
                                final Name name,
                                final ServiceCode serviceCode)
  {
    bankCard = new BankCard(pan, expirationDate, name);

    if (serviceCode != null)
    {
      this.serviceCode = serviceCode;
    }
    else
    {
      this.serviceCode = new ServiceCode();
    }
  }

  public String getAccountNumber()
  {
    return bankCard.getAccountNumber();
  }

  public String getCardHolderName()
  {
    return bankCard.getCardHolderName();
  }

  public ExpirationDate getExpirationDate()
  {
    return bankCard.getExpirationDate();
  }

  public Date getExpirationDateAsDate()
  {
    return bankCard.getExpirationDateAsDate();
  }

  public Name getName()
  {
    return bankCard.getName();
  }

  public PrimaryAccountNumber getPrimaryAccountNumber()
  {
    return bankCard.getPrimaryAccountNumber();
  }

  public ServiceCode getServiceCode()
  {
    return serviceCode;
  }

  public boolean hasExpirationDate()
  {
    return bankCard.hasExpirationDate();
  }

  public boolean hasName()
  {
    return bankCard.hasName();
  }

  public boolean hasPrimaryAccountNumber()
  {
    return bankCard.hasPrimaryAccountNumber();
  }

  public boolean hasServiceCode()
  {
    return serviceCode != null && serviceCode.hasServiceCode();
  }

  public boolean isExpired()
  {
    return bankCard.isExpired();
  }

  /**
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString()
  {
    final String NEWLINE = System.getProperty("line.separator");
    final StringBuilder buffer = new StringBuilder();

    buffer.append("Bank Card Information: ").append(NEWLINE);
    if (hasPrimaryAccountNumber())
    {
      final PrimaryAccountNumber pan = getPrimaryAccountNumber();
      buffer.append("  Primary Account Number: ");
      buffer.append(pan).append(NEWLINE);
      buffer.append("  Primary Account Number (Secure): ");
      buffer.append(new AccountNumberInfo(pan)).append(NEWLINE);
      buffer.append("    Major Industry Identifier: ");
      buffer.append(pan.getMajorIndustryIdentifier()).append(NEWLINE);
      buffer.append("    Issuer Identification Number: ");
      buffer.append(pan.getIssuerIdentificationNumber()).append(NEWLINE);
      buffer.append("    Card Brand: ");
      buffer.append(pan.getCardBrand()).append(NEWLINE);
      buffer.append("    Last Four Digits: ");
      buffer.append(pan.getLastFourDigits()).append(NEWLINE);
      buffer.append("    Passes Luhn Check? ");
      buffer.append(pan.passesLuhnCheck()? "Yes": "No").append(NEWLINE);
      buffer.append("    Is Primary Account Number Valid? ");
      buffer.append(pan.passesLuhnCheck()? "Yes": "No").append(NEWLINE);
    }
    if (hasExpirationDate())
    {
      buffer.append("  Expiration Date: ");
      buffer.append(formatter.format(getExpirationDate().getExpirationDate()))
        .append(NEWLINE);
      buffer.append("    Is Expired: ");
      buffer.append(isExpired()? "Yes": "No").append(NEWLINE);
    }
    if (hasName())
    {
      buffer.append("  Name: ");
      buffer.append(getName()).append(NEWLINE);
    }
    if (hasServiceCode())
    {
      final ServiceCode serviceCode = getServiceCode();
      buffer.append("  Service Code: ");
      buffer.append(NEWLINE);
      buffer.append("    ");
      buffer.append(serviceCode.getServiceCode1()).append(NEWLINE);
      buffer.append("    ");
      buffer.append(serviceCode.getServiceCode2()).append(NEWLINE);
      buffer.append("    ");
      buffer.append(serviceCode.getServiceCode3()).append(NEWLINE);
    }

    return buffer.toString();
  }
}
