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

import org.threeten.bp.format.DateTimeFormatter;

/**
 * Represents a bank card, and contains information about the card
 * number, cardholder's name, expiration date, and service code.
 */
public class BankCard
implements Serializable
{

  private static final long serialVersionUID = 6253084852668206154L;

  protected static final DateTimeFormatter formatter = DateTimeFormatter
      .ofPattern("MMMM yyyy");

  private final PrimaryAccountNumber pan;
  private final Name name;
  private final ExpirationDate expirationDate;
  private final ServiceCode serviceCode;

  /**
   * No bank card.
   */
  public BankCard()
  {
    this(null);
  }

  /**
   * Construct a bank card from the constituent parts.
   *
   * @param pan
   *        Primary account number
   */
  public BankCard(final PrimaryAccountNumber pan)
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
  public BankCard(final PrimaryAccountNumber pan,
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
  public BankCard(final PrimaryAccountNumber pan,
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
  public BankCard(final PrimaryAccountNumber pan,
                  final ExpirationDate expirationDate,
                  final Name name,
                  final ServiceCode serviceCode)
  {
    if (pan != null)
    {
      this.pan = pan;
    }
    else
    {
      this.pan = new PrimaryAccountNumber();
    }

    if (name != null)
    {
      this.name = name;
    }
    else
    {
      this.name = new Name();
    }

    if (expirationDate != null)
    {
      this.expirationDate = expirationDate;
    }
    else
    {
      this.expirationDate = new ExpirationDate();
    }

    if (serviceCode != null)
    {
      this.serviceCode = serviceCode;
    }
    else
    {
      this.serviceCode = new ServiceCode();
    }
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
    if (!(obj instanceof BankCard))
    {
      return false;
    }
    final BankCard other = (BankCard) obj;
    if (expirationDate == null)
    {
      if (other.expirationDate != null)
      {
        return false;
      }
    }
    else if (!expirationDate.equals(other.expirationDate))
    {
      return false;
    }
    if (name == null)
    {
      if (other.name != null)
      {
        return false;
      }
    }
    else if (!name.equals(other.name))
    {
      return false;
    }
    if (pan == null)
    {
      if (other.pan != null)
      {
        return false;
      }
    }
    else if (!pan.equals(other.pan))
    {
      return false;
    }
    if (serviceCode == null)
    {
      if (other.serviceCode != null)
      {
        return false;
      }
    }
    else if (!serviceCode.equals(other.serviceCode))
    {
      return false;
    }
    return true;
  }

  /**
   * Gets the card expiration date.
   *
   * @return Card expiration date.
   */
  public ExpirationDate getExpirationDate()
  {
    return expirationDate;
  }

  /**
   * Gets the cardholder's name.
   *
   * @return Cardholder's name.
   */
  public Name getName()
  {
    return name;
  }

  /**
   * Gets the primary account number for the card.
   *
   * @return Primary account number.
   */
  public PrimaryAccountNumber getPrimaryAccountNumber()
  {
    return pan;
  }

  /**
   * Gets the service code.
   *
   * @return Service code.
   */
  public ServiceCode getServiceCode()
  {
    return serviceCode;
  }

  /**
   * Checks whether the card expiration date is available.
   *
   * @return True if the card expiration date is available.
   */
  public boolean hasExpirationDate()
  {
    return expirationDate != null && expirationDate.hasExpirationDate();
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
        + (expirationDate == null? 0: expirationDate.hashCode());
    result = prime * result + (name == null? 0: name.hashCode());
    result = prime * result + (pan == null? 0: pan.hashCode());
    result = prime * result + (serviceCode == null? 0: serviceCode.hashCode());
    return result;
  }

  /**
   * Checks whether the cardholder's name is available.
   *
   * @return True if the cardholder's name is available.
   */
  public boolean hasName()
  {
    return name != null && name.hasName();
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
    return pan != null && pan.hasPrimaryAccountNumber();
  }

  /**
   * Checks whether the card service code is available.
   *
   * @return True if the card service code is available.
   */
  public boolean hasServiceCode()
  {
    return serviceCode != null && serviceCode.hasServiceCode();
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
      buffer.append("  Primary Account Number: ");
      buffer.append(pan).append(NEWLINE);
      buffer.append("    MII: ");
      buffer.append(pan.getMajorIndustryIdentifier()).append(NEWLINE);
      buffer.append("    IIN: ");
      buffer.append(pan.getIssuerIdentificationNumber()).append(NEWLINE);
      buffer.append("    Card Brand: ");
      buffer.append(pan.getCardBrand()).append(NEWLINE);
      buffer.append("    Passes Luhn Check: ");
      buffer.append(pan.isPassesLuhnCheck()).append(NEWLINE);
    }
    if (hasExpirationDate())
    {
      buffer.append("  Expiration Date: ");
      buffer.append(formatter.format(expirationDate.getExpirationDate()))
      .append(NEWLINE);
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
