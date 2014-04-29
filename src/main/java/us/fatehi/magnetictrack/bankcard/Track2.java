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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
  extends BaseBankCardTrackData
{

  private static final long serialVersionUID = 2209024303926876386L;

  private static final Pattern track2Pattern = Pattern
    .compile(".*[\\t\\n\\r ]?(;([0-9]{1,19})=([0-9]{4})([0-9]{3})(.*)\\?).*");

  public static Track2 from(final String rawTrackData)
  {
    final Matcher matcher = track2Pattern.matcher(trimToEmpty(rawTrackData));

    final String rawTrack2Data;
    final String discretionaryData;
    if (matcher.matches())
    {
      rawTrack2Data = getGroup(matcher, 1);
      discretionaryData = getGroup(matcher, 5);
    }
    else
    {
      rawTrack2Data = "";
      discretionaryData = "";
    }
    return new Track2(rawTrack2Data, discretionaryData, matcher);
  }

  private final PrimaryAccountNumber pan;
  private final ExpirationDate expirationDate;

  private final ServiceCode serviceCode;

  private Track2(final String rawTrack2Data,
                 final String discretionaryData,
                 final Matcher matcher)
  {
    super(rawTrack2Data, discretionaryData);

    if (matcher.matches())
    {
      pan = new PrimaryAccountNumber(getGroup(matcher, 2));
      expirationDate = new ExpirationDate(getGroup(matcher, 3));
      serviceCode = new ServiceCode(getGroup(matcher, 4));
    }
    else
    {
      pan = null;
      expirationDate = null;
      serviceCode = null;
    }
  }

  /**
   * @see us.fatehi.magnetictrack.TrackData#exceedsMaximumLength()
   */
  @Override
  public boolean exceedsMaximumLength()
  {
    return hasRawTrackData() && getRawTrackData().length() > 40;
  }

  /**
   * @return the expirationDate
   */
  public ExpirationDate getExpirationDate()
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
    return expirationDate != null && expirationDate.hasExpirationDate();
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
