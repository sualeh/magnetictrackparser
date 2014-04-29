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


import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.regex.Matcher;

import org.threeten.bp.YearMonth;
import org.threeten.bp.format.DateTimeFormatter;

/**
 * @see <a
 *      href="https://en.wikipedia.org/wiki/ISO/IEC_7813#Magnetic_tracks">Wikipedia
 *      - ISO/IEC 7813</a>
 */
public abstract class BaseTrack
  implements Track
{

  private static final long serialVersionUID = 7821463290736676016L;

  private static final DateTimeFormatter formatter = DateTimeFormatter
    .ofPattern("yyMM");

  protected String discretionaryData;
  protected String trackData;

  /**
   * @see us.fatehi.magnetictrack.bankcard.Track#getDiscretionaryData()
   */
  @Override
  public String getDiscretionaryData()
  {
    return discretionaryData;
  }

  /**
   * @see us.fatehi.magnetictrack.bankcard.Track#getTrackData()
   */
  @Override
  public String getTrackData()
  {
    return trackData;
  }

  public boolean hasDiscretionaryData()
  {
    return !isBlank(discretionaryData);
  }

  public boolean hasTrackData()
  {
    return !isBlank(trackData);
  }

  /**
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString()
  {
    return trackData;
  }

  protected String getGroup(final Matcher matcher, final int group)
  {
    final int groupCount = matcher.groupCount();
    if (groupCount > group - 1)
    {
      return matcher.group(group);
    }
    else
    {
      return null;
    }
  }

  protected YearMonth parseExpirationDate(final Matcher matcher, final int field)
  {
    YearMonth expirationDate;
    try
    {
      expirationDate = YearMonth.parse(getGroup(matcher, field), formatter);
    }
    catch (final Exception e)
    {
      expirationDate = null;
    }
    return expirationDate;
  }

}
