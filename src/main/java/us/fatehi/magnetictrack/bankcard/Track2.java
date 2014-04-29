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

import org.threeten.bp.YearMonth;

/**
 * From <a
 * href="https://en.wikipedia.org/wiki/ISO/IEC_7813#Magnetic_tracks"
 * >Wikipedia - ISO/IEC 7813</a><br/>
 * The Track 2 structure is specified as:
 * <ol>
 * <li>STX : Start sentinel ";"</li>
 * <li>PAN : Primary Account Number, up to 19 digits, as defined in
 * ISO/IEC 7812-1</li>
 * <li>FS : Separator "="</li>
 * <li>ED : Expiration date, YYMM or "=" if not present</li>
 * <li>SC : Service code, 3 digits or "=" if not present</li>
 * <li>DD : Discretionary data, balance of available digits</li>
 * <li>ETX : End sentinel "?"</li>
 * <li>LRC : Longitudinal redundancy check, calculated according to
 * ISO/IEC 7811-2</li>
 * </ol>
 * The maximum record length is 40 numeric digits. e.g.5095700000000
 *
 * @see <a
 *      href="https://en.wikipedia.org/wiki/ISO/IEC_7813#Magnetic_tracks">Wikipedia
 *      - ISO/IEC 7813</a>
 */
public class Track2
  extends BaseTrack
{

  private static final long serialVersionUID = 2209024303926876386L;

  private static final Pattern track2Pattern = Pattern
    .compile(".*[\\t\\n\\r ]?(;([0-9]{1,19})=([0-9]{4})([0-9]{3})(.*)\\?).*");

  private final PrimaryAccountNumber pan;
  private final YearMonth expirationDate;
  private final ServiceCode serviceCode;

  public Track2(final String track)
  {

    final Matcher matcher;
    final boolean matches;
    if (!isBlank(track))
    {
      matcher = track2Pattern.matcher(track);
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
      pan = new PrimaryAccountNumber(getGroup(matcher, 2));
      expirationDate = parseExpirationDate(matcher, 3);
      serviceCode = new ServiceCode(getGroup(matcher, 4));
      discretionaryData = getGroup(matcher, 5);
    }
    else
    {
      trackData = null;
      pan = null;
      expirationDate = null;
      serviceCode = null;
      discretionaryData = null;
    }
  }

  /**
   * @see us.fatehi.magnetictrack.bankcard.Track#exceedsMaximumLength()
   */
  @Override
  public boolean exceedsMaximumLength()
  {
    return hasTrackData() && trackData.length() > 40;
  }

  /**
   * @return the expirationDate
   */
  public YearMonth getExpirationDate()
  {
    return expirationDate;
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

  public boolean hasPrimaryAccountNumber()
  {
    return pan != null && pan.hasPrimaryAccountNumber();
  }

  public boolean hasServiceCode()
  {
    return serviceCode != null && serviceCode.hasServiceCode();
  }

}
