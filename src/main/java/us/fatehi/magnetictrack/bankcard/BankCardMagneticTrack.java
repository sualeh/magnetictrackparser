package us.fatehi.magnetictrack.bankcard;


import org.threeten.bp.format.DateTimeFormatter;

import us.fatehi.creditcardnumber.AccountNumber;
import us.fatehi.creditcardnumber.BankCard;
import us.fatehi.creditcardnumber.ExpirationDate;
import us.fatehi.creditcardnumber.Name;
import us.fatehi.creditcardnumber.PrimaryAccountNumber;
import us.fatehi.creditcardnumber.ServiceCode;

/**
 * Parser and representation for all 3 bank card magnetic track
 * information. Has a method to generate bank card information.
 */
public class BankCardMagneticTrack
  extends BaseBankCardTrackData
{

  /**
   * Parses magnetic track data into a BankCardMagneticTrack object.
   *
   * @param rawTrackData
   *        Raw track data as a string. Can include newlines, and all 3
   *        tracks.
   * @return A BankCardMagneticTrack instance, corresponding to the
   *         parsed data.
   */
  public static BankCardMagneticTrack from(final String rawTrackData)
  {
    return new BankCardMagneticTrack(rawTrackData);
  }

  private static final long serialVersionUID = -8703108091852410189L;

  protected static final DateTimeFormatter formatter = DateTimeFormatter
    .ofPattern("MMMM yyyy");

  private final Track1FormatB track1;
  private final Track2 track2;
  private final Track3 track3;

  private BankCardMagneticTrack(final String rawTrackData)
  {
    super(rawTrackData, "");

    track1 = Track1FormatB.from(rawTrackData);
    track2 = Track2.from(rawTrackData);
    track3 = Track3.from(rawTrackData);
  }

  /**
   * @see us.fatehi.magnetictrack.TrackData#exceedsMaximumLength()
   */
  @Override
  public boolean exceedsMaximumLength()
  {
    return track1.exceedsMaximumLength() || track2.exceedsMaximumLength()
           || track2.exceedsMaximumLength();
  }

  /**
   * Gets track 1 representation.
   *
   * @return Track 1 representation.
   */
  public Track1FormatB getTrack1()
  {
    return track1;
  }

  /**
   * Gets track 2 representation.
   *
   * @return Track 2 representation.
   */
  public Track2 getTrack2()
  {
    return track2;
  }

  /**
   * Gets track 3 representation.
   *
   * @return Track 3 representation.
   */
  public Track3 getTrack3()
  {
    return track3;
  }

  /**
   * Constructs and returns bank card information, if all the track data
   * is consistent. That is, if any bank card information is repeated in
   * track 1 and track 2, it should be the same data.
   *
   * @return Bank card information.
   */
  public BankCard toBankCard()
  {
    final PrimaryAccountNumber pan;
    if (track1.hasPrimaryAccountNumber())
    {
      pan = track1.getPrimaryAccountNumber();
    }
    else if (track2.hasPrimaryAccountNumber())
    {
      pan = track2.getPrimaryAccountNumber();
    }
    else
    {
      pan = new AccountNumber();
    }
    if (track1.hasPrimaryAccountNumber() && track2.hasPrimaryAccountNumber())
    {
      if (!track1.getPrimaryAccountNumber()
        .equals(track2.getPrimaryAccountNumber()))
      {
        throw new IllegalStateException("Inconsistent primary account number between track 1 and track 2");
      }
    }

    final Name name;
    if (track1.hasName())
    {
      name = track1.getName();
    }
    else
    {
      name = new Name();
    }

    final ExpirationDate expirationDate;
    if (track1.hasExpirationDate())
    {
      expirationDate = track1.getExpirationDate();
    }
    else if (track2.hasExpirationDate())
    {
      expirationDate = track2.getExpirationDate();
    }
    else
    {
      expirationDate = new ExpirationDate();
    }
    if (track1.hasExpirationDate() && track2.hasExpirationDate())
    {
      if (!track1.getExpirationDate().equals(track2.getExpirationDate()))
      {
        throw new IllegalStateException("Inconsistent expiration date between track 1 and track 2");
      }
    }

    final ServiceCode serviceCode;
    if (track1.hasServiceCode())
    {
      serviceCode = track1.getServiceCode();
    }
    else if (track2.hasServiceCode())
    {
      serviceCode = track2.getServiceCode();
    }
    else
    {
      serviceCode = new ServiceCode();
    }
    if (track1.hasServiceCode() && track2.hasServiceCode())
    {
      if (!track1.getServiceCode().equals(track2.getServiceCode()))
      {
        throw new IllegalStateException("Inconsistent service between track 1 and track 2");
      }
    }

    final BankCard cardInfo = new BankCard(pan,
                                           expirationDate,
                                           name,
                                           serviceCode);
    return cardInfo;
  }

  /**
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString()
  {
    final String NEWLINE = System.getProperty("line.separator");
    final StringBuilder buffer = new StringBuilder();

    buffer.append("Track 1: ");
    if (track1.hasRawTrackData())
    {
      buffer.append(track1.getRawTrackData()).append(NEWLINE);
      if (track1.hasPrimaryAccountNumber())
      {
        final PrimaryAccountNumber pan = track1.getPrimaryAccountNumber();
        buffer.append("  Primary Account Number: ");
        buffer.append(pan).append(NEWLINE);
        buffer.append("    MII: ");
        buffer.append(pan.getMajorIndustryIdentifier()).append(NEWLINE);
        buffer.append("    IIN: ");
        buffer.append(pan.getIssuerIdentificationNumber()).append(NEWLINE);
        buffer.append("    Card Brand: ");
        buffer.append(pan.getCardBrand()).append(NEWLINE);
      }
      else
      {
        buffer.append("  No Primary Account Number").append(NEWLINE);
      }
      if (track1.hasExpirationDate())
      {
        buffer.append("  Expiration Date: ");
        buffer.append(formatter.format(track2.getExpirationDate()
          .getExpirationDate())).append(NEWLINE);
      }
      else
      {
        buffer.append("  No Expiration Date").append(NEWLINE);
      }
      if (track1.hasName())
      {
        buffer.append("  Name: ");
        buffer.append(track1.getName()).append(NEWLINE);
      }
      else
      {
        buffer.append("  No Name").append(NEWLINE);
      }
      if (track1.hasServiceCode())
      {
        final ServiceCode serviceCode = track1.getServiceCode();
        buffer.append("  Service Code: ");
        buffer.append(NEWLINE);
        buffer.append("    ");
        buffer.append(serviceCode.getServiceCode1()).append(NEWLINE);
        buffer.append("    ");
        buffer.append(serviceCode.getServiceCode2()).append(NEWLINE);
        buffer.append("    ");
        buffer.append(serviceCode.getServiceCode3()).append(NEWLINE);
      }
      else
      {
        buffer.append("  No Service Code");
      }
      if (track1.hasDiscretionaryData())
      {
        buffer.append("  Discretionary Data: ");
        buffer.append(track1.getDiscretionaryData()).append(NEWLINE);
      }
      else
      {
        buffer.append("  No Discretionary Data");
      }
    }
    else
    {
      buffer.append(" Not Available.").append(NEWLINE);
    }

    buffer.append("Track 2: ");
    if (track2.hasRawTrackData())
    {
      buffer.append(track2.getRawTrackData()).append(NEWLINE);
      if (track2.hasPrimaryAccountNumber())
      {
        final PrimaryAccountNumber pan = track2.getPrimaryAccountNumber();
        buffer.append("  Primary Account Number: ");
        buffer.append(pan).append(NEWLINE);
        buffer.append("    MII: ");
        buffer.append(pan.getMajorIndustryIdentifier()).append(NEWLINE);
        buffer.append("    IIN: ");
        buffer.append(pan.getIssuerIdentificationNumber()).append(NEWLINE);
        buffer.append("    Card Brand: ");
        buffer.append(pan.getCardBrand()).append(NEWLINE);
      }
      else
      {
        buffer.append("  No Primary Account Number").append(NEWLINE);
      }
      if (track2.hasExpirationDate())
      {
        buffer.append("  Expiration Date: ");
        buffer.append(formatter.format(track2.getExpirationDate()
          .getExpirationDate())).append(NEWLINE);
      }
      else
      {
        buffer.append("  No Expiration Date").append(NEWLINE);
      }
      if (track2.hasServiceCode())
      {
        final ServiceCode serviceCode = track2.getServiceCode();
        buffer.append("  Service Code: ");
        buffer.append(NEWLINE);
        buffer.append("    ");
        buffer.append(serviceCode.getServiceCode1()).append(NEWLINE);
        buffer.append("    ");
        buffer.append(serviceCode.getServiceCode2()).append(NEWLINE);
        buffer.append("    ");
        buffer.append(serviceCode.getServiceCode3()).append(NEWLINE);
      }
      else
      {
        buffer.append("  No Service Code");
      }
      if (track2.hasDiscretionaryData())
      {
        buffer.append("  Discretionary Data: ");
        buffer.append(track2.getDiscretionaryData()).append(NEWLINE);
      }
      else
      {
        buffer.append("  No Discretionary Data");
      }
    }
    else
    {
      buffer.append(" Not Available.").append(NEWLINE);
    }

    buffer.append("Track 3: ");
    if (track3.hasRawTrackData())
    {
      buffer.append(track3.getRawTrackData()).append(NEWLINE);
      if (track3.hasDiscretionaryData())
      {
        buffer.append("  Discretionary Data: ");
        buffer.append(track3.getDiscretionaryData()).append(NEWLINE);
      }
      else
      {
        buffer.append("  No Discretionary Data");
      }
    }
    else
    {
      buffer.append(" Not Available.").append(NEWLINE);
    }

    final BankCard bankCard = toBankCard();
    buffer.append(NEWLINE).append(bankCard).append(NEWLINE);

    return buffer.toString();
  }

}
