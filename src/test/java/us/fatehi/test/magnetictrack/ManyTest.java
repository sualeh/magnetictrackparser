/*
 *
 * Magnetic Track Parser
 * https://github.com/sualeh/magnetictrackparser
 * Copyright (c) 2014-2023, Sualeh Fatehi.
 *
 */
package us.fatehi.test.magnetictrack;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.YearMonth;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import us.fatehi.creditcardnumber.BankCard;
import us.fatehi.creditcardnumber.CardBrand;
import us.fatehi.magnetictrack.BankCardMagneticTrack;

public class ManyTest {

  @Test
  public void track_1(final TestInfo testInfo) throws Exception {
    final BankCardMagneticTrack track_1 = BankCardMagneticTrack.from("");
    checkToString(track_1, testInfo, false);

    assertThat(track_1.exceedsMaximumLength(), is(false));
    assertThat(track_1.getTrack1(), is(not(nullValue())));
    assertThat(track_1.getTrack1().hasAccountNumber(), is(false));
    assertThat(track_1.getTrack2(), is(not(nullValue())));
    assertThat(track_1.getTrack2().hasAccountNumber(), is(false));
    assertThat(track_1.getTrack3(), is(not(nullValue())));
  }

  @Test
  public void track_2(final TestInfo testInfo) throws Exception {
    final BankCardMagneticTrack track_2 =
        BankCardMagneticTrack.from(
            "%B5350290149345177^FATEHI/SUALEH^16042010000000000000000000000000000567001000?;5350290149345177=16042010000056700100?");

    track_2.getTrack1().getAccountNumber().dispose();
    track_2.getTrack2().getAccountNumber().dispose();
    track_2.getTrack2().disposeDiscretionaryData();
    track_2.getTrack3().disposeDiscretionaryData();

    checkToString(track_2, testInfo, false);

    assertThat(track_2.exceedsMaximumLength(), is(false));
    assertThat(track_2.getTrack1(), is(not(nullValue())));
    assertThat(track_2.getTrack1().hasAccountNumber(), is(false));
    assertThat(track_2.getTrack2(), is(not(nullValue())));
    assertThat(track_2.getTrack2().hasAccountNumber(), is(false));
    assertThat(track_2.getTrack3(), is(not(nullValue())));
  }

  @Test
  public void trackA(final TestInfo testInfo) throws Exception {
    final BankCardMagneticTrack trackA =
        BankCardMagneticTrack.from(
            "%B5350290149345177^FATEHI/SUALEH^16042010000000000000000000000000000567001000?;5350290149345177=16042010000056700100?");
    checkToString(trackA, testInfo, false);

    checkTrackHealth(trackA);

    final BankCard cardInfo = trackA.toBankCard();
    assertThat(cardInfo.getAccountNumber().getAccountNumber(), is("5350290149345177"));
    assertThat(cardInfo.getAccountNumber().getCardBrand(), is(CardBrand.MasterCard));
    assertThat(cardInfo.getName().toString(), is("Sualeh Fatehi"));
    assertThat(cardInfo.getExpirationDate().getExpirationDate(), is(YearMonth.of(2016, 4)));
  }

  @Test
  public void trackB(final TestInfo testInfo) throws Exception {
    final BankCardMagneticTrack trackB =
        BankCardMagneticTrack.from(
            "%B4181887684889366^FATEHI/SUALEH^1605101097670000000000120000000?;4181887684889366=160510101200009767?");
    checkToString(trackB, testInfo, false);

    checkTrackHealth(trackB);

    final BankCard cardInfo = trackB.toBankCard();
    assertThat(cardInfo.getAccountNumber().getAccountNumber(), is("4181887684889366"));
    assertThat(cardInfo.getAccountNumber().getCardBrand(), is(CardBrand.Visa));
    assertThat(cardInfo.getName().toString(), is("Sualeh Fatehi"));
    assertThat(cardInfo.getExpirationDate().getExpirationDate(), is(YearMonth.of(2016, 5)));
  }

  @Test
  public void trackC(final TestInfo testInfo) throws Exception {
    final BankCardMagneticTrack trackC =
        BankCardMagneticTrack.from(
            "%B5391285197433215^FATEHI/SUALEH             ^1701101000001540000000154000000?;5391285197433215=17011010000015400000?");
    checkToString(trackC, testInfo, false);

    checkTrackHealth(trackC);

    final BankCard cardInfo = trackC.toBankCard();
    assertThat(cardInfo.getAccountNumber().getAccountNumber(), is("5391285197433215"));
    assertThat(cardInfo.getAccountNumber().getCardBrand(), is(CardBrand.MasterCard));
    assertThat(cardInfo.getName().toString(), is("Sualeh Fatehi"));
    assertThat(cardInfo.getExpirationDate().getExpirationDate(), is(YearMonth.of(2017, 1)));
  }

  @Test
  public void trackD(final TestInfo testInfo) throws Exception {
    final BankCardMagneticTrack trackD =
        BankCardMagneticTrack.from(
            "%B6011460477609366^FATEHI/SUALEH             ^15101011000606818102?;6011460477609366=15101011000606818102?");
    checkToString(trackD, testInfo, false);

    checkTrackHealth(trackD);

    final BankCard cardInfo = trackD.toBankCard();
    assertThat(cardInfo.getAccountNumber().getAccountNumber(), is("6011460477609366"));
    assertThat(cardInfo.getAccountNumber().getCardBrand(), is(CardBrand.Discover));
    assertThat(cardInfo.getName().toString(), is("Sualeh Fatehi"));
    assertThat(cardInfo.getExpirationDate().getExpirationDate(), is(YearMonth.of(2015, 10)));
  }

  @Test
  public void trackE(final TestInfo testInfo) throws Exception {
    final BankCardMagneticTrack trackE =
        BankCardMagneticTrack.from(
            "%B6135320294113573^FATEHI/SUALEH^491210100316000000?;6135320294113573=491210110000316?");
    checkToString(trackE, testInfo, false);

    checkTrackHealth(trackE);

    final BankCard cardInfo = trackE.toBankCard();
    assertThat(cardInfo.getAccountNumber().getAccountNumber(), is("6135320294113573"));
    assertThat(cardInfo.getAccountNumber().getCardBrand(), is(CardBrand.Unknown));
    assertThat(cardInfo.getName().toString(), is("Sualeh Fatehi"));
    assertThat(cardInfo.getExpirationDate().getExpirationDate(), is(YearMonth.of(2049, 12)));
  }

  @Test
  public void trackF(final TestInfo testInfo) throws Exception {
    final BankCardMagneticTrack trackF =
        BankCardMagneticTrack.from(
            "%B4181887684889366^FATEHI/SUALEH^1502101072560000000000019000000?;4181887684889366=150210100190007256?");
    checkToString(trackF, testInfo, false);

    checkTrackHealth(trackF);

    final BankCard cardInfo = trackF.toBankCard();
    assertThat(cardInfo.getAccountNumber().getAccountNumber(), is("4181887684889366"));
    assertThat(cardInfo.getAccountNumber().getCardBrand(), is(CardBrand.Visa));
    assertThat(cardInfo.getName().toString(), is("Sualeh Fatehi"));
    assertThat(cardInfo.getExpirationDate().getExpirationDate(), is(YearMonth.of(2015, 2)));
  }

  @Test
  public void trackG(final TestInfo testInfo) throws Exception {
    final BankCardMagneticTrack trackG =
        BankCardMagneticTrack.from(
            "%B379580832431161^ /                        ^1508121140165241?;379580832431161=150812114016524100000?+6202408082356005=15046200000010000000000004976?");
    checkToString(trackG, testInfo, false);

    checkTrackHealth(trackG);

    final BankCard cardInfo = trackG.toBankCard();
    assertThat(cardInfo.getAccountNumber().getAccountNumber(), is("379580832431161"));
    assertThat(cardInfo.getAccountNumber().getCardBrand(), is(CardBrand.AmericanExpress));
    assertThat(cardInfo.getName().toString(), is(""));
    assertThat(cardInfo.getExpirationDate().getExpirationDate(), is(YearMonth.of(2015, 8)));
  }

  @Test
  public void trackH(final TestInfo testInfo) throws Exception {
    final BankCardMagneticTrack trackH =
        BankCardMagneticTrack.from(
            "%B455618692574^FATEHI/SUALEH             ^888809010299211?;455618692574=888809010299211?");
    checkToString(trackH, testInfo, false);

    checkTrackHealth(trackH);

    final BankCard cardInfo = trackH.toBankCard();
    assertThat(cardInfo.getAccountNumber().getAccountNumber(), is("455618692574"));
    assertThat(cardInfo.getAccountNumber().getCardBrand(), is(CardBrand.Visa));
    assertThat(cardInfo.getName().toString(), is("Sualeh Fatehi"));
    assertThat(cardInfo.getExpirationDate().getExpirationDate(), is(nullValue()));
  }

  @Test
  public void trackI(final TestInfo testInfo) throws Exception {
    final BankCardMagneticTrack trackI = BankCardMagneticTrack.from(";636294169881005271827?");
    checkToString(trackI, testInfo, false);

    assertThat(trackI.exceedsMaximumLength(), is(false));
    assertThat(trackI.getTrack1(), is(not(nullValue())));
    assertThat(trackI.getTrack1().hasAccountNumber(), is(false));
    assertThat(trackI.getTrack2(), is(not(nullValue())));
    assertThat(trackI.getTrack2().hasAccountNumber(), is(false));
    assertThat(trackI.getTrack3(), is(not(nullValue())));

    final BankCard cardInfo = trackI.toBankCard();
    assertThat(cardInfo.getAccountNumber().getAccountNumber(), is(nullValue()));
    assertThat(cardInfo.getAccountNumber().getCardBrand(), is(CardBrand.Unknown));
    assertThat(cardInfo.getName().toString(), is(""));
    assertThat(cardInfo.getExpirationDate().getExpirationDate(), is(nullValue()));
  }

  @Test
  public void trackJ(final TestInfo testInfo) throws Exception {
    final BankCardMagneticTrack trackJ =
        BankCardMagneticTrack.from(
            "%B7083560000013710910^MICHAELS OPEN VALUE CARD  ^8551?;7083560000013710910=8551?");
    checkToString(trackJ, testInfo, false);

    assertThat(trackJ.exceedsMaximumLength(), is(false));
    assertThat(trackJ.getTrack1(), is(not(nullValue())));
    assertThat(trackJ.getTrack1().hasAccountNumber(), is(true));
    assertThat(trackJ.getTrack2(), is(not(nullValue())));
    assertThat(trackJ.getTrack2().hasAccountNumber(), is(false));
    assertThat(trackJ.getTrack3(), is(not(nullValue())));

    final BankCard cardInfo = trackJ.toBankCard();
    assertThat(cardInfo.getAccountNumber().getAccountNumber(), is("7083560000013710910"));
    assertThat(cardInfo.getAccountNumber().getCardBrand(), is(CardBrand.Unknown));
    assertThat(cardInfo.getName().toString(), is("Michaels Open Value Card"));
    assertThat(cardInfo.getExpirationDate().getExpirationDate(), is(nullValue()));
  }

  private void checkToString(
      final BankCardMagneticTrack track, final TestInfo testInfo, final boolean printToConsole)
      throws IOException {

    if (printToConsole) {
      System.out.println(track);
    }

    final String trackToString =
        IOUtils.resourceToString(
            String.format(
                "/BankCardMagneticTrack.%s.txt", testInfo.getTestMethod().get().getName()),
            StandardCharsets.UTF_8);
    assertThat(track.toString().replaceAll("\\R", ""), is(trackToString.replaceAll("\\R", "")));
  }

  private void checkTrackHealth(final BankCardMagneticTrack track) {
    assertThat(track.exceedsMaximumLength(), is(false));
    assertThat(track.getTrack1(), is(not(nullValue())));
    assertThat(track.getTrack1().hasAccountNumber(), is(true));
    assertThat(track.getTrack2(), is(not(nullValue())));
    assertThat(track.getTrack2().hasAccountNumber(), is(true));
    assertThat(track.getTrack3(), is(not(nullValue())));
  }
}
