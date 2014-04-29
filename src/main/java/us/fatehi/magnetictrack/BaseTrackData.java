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
package us.fatehi.magnetictrack;


import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.regex.Pattern;

public abstract class BaseTrackData
  implements TrackData
{

  private static final long serialVersionUID = 7821463290736676016L;

  protected static final Pattern non_digit = Pattern.compile("[^0-9]");

  private final String rawTrackData;

  protected BaseTrackData(final String rawTrackData)
  {
    this.rawTrackData = rawTrackData;
  }

  /**
   * @see us.fatehi.magnetictrack.TrackData#getRawTrackData()
   */
  @Override
  public String getRawTrackData()
  {
    return rawTrackData;
  }

  /**
   *
   */
  @Override
  public boolean hasRawTrackData()
  {
    return !isBlank(rawTrackData);
  }

  /**
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString()
  {
    return rawTrackData;
  }

}
