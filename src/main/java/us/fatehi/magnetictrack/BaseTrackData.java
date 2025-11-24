/*
 *
 * Magnetic Track Parser
 * https://github.com/sualeh/magnetictrackparser
 * Copyright (c) 2014-2026, Sualeh Fatehi.
 *
 */
package us.fatehi.magnetictrack;

import java.io.Serial;
import java.io.Serializable;
import java.util.regex.Matcher;
import us.fatehi.creditcardnumber.BaseRawData;
import us.fatehi.creditcardnumber.DisposableStringData;
import us.fatehi.creditcardnumber.RawData;

abstract class BaseTrackData extends BaseRawData implements RawData, Serializable {

  @Serial private static final long serialVersionUID = 7821463290736676016L;

  protected static String getGroup(final Matcher matcher, final int group) {
    if (matcher == null || !matcher.matches()) {
      return null;
    }
    if (group <= 0 || matcher.groupCount() <= group - 1) {
      return null;
    }
    return matcher.group(group);
  }

  private final DisposableStringData discretionaryData;

  BaseTrackData(final String rawTrackData, final String discretionaryData) {
    super(rawTrackData);
    this.discretionaryData = new DisposableStringData(discretionaryData);
  }

  /**
   * Disposes discretionary data from memory. Following recommendations from the <a href=
   * "http://docs.oracle.com/javase/6/docs/technotes/guides/security/crypto/CryptoSpec.html#PBEEx">Java
   * Cryptography Architecture (JCA) Reference Guide</a>
   */
  public void disposeDiscretionaryData() {
    discretionaryData.disposeData();
  }

  /**
   * Gets discretionary data on the track.
   *
   * @return Discretionary data.
   */
  public String getDiscretionaryData() {
    return discretionaryData.getData();
  }

  /**
   * Whether discretionary data is present.
   *
   * @return True if discretionary data is available
   */
  public boolean hasDiscretionaryData() {
    return discretionaryData.hasData();
  }

  @Override
  public String toString() {
    return getRawData();
  }
}
