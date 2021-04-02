/*
 *
 * Magnetic Track Parser
 * https://github.com/sualeh/magnetictrackparser
 * Copyright (c) 2014-2021, Sualeh Fatehi.
 *
 */
package us.fatehi.magnetictrack;

import us.fatehi.creditcardnumber.AccountNumber;
import us.fatehi.creditcardnumber.BankCard;
import us.fatehi.creditcardnumber.ExpirationDate;
import us.fatehi.creditcardnumber.Name;
import us.fatehi.creditcardnumber.ServiceCode;

/**
 * Parser and representation for all 3 bank card magnetic track information. Has a method to
 * generate bank card information.
 */
public class BankCardMagneticTrack extends BaseTrackData {

  private static final long serialVersionUID = -8703108091852410189L;

  /**
   * Parses magnetic track data into a BankCardMagneticTrack object.
   *
   * @param rawTrackData Raw track data as a string. Can include newlines, and all 3 tracks.
   * @return A BankCardMagneticTrack instance, corresponding to the parsed data.
   */
  public static BankCardMagneticTrack from(final String rawTrackData) {
    final Track1FormatB track1 = Track1FormatB.from(rawTrackData);
    final Track2 track2 = Track2.from(rawTrackData);
    final Track3 track3 = Track3.from(rawTrackData);

    return new BankCardMagneticTrack(rawTrackData, track1, track2, track3);
  }

  private final Track1FormatB track1;
  private final Track2 track2;
  private final Track3 track3;

  private BankCardMagneticTrack(
      final String rawTrackData,
      final Track1FormatB track1,
      final Track2 track2,
      final Track3 track3) {
    super(rawTrackData, "");
    this.track1 = track1;
    this.track2 = track2;
    this.track3 = track3;
  }

  /** {@inheritDoc} */
  @Override
  public boolean exceedsMaximumLength() {
    return track1.exceedsMaximumLength()
        || track2.exceedsMaximumLength()
        || track3.exceedsMaximumLength();
  }

  /**
   * Gets track 1 representation.
   *
   * @return Track 1 representation.
   */
  public Track1FormatB getTrack1() {
    return track1;
  }

  /**
   * Gets track 2 representation.
   *
   * @return Track 2 representation.
   */
  public Track2 getTrack2() {
    return track2;
  }

  /**
   * Gets track 3 representation.
   *
   * @return Track 3 representation.
   */
  public Track3 getTrack3() {
    return track3;
  }

  /**
   * Constructs and returns bank card information, if all the track data is consistent. That is, if
   * any bank card information is repeated in track 1 and track 2, it should be the same data.
   *
   * @return Bank card information.
   */
  public BankCard toBankCard() {
    final AccountNumber pan;
    if (track1.hasAccountNumber()) {
      pan = track1.getAccountNumber();
    } else {
      pan = track2.getAccountNumber();
    }

    final Name name;
    if (track1.hasName()) {
      name = track1.getName();
    } else {
      name = new Name();
    }

    final ExpirationDate expirationDate;
    if (track1.hasExpirationDate()) {
      expirationDate = track1.getExpirationDate();
    } else {
      expirationDate = track2.getExpirationDate();
    }

    final ServiceCode serviceCode;
    if (track1.hasServiceCode()) {
      serviceCode = track1.getServiceCode();
    } else {
      serviceCode = track2.getServiceCode();
    }

    final BankCard cardInfo = new BankCard(pan, expirationDate, name, serviceCode);
    return cardInfo;
  }

  /** @see java.lang.Object#toString() */
  @Override
  public String toString() {
    final String NEWLINE = System.getProperty("line.separator");
    final StringBuilder buffer = new StringBuilder();

    buffer.append("TRACK 1: ");
    if (track1.hasRawData()) {
      buffer.append(track1.getRawData()).append(NEWLINE);
      if (track1.hasAccountNumber()) {
        final AccountNumber pan = track1.getAccountNumber();
        buffer.append("  Primary Account Number: ").append(pan).append(NEWLINE);
      } else {
        buffer.append("  No Primary Account Number").append(NEWLINE);
      }
      if (track1.hasExpirationDate()) {
        buffer.append("  Expiration Date: ");
        buffer.append(track2.getExpirationDate()).append(NEWLINE);
      } else {
        buffer.append("  No Expiration Date").append(NEWLINE);
      }
      if (track1.hasName()) {
        buffer.append("  Name: ");
        buffer.append(track1.getName()).append(NEWLINE);
      } else {
        buffer.append("  No Name").append(NEWLINE);
      }
      if (track1.hasServiceCode()) {
        final ServiceCode serviceCode = track1.getServiceCode();
        buffer.append("  Service Code: ").append(serviceCode).append(NEWLINE);
      } else {
        buffer.append("  No Service Code");
      }
      if (track1.hasDiscretionaryData()) {
        buffer.append("  Discretionary Data: ");
        buffer.append(track1.getDiscretionaryData()).append(NEWLINE);
      } else {
        buffer.append("  No Discretionary Data");
      }
    } else {
      buffer.append(" Not Available.").append(NEWLINE);
    }

    buffer.append("TRACK 2: ");
    if (track2.hasRawData()) {
      buffer.append(track2.getRawData()).append(NEWLINE);
      if (track2.hasAccountNumber()) {
        final AccountNumber pan = track2.getAccountNumber();
        buffer.append("  Primary Account Number: ").append(pan).append(NEWLINE);
      } else {
        buffer.append("  No Primary Account Number").append(NEWLINE);
      }
      if (track2.hasExpirationDate()) {
        buffer.append("  Expiration Date: ");
        buffer.append(track2.getExpirationDate()).append(NEWLINE);
      } else {
        buffer.append("No Expiration Date").append(NEWLINE);
      }
      if (track2.hasServiceCode()) {
        final ServiceCode serviceCode = track2.getServiceCode();
        buffer.append("  Service Code: ").append(serviceCode).append(NEWLINE);
      } else {
        buffer.append("  No Service Code");
      }
      if (track2.hasDiscretionaryData()) {
        buffer.append("  Discretionary Data: ");
        buffer.append(track2.getDiscretionaryData()).append(NEWLINE);
      } else {
        buffer.append("  No Discretionary Data");
      }
    } else {
      buffer.append(" Not Available.").append(NEWLINE);
    }

    buffer.append("TRACK 3: ");
    if (track3.hasRawData()) {
      buffer.append(track3.getRawData()).append(NEWLINE);
      if (track3.hasDiscretionaryData()) {
        buffer.append("  Discretionary Data: ");
        buffer.append(track3.getDiscretionaryData()).append(NEWLINE);
      } else {
        buffer.append("  No Discretionary Data");
      }
    } else {
      buffer.append(" Not Available.").append(NEWLINE);
    }

    final BankCard bankCard = toBankCard();
    buffer.append(NEWLINE).append(bankCard).append(NEWLINE);

    return buffer.toString();
  }
}
