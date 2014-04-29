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
import java.util.regex.Pattern;

/**
 * @see <a
 *      href="https://en.wikipedia.org/wiki/ISO/IEC_7813#Magnetic_tracks">Wikipedia
 *      - ISO/IEC 7813</a>
 */
public class Track3
  extends BaseTrack
{

  private static final long serialVersionUID = 1469806733607842924L;

  private static final Pattern track3Pattern = Pattern
    .compile(".*[\\t\\n\\r ]?(\\+(.*)\\?)");

  public Track3(final String track)
  {

    final Matcher matcher;
    final boolean matches;
    if (!isBlank(track))
    {
      matcher = track3Pattern.matcher(track);
      matches = matcher.matches();
    }
    else
    {
      matcher = null;
      matches = false;
    }

    if (matches)
    {
      trackData = getGroup(matcher, 1);
      discretionaryData = getGroup(matcher, 2);
    }
    else
    {
      trackData = null;
      discretionaryData = null;
    }
  }

  /**
   * @see us.fatehi.magnetictrack.bankcard.Track#exceedsMaximumLength()
   */
  @Override
  public boolean exceedsMaximumLength()
  {
    return false;
  }

}
