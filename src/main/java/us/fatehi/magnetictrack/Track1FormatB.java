/*
 *
 * Magnetic Track Parser
 * https://github.com/sualeh/magnetictrackparser
 * Copyright (c) 2014-2021, Sualeh Fatehi.
 *
 */
package us.fatehi.magnetictrack;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.trimToEmpty;
import static us.fatehi.creditcardnumber.AccountNumbers.completeAccountNumber;
import static us.fatehi.creditcardnumber.AccountNumbers.emptyAccountNumber;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import us.fatehi.creditcardnumber.AccountNumber;
import us.fatehi.creditcardnumber.ExpirationDate;
import us.fatehi.creditcardnumber.Name;
import us.fatehi.creditcardnumber.ServiceCode;

/**
 * Parses, and represents a card's track 1 data, in format "B". From <a
 * href="https://en.wikipedia.org/wiki/ISO/IEC_7813#Magnetic_tracks" >Wikipedia - ISO/IEC 7813</a>
 *
 * <p>The Track 1 structure is specified as:
 *
 * <ol>
 *   <li>STX: Start sentinel "%"
 *   <li>FC: Format code "B" (The format described here. Format "A" is reserved for proprietary
 *       use.)
 *   <li>PAN: Primary Account Number, up to 19 digits
 *   <li>FS: Separator "^"
 *   <li>NM: Name, 2 to 26 characters (including separators, where appropriate, between surname,
 *       first name etc.)
 *   <li>FS: Separator "^"
 *   <li>ED: Expiration data, 4 digits or "^"
 *   <li>SC: Service code, 3 digits or "^"
 *   <li>DD: Discretionary data, balance of characters
 *   <li>ETX: End sentinel "?"
 *   <li>LRC: Longitudinal redundancy check, calculated according to ISO/IEC 7811-2
 * </ol>
 *
 * The maximum record length is 79 alphanumeric characters.
 *
 * @see <a href= "https://en.wikipedia.org/wiki/ISO/IEC_7813#Magnetic_tracks">Wikipedia - ISO/IEC
 *     7813</a>
 */
public final class Track1FormatB extends BaseBankCardTrackData {

  private static final long serialVersionUID = 3020739300944280022L;

  private static final Pattern track1FormatBPattern =
      Pattern.compile(
          "(%?([A-Z])([0-9]{1,19})\\^([^\\^]{2,26})\\^([0-9]{4}|\\^)([0-9]{3}|\\^)?([^\\?]+)?\\??)[\t\n\r ]{0,2}.*");

  /**
   * Parses magnetic track 1 format B data into a Track1FormatB object.
   *
   * @param rawTrackData Raw track data as a string. Can include newlines, and other tracks as well.
   * @return A Track1FormatB instance, corresponding to the parsed data.
   */
  public static Track1FormatB from(final String rawTrackData) {
    final Matcher matcher = track1FormatBPattern.matcher(trimToEmpty(rawTrackData));

    final String rawTrack1Data;
    final AccountNumber pan;
    final ExpirationDate expirationDate;
    final Name name;
    final ServiceCode serviceCode;
    final String formatCode;
    final String discretionaryData;

    if (matcher.matches()) {
      rawTrack1Data = getGroup(matcher, 1);
      formatCode = getGroup(matcher, 2);
      pan = completeAccountNumber(getGroup(matcher, 3));
      name = new Name(getGroup(matcher, 4));
      expirationDate = new ExpirationDate(getGroup(matcher, 5));
      serviceCode = new ServiceCode(getGroup(matcher, 6));
      discretionaryData = getGroup(matcher, 7);
    } else {
      rawTrack1Data = null;
      formatCode = "";
      pan = emptyAccountNumber();
      name = new Name();
      expirationDate = new ExpirationDate();
      serviceCode = new ServiceCode();
      discretionaryData = "";
    }

    return new Track1FormatB(
        rawTrack1Data, pan, expirationDate, name, serviceCode, formatCode, discretionaryData);
  }

  private final Name name;
  private final String formatCode;

  private Track1FormatB(
      final String rawTrackData,
      final AccountNumber pan,
      final ExpirationDate expirationDate,
      final Name name,
      final ServiceCode serviceCode,
      final String formatCode,
      final String discretionaryData) {
    super(rawTrackData, pan, expirationDate, serviceCode, discretionaryData);
    this.formatCode = formatCode;
    this.name = name;
  }

  /** The regular expression prevents the maximum length from being exceeded. */
  @Override
  public boolean exceedsMaximumLength() {
    return false;
  }

  /**
   * Gets the track 1 format code, usually "B".
   *
   * @return Track 1 format code, usually "B"
   */
  public String getFormatCode() {
    return formatCode;
  }

  /**
   * Gets the cardholder's name.
   *
   * @return Cardholder's name
   */
  public Name getName() {
    return name;
  }

  /**
   * Checks whether the format code is available.
   *
   * @return True if the format code is available.
   */
  public boolean hasFormatCode() {
    return !isBlank(formatCode);
  }

  /**
   * Checks whether the cardholder's name is available.
   *
   * @return True if the cardholder's name is available.
   */
  public boolean hasName() {
    return name != null && name.hasName();
  }
}
