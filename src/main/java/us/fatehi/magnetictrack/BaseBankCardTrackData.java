/*
 *
 * Magnetic Track Parser
 * https://github.com/sualeh/magnetictrackparser
 * Copyright (c) 2014-2026, Sualeh Fatehi.
 *
 */
package us.fatehi.magnetictrack;

import static us.fatehi.creditcardnumber.AccountNumbers.emptyAccountNumber;

import java.io.Serial;
import java.util.Objects;
import us.fatehi.creditcardnumber.AccountNumber;
import us.fatehi.creditcardnumber.ExpirationDate;
import us.fatehi.creditcardnumber.ServiceCode;

/**
 * @see <a href= "https://en.wikipedia.org/wiki/ISO/IEC_7813#Magnetic_tracks">Wikipedia - ISO/IEC
 *     7813</a>
 */
abstract class BaseBankCardTrackData extends BaseTrackData {

  @Serial private static final long serialVersionUID = 7821463290736676016L;

  private final AccountNumber pan;
  private final ExpirationDate expirationDate;
  private final ServiceCode serviceCode;

  BaseBankCardTrackData(
      final String rawTrackData,
      final AccountNumber pan,
      final ExpirationDate expirationDate,
      final ServiceCode serviceCode,
      final String discretionaryData) {
    super(rawTrackData, discretionaryData);

    if (pan == null) {
      this.pan = emptyAccountNumber();
    } else {
      this.pan = pan;
    }

    if (expirationDate == null) {
      this.expirationDate = new ExpirationDate();
    } else {
      this.expirationDate = expirationDate;
    }

    if (serviceCode == null) {
      this.serviceCode = new ServiceCode();
    } else {
      this.serviceCode = serviceCode;
    }
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof BaseBankCardTrackData)) {
      return false;
    }
    final BaseBankCardTrackData other = (BaseBankCardTrackData) obj;
    if (!Objects.equals(expirationDate, other.expirationDate)) {
      return false;
    }
    if (!Objects.equals(pan, other.pan)) {
      return false;
    }
    if (!Objects.equals(serviceCode, other.serviceCode)) {
      return false;
    }
    return true;
  }

  /**
   * Gets the primary account number for the card.
   *
   * @return Primary account number.
   */
  public AccountNumber getAccountNumber() {
    return pan;
  }

  /**
   * Gets the primary account number for the card.
   *
   * @return Primary account number.
   */
  public ExpirationDate getExpirationDate() {
    return expirationDate;
  }

  /**
   * Gets the card service code.
   *
   * @return Card service code.
   */
  public ServiceCode getServiceCode() {
    return serviceCode;
  }

  /**
   * Checks whether the primary account number for the card is available.
   *
   * @return True if the primary account number for the card is available.
   */
  public boolean hasAccountNumber() {
    return pan.hasAccountNumber();
  }

  /**
   * Checks whether the card expiration date is available.
   *
   * @return True if the card expiration date is available.
   */
  public boolean hasExpirationDate() {
    return expirationDate.hasExpirationDate();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (expirationDate == null ? 0 : expirationDate.hashCode());
    result = prime * result + (pan == null ? 0 : pan.hashCode());
    result = prime * result + (serviceCode == null ? 0 : serviceCode.hashCode());
    return result;
  }

  /**
   * Checks whether the card service code is available.
   *
   * @return True if the card service code is available.
   */
  public boolean hasServiceCode() {
    return serviceCode.hasServiceCode();
  }
}
