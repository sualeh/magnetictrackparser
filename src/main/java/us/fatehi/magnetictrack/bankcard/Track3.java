/*
 *
 * Magnetic Track Parser
 * https://github.com/sualeh/magnetictrackparser
 * Copyright (c) 2014-2016, Sualeh Fatehi.
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @see <a href=
 *      "https://en.wikipedia.org/wiki/ISO/IEC_7813#Magnetic_tracks">Wikipedia
 *      - ISO/IEC 7813</a>
 */
public class Track3
  extends BaseTrackData
{

  private static final long serialVersionUID = 1469806733607842924L;

  private static final Pattern track3Pattern = Pattern
    .compile(".*?[\t\n\r ]{0,2}(\\+(.*)\\?)");

  /**
   * Parses magnetic track 3 data into a Track3 object.
   *
   * @param rawTrackData
   *        Raw track data as a string. Can include newlines, and other
   *        tracks as well.
   * @return A Track3instance, corresponding to the parsed data.
   */
  public static Track3 from(final String rawTrackData)
  {
    final Matcher matcher = track3Pattern.matcher(trimToEmpty(rawTrackData));

    final String rawTrack3Data;
    final String discretionaryData;
    if (matcher.matches())
    {
      rawTrack3Data = getGroup(matcher, 1);
      discretionaryData = getGroup(matcher, 2);
    }
    else
    {
      rawTrack3Data = null;
      discretionaryData = "";
    }
    return new Track3(rawTrack3Data, discretionaryData);
  }

  private Track3(final String rawTrack2Data, final String discretionaryData)
  {
    super(rawTrack2Data, discretionaryData);
  }

  public boolean exceedsMaximumLength()
  {
    return false;
  }

}
