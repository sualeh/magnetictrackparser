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


import static org.apache.commons.lang3.StringUtils.trimToEmpty;

import org.threeten.bp.YearMonth;
import org.threeten.bp.format.DateTimeFormatter;

import us.fatehi.magnetictrack.BaseTrackData;

public class ExpirationDate
  extends BaseTrackData
{

  private static final DateTimeFormatter formatter = DateTimeFormatter
    .ofPattern("yyMM");

  private static final long serialVersionUID = 422773685360335298L;

  private final YearMonth expirationDate;

  public ExpirationDate()
  {
    this(null);
  }

  public ExpirationDate(final String rawExpirationDate)
  {
    super(rawExpirationDate);
    final String expirationDateString = non_digit
      .matcher(trimToEmpty(rawExpirationDate)).replaceAll("");
    YearMonth expirationDate;
    try
    {
      expirationDate = YearMonth.parse(expirationDateString, formatter);
    }
    catch (final Exception e)
    {
      expirationDate = null;
    }
    this.expirationDate = expirationDate;
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
    if (!(obj instanceof ExpirationDate))
    {
      return false;
    }
    final ExpirationDate other = (ExpirationDate) obj;
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
    return true;
  }

  @Override
  public boolean exceedsMaximumLength()
  {
    return trimToEmpty(getRawTrackData()).length() > 4;
  }

  /**
   * @return the expirationDate
   */
  public YearMonth getExpirationDate()
  {
    return expirationDate;
  }

  public boolean hasExpirationDate()
  {
    return expirationDate != null;
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
    return result;
  }

  /**
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString()
  {
    if (hasExpirationDate())
    {
      return expirationDate.toString();
    }
    else
    {
      return super.toString();
    }
  }

}
