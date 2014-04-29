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
package us.fatehi.magnetictrack.bankcard;


import java.io.Serializable;

import org.threeten.bp.YearMonth;
import org.threeten.bp.format.DateTimeFormatter;

public class CardInfo
  implements Serializable
{

  private static final long serialVersionUID = 6253084852668206154L;

  protected static final DateTimeFormatter formatter = DateTimeFormatter
    .ofPattern("MMMM yyyy");

  private final PrimaryAccountNumber pan;
  private final Name name;
  private final YearMonth expirationDate;
  private final ServiceCode serviceCode;

  public CardInfo(final PrimaryAccountNumber pan,
                  final Name name,
                  final YearMonth expirationDate,
                  final ServiceCode serviceCode)
  {
    this.pan = pan;
    this.name = name;
    this.expirationDate = expirationDate;
    this.serviceCode = serviceCode;
  }

  /**
   * @return the expirationDate
   */
  public YearMonth getExpirationDate()
  {
    return expirationDate;
  }

  /**
   * @return the name
   */
  public Name getName()
  {
    return name;
  }

  /**
   * @return the pan
   */
  public PrimaryAccountNumber getPrimaryAccountNumber()
  {
    return pan;
  }

  /**
   * @return the serviceCode
   */
  public ServiceCode getServiceCode()
  {
    return serviceCode;
  }

  public boolean hasExpirationDate()
  {
    return expirationDate != null;
  }

  public boolean hasName()
  {
    return name != null && name.hasName();
  }

  public boolean hasPrimaryAccountNumber()
  {
    return pan != null && pan.hasPrimaryAccountNumber();
  }

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

    buffer.append("Card Info: ").append(NEWLINE);
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
    }
    else
    {
      buffer.append("  No Primary Account Number").append(NEWLINE);
    }
    if (hasExpirationDate())
    {
      buffer.append("  Expiration Date: ");
      buffer.append(formatter.format(expirationDate)).append(NEWLINE);
    }
    else
    {
      buffer.append("  No Expiration Date").append(NEWLINE);
    }
    if (hasName())
    {
      buffer.append("  Name: ");
      buffer.append(getName()).append(NEWLINE);
    }
    else
    {
      buffer.append("  No Name").append(NEWLINE);
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
    else
    {
      buffer.append("  No Service Code");
    }

    return buffer.toString();
  }

}
