/*
 *
 * Magnetic Track Parser
 * https://github.com/sualeh/magnetictrackparser
 * Copyright (c) 2014-2015, Sualeh Fatehi.
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

import java.io.Serializable;
import java.util.regex.Matcher;

import us.fatehi.creditcardnumber.RawData;

abstract class BaseTrackData
  implements RawData, Serializable
{

  private static final long serialVersionUID = 7821463290736676016L;

  protected static String getGroup(final Matcher matcher, final int group)
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

  private final String rawTrackData;
  private final String discretionaryData;

  BaseTrackData(final String rawTrackData, final String discretionaryData)
  {
    this.rawTrackData = rawTrackData;
    this.discretionaryData = discretionaryData == null? "": discretionaryData;
  }

  /**
   * Gets discretionary data on the track.
   *
   * @return Discretionary data.
   */
  public String getDiscretionaryData()
  {
    return discretionaryData;
  }

  @Override
  public String getRawData()
  {
    return rawTrackData;
  }

  /**
   * Whether discretionary data is present.
   *
   * @return True if discretionary data is available
   */
  public boolean hasDiscretionaryData()
  {
    return !isBlank(discretionaryData);
  }

  @Override
  public boolean hasRawData()
  {
    return !isBlank(rawTrackData);
  }

  @Override
  public String toString()
  {
    return rawTrackData;
  }

}
