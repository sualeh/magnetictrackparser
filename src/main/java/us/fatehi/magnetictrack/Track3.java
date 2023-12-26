/*
 *
 * Magnetic Track Parser
 * https://github.com/sualeh/magnetictrackparser
 * Copyright (c) 2014-2024, Sualeh Fatehi.
 *
 */
package us.fatehi.magnetictrack;

import static org.apache.commons.lang3.StringUtils.trimToEmpty;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @see <a href= "https://en.wikipedia.org/wiki/ISO/IEC_7813#Magnetic_tracks">Wikipedia - ISO/IEC
 *     7813</a>
 */
public final class Track3 extends BaseTrackData {

  private static final long serialVersionUID = 1469806733607842924L;

  private static final Pattern track3Pattern = Pattern.compile(".*?[\t\n\r ]{0,2}(\\+(.*)\\?)");

  /**
   * Parses magnetic track 3 data into a Track3 object.
   *
   * @param rawTrackData Raw track data as a string. Can include newlines, and other tracks as well.
   * @return A Track3instance, corresponding to the parsed data.
   */
  public static Track3 from(final String rawTrackData) {
    final Matcher matcher = track3Pattern.matcher(trimToEmpty(rawTrackData));

    final String rawTrack3Data;
    final String discretionaryData;
    if (matcher.matches()) {
      rawTrack3Data = getGroup(matcher, 1);
      discretionaryData = getGroup(matcher, 2);
    } else {
      rawTrack3Data = null;
      discretionaryData = "";
    }
    return new Track3(rawTrack3Data, discretionaryData);
  }

  private Track3(final String rawTrack2Data, final String discretionaryData) {
    super(rawTrack2Data, discretionaryData);
  }

  /** The regular expression prevents the maximum length from being exceeded. */
  @Override
  public boolean exceedsMaximumLength() {
    return false;
  }
}
