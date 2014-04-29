package us.fatehi.magnetictrack.bankcard;


import static org.apache.commons.lang3.StringUtils.isBlank;

import java.text.ParseException;

import org.threeten.bp.YearMonth;
import org.threeten.bp.format.DateTimeFormatter;

public class MagneticTrack
  extends BaseTrack
{

  private static final long serialVersionUID = -8703108091852410189L;

  protected static final DateTimeFormatter formatter = DateTimeFormatter
    .ofPattern("MMMM yyyy");

  private final Track1FormatB track1;
  private final Track2 track2;
  private final Track3 track3;

  public MagneticTrack(final String track)
  {
    if (isBlank(track))
    {
      throw new IllegalArgumentException("No track data provided");
    }

    trackData = track;

    track1 = new Track1FormatB(track);
    track2 = new Track2(track);
    track3 = new Track3(track);
  }

  /**
   * @see us.fatehi.magnetictrack.bankcard.Track#exceedsMaximumLength()
   */
  @Override
  public boolean exceedsMaximumLength()
  {
    return track1.exceedsMaximumLength() || track2.exceedsMaximumLength()
           || track2.exceedsMaximumLength();
  }

  public CardInfo getCardInfo()
    throws ParseException
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
      pan = new PrimaryAccountNumber();
    }
    if (track1.hasPrimaryAccountNumber() && track2.hasPrimaryAccountNumber())
    {
      if (!track1.getPrimaryAccountNumber()
        .equals(track2.getPrimaryAccountNumber()))
      {
        throw new ParseException("Inconsistent primary account number between track 1 and track 2",
                                 0);
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

    final YearMonth expirationDate;
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
      expirationDate = null;
    }
    if (track1.hasExpirationDate() && track2.hasExpirationDate())
    {
      if (!track1.getExpirationDate().equals(track2.getExpirationDate()))
      {
        throw new ParseException("Inconsistent expiration date between track 1 and track 2",
                                 0);
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
        throw new ParseException("Inconsistent service between track 1 and track 2",
                                 0);
      }
    }

    final CardInfo cardInfo = new CardInfo(pan,
                                           name,
                                           expirationDate,
                                           serviceCode);
    return cardInfo;
  }

  /**
   * @return the track1
   */
  public Track1FormatB getTrack1()
  {
    return track1;
  }

  /**
   * @return the track2
   */
  public Track2 getTrack2()
  {
    return track2;
  }

  /**
   * @return the track3
   */
  public Track3 getTrack3()
  {
    return track3;
  }

  /**
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString()
  {
    final String NEWLINE = System.getProperty("line.separator");
    final StringBuilder buffer = new StringBuilder();
    if (track1.hasTrackData())
    {
      buffer.append("Track 1: ");
      buffer.append(track1.getTrackData()).append(NEWLINE);
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
        buffer.append(formatter.format(track2.getExpirationDate()))
          .append(NEWLINE);
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
      buffer.append("No Track 1 Data").append(NEWLINE);
    }

    if (track2.hasTrackData())
    {
      buffer.append("Track 2: ");
      buffer.append(track2.getTrackData()).append(NEWLINE);
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
        buffer.append(formatter.format(track2.getExpirationDate()))
          .append(NEWLINE);
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
      buffer.append("No Track 2 Data").append(NEWLINE);
    }

    if (track3.hasTrackData())
    {
      buffer.append("Track 3: ");
      buffer.append(track3.getTrackData()).append(NEWLINE);
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
      buffer.append("No Track 3 Data").append(NEWLINE);
    }

    return buffer.toString();
  }

}
