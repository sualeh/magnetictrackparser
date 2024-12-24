/*
 *
 * Magnetic Track Parser
 * https://github.com/sualeh/magnetictrackparser
 * Copyright (c) 2014-2025, Sualeh Fatehi.
 *
 */
package us.fatehi.magnetictrack;

import static org.apache.commons.lang3.StringUtils.trimToEmpty;
import static us.fatehi.creditcardnumber.AccountNumbers.completeAccountNumber;
import static us.fatehi.creditcardnumber.AccountNumbers.emptyAccountNumber;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import us.fatehi.creditcardnumber.AccountNumber;
import us.fatehi.creditcardnumber.ExpirationDate;
import us.fatehi.creditcardnumber.ServiceCode;

/**
 * From <a href="https://en.wikipedia.org/wiki/ISO/IEC_7813#Magnetic_tracks" >Wikipedia - ISO/IEC
 * 7813</a>
 *
 * <p>The Track 2 structure is specified as:
 *
 * <ol>
 *   <li>STX : Start sentinel ";"
 *   <li>PAN : Primary Account Number, up to 19 digits, as defined in ISO/IEC 7812-1
 *   <li>FS : Separator "="
 *   <li>ED : Expiration date, YYMM or "=" if not present
 *   <li>SC : Service code, 3 digits or "=" if not present
 *   <li>DD : Discretionary data, balance of available digits
 *   <li>ETX : End sentinel "?"
 *   <li>LRC : Longitudinal redundancy check, calculated according to ISO/IEC 7811-2
 * </ol>
 *
 * The maximum record length is 40 numeric digits. e.g.5095700000000
 *
 * @see <a href= "https://en.wikipedia.org/wiki/ISO/IEC_7813#Magnetic_tracks">Wikipedia - ISO/IEC
 *     7813</a>
 */
public final class Track2 extends BaseBankCardTrackData {

  private static final long serialVersionUID = 2209024303926876386L;

  private static final Pattern track2Pattern =
      Pattern.compile(".*[\\t\\n\\r ]?(;([0-9]{1,19})=([0-9]{4})([0-9]{3})(.*)\\?).*");

  /**
   * Parses magnetic track 2 data into a Track2 object.
   *
   * @param rawTrackData Raw track data as a string. Can include newlines, and other tracks as well.
   * @return A Track2 instance, corresponding to the parsed data.
   */
  public static Track2 from(final String rawTrackData) {
    final Matcher matcher = track2Pattern.matcher(trimToEmpty(rawTrackData));

    final String rawTrack2Data;
    final AccountNumber pan;
    final ExpirationDate expirationDate;
    final ServiceCode serviceCode;
    final String discretionaryData;

    if (matcher.matches()) {
      rawTrack2Data = getGroup(matcher, 1);
      pan = completeAccountNumber(getGroup(matcher, 2));
      expirationDate = new ExpirationDate(getGroup(matcher, 3));
      serviceCode = new ServiceCode(getGroup(matcher, 4));
      discretionaryData = getGroup(matcher, 5);
    } else {
      rawTrack2Data = null;
      pan = emptyAccountNumber();
      expirationDate = new ExpirationDate();
      serviceCode = new ServiceCode();
      discretionaryData = "";
    }

    return new Track2(rawTrack2Data, pan, expirationDate, serviceCode, discretionaryData);
  }

  private Track2(
      final String rawTrackData,
      final AccountNumber pan,
      final ExpirationDate expirationDate,
      final ServiceCode serviceCode,
      final String discretionaryData) {
    super(rawTrackData, pan, expirationDate, serviceCode, discretionaryData);
  }

  /** The regular expression prevents the maximum length from being exceeded. */
  @Override
  public boolean exceedsMaximumLength() {
    return false;
  }
}
