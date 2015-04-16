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
import static org.apache.commons.lang3.StringUtils.trimToEmpty;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import us.fatehi.creditcardnumber.AccountNumber;
import us.fatehi.creditcardnumber.ExpirationDate;
import us.fatehi.creditcardnumber.Name;
import us.fatehi.creditcardnumber.PrimaryAccountNumber;
import us.fatehi.creditcardnumber.ServiceCode;

/**
 * Parses, and represents a card's track 1 data, in format "B". From <a
 * href="https://en.wikipedia.org/wiki/ISO/IEC_7813#Magnetic_tracks"
 * >Wikipedia - ISO/IEC 7813</a><br/>
 * The Track 1 structure is specified as:
 * <ol>
 * <li>STX: Start sentinel "%"</li>
 * <li>FC: Format code "B" (The format described here. Format "A" is
 * reserved for proprietary use.)</li>
 * <li>PAN: Primary Account Number, up to 19 digits</li>
 * <li>FS: Separator "^"</li>
 * <li>NM: Name, 2 to 26 characters (including separators, where
 * appropriate, between surname, first name etc.)</li>
 * <li>FS: Separator "^"</li>
 * <li>ED: Expiration data, 4 digits or "^"</li>
 * <li>SC: Service code, 3 digits or "^"</li>
 * <li>DD: Discretionary data, balance of characters</li>
 * <li>ETX: End sentinel "?"</li>
 * <li>LRC: Longitudinal redundancy check, calculated according to
 * ISO/IEC 7811-2</li>
 * </ol>
 * The maximum record length is 79 alphanumeric characters.
 *
 * @see <a
 *      href="https://en.wikipedia.org/wiki/ISO/IEC_7813#Magnetic_tracks">Wikipedia
 *      - ISO/IEC 7813</a>
 */
public class Track1FormatB
  extends BaseBankCardTrackData
{

  private static final long serialVersionUID = 3020739300944280022L;

  private static final Pattern track1FormatBPattern = Pattern
    .compile("(%?([A-Z])([0-9]{1,19})\\^([^\\^]{2,26})\\^([0-9]{4}|\\^)([0-9]{3}|\\^)?([^\\?]+)?\\??)[\t\n\r ]{0,2}.*");

  /**
   * Parses magnetic track 1 format B data into a Track1FormatB object.
   *
   * @param rawTrackData
   *        Raw track data as a string. Can include newlines, and other
   *        tracks as well.
   * @return A Track1FormatB instance, corresponding to the parsed data.
   */
  public static Track1FormatB from(final String rawTrackData)
  {
    final Matcher matcher = track1FormatBPattern
      .matcher(trimToEmpty(rawTrackData));

    final String rawTrack1Data;
    final PrimaryAccountNumber pan;
    final ExpirationDate expirationDate;
    final Name name;
    final ServiceCode serviceCode;
    final String formatCode;
    final String discretionaryData;

    if (matcher.matches())
    {
      rawTrack1Data = getGroup(matcher, 1);
      formatCode = getGroup(matcher, 2);
      pan = new AccountNumber(getGroup(matcher, 3));
      name = new Name(getGroup(matcher, 4));
      expirationDate = new ExpirationDate(getGroup(matcher, 5));
      serviceCode = new ServiceCode(getGroup(matcher, 6));
      discretionaryData = getGroup(matcher, 7);
    }
    else
    {
      rawTrack1Data = "";
      formatCode = "";
      pan = new AccountNumber();
      name = new Name();
      expirationDate = new ExpirationDate();
      serviceCode = new ServiceCode();
      discretionaryData = "";
    }

    return new Track1FormatB(rawTrack1Data,
                             pan,
                             expirationDate,
                             name,
                             serviceCode,
                             formatCode,
                             discretionaryData);
  }

  private final Name name;
  private final String formatCode;

  private Track1FormatB(final String rawTrackData,
                        final PrimaryAccountNumber pan,
                        final ExpirationDate expirationDate,
                        final Name name,
                        final ServiceCode serviceCode,
                        final String formatCode,
                        final String discretionaryData)
  {
    super(rawTrackData, pan, expirationDate, serviceCode, discretionaryData);
    this.formatCode = formatCode;
    this.name = name;
  }

  @Override
  public boolean exceedsMaximumLength()
  {
    return hasRawData() && getRawData().length() > 79;
  }

  /**
   * Gets the track 1 format code, usually "B".
   *
   * @return Track 1 format code, usually "B"
   */
  public String getFormatCode()
  {
    return formatCode;
  }

  /**
   * Gets the cardholder's name.
   *
   * @return Cardholder's name
   */
  public Name getName()
  {
    return name;
  }

  /**
   * Checks whether the format code is available.
   *
   * @return True if the format code is available.
   */
  public boolean hasFormatCode()
  {
    return !isBlank(formatCode);
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

}
