/*
 *
 * Magnetic Track Parser
 * https://github.com/sualeh/magnetictrackparser
 * Copyright (c) 2014-2021, Sualeh Fatehi.
 *
 */
package us.fatehi.test.magnetictrack;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import java.time.YearMonth;

import org.junit.jupiter.api.Test;

import jdk.nashorn.internal.ir.annotations.Ignore;
import us.fatehi.creditcardnumber.BankCard;
import us.fatehi.creditcardnumber.CardBrand;
import us.fatehi.magnetictrack.BankCardMagneticTrack;

public class ManyTest {

  @Test
  public void trackA() throws Exception {
    final BankCardMagneticTrack trackA =
        BankCardMagneticTrack.from(
            "%B5350290149345177^FATEHI/SUALEH^16042010000000000000000000000000000567001000?;5350290149345177=16042010000056700100?");
    debugPrint(trackA);

    assertThat(trackA.getTrack1(), is(not(nullValue())));
    assertThat(trackA.getTrack1().hasAccountNumber(), is(true));
    assertThat(trackA.getTrack2(), is(not(nullValue())));
    assertThat(trackA.getTrack2().hasAccountNumber(), is(true));
    assertThat(trackA.getTrack3(), is(not(nullValue())));

    final BankCard cardInfo = trackA.toBankCard();
    assertThat(cardInfo.getAccountNumber().getAccountNumber(), is("5350290149345177"));
    assertThat(cardInfo.getAccountNumber().getCardBrand(), is(CardBrand.MasterCard));
    assertThat(cardInfo.getName().toString(), is("Sualeh Fatehi"));
    assertThat(cardInfo.getExpirationDate().getExpirationDate(), is(YearMonth.of(2016, 4)));
  }

  @Test
  public void trackB() throws Exception {
    final BankCardMagneticTrack trackB =
        BankCardMagneticTrack.from(
            "%B4181887684889366^FATEHI/SUALEH^1605101097670000000000120000000?;4181887684889366=160510101200009767?");
    debugPrint(trackB);

    assertThat(trackB.getTrack1(), is(not(nullValue())));
    assertThat(trackB.getTrack1().hasAccountNumber(), is(true));
    assertThat(trackB.getTrack2(), is(not(nullValue())));
    assertThat(trackB.getTrack2().hasAccountNumber(), is(true));
    assertThat(trackB.getTrack3(), is(not(nullValue())));

    final BankCard cardInfo = trackB.toBankCard();
    assertThat(cardInfo.getAccountNumber().getAccountNumber(), is("4181887684889366"));
    assertThat(cardInfo.getAccountNumber().getCardBrand(), is(CardBrand.Visa));
    assertThat(cardInfo.getName().toString(), is("Sualeh Fatehi"));
    assertThat(cardInfo.getExpirationDate().getExpirationDate(), is(YearMonth.of(2016, 5)));
  }

  @Test
  public void trackC() throws Exception {
    final BankCardMagneticTrack trackC =
        BankCardMagneticTrack.from(
            "%B5391285197433215^FATEHI/SUALEH             ^1701101000001540000000154000000?;5391285197433215=17011010000015400000?");
    debugPrint(trackC);

    assertThat(trackC.getTrack1(), is(not(nullValue())));
    assertThat(trackC.getTrack1().hasAccountNumber(), is(true));
    assertThat(trackC.getTrack2(), is(not(nullValue())));
    assertThat(trackC.getTrack2().hasAccountNumber(), is(true));
    assertThat(trackC.getTrack3(), is(not(nullValue())));

    final BankCard cardInfo = trackC.toBankCard();
    assertThat(cardInfo.getAccountNumber().getAccountNumber(), is("5391285197433215"));
    assertThat(cardInfo.getAccountNumber().getCardBrand(), is(CardBrand.MasterCard));
    assertThat(cardInfo.getName().toString(), is("Sualeh Fatehi"));
    assertThat(cardInfo.getExpirationDate().getExpirationDate(), is(YearMonth.of(2017, 1)));
  }

  @Test
  public void trackD() throws Exception {
    final BankCardMagneticTrack trackD =
        BankCardMagneticTrack.from(
            "%B6011460477609366^FATEHI/SUALEH             ^15101011000606818102?;6011460477609366=15101011000606818102?");
    debugPrint(trackD);

    assertThat(trackD.getTrack1(), is(not(nullValue())));
    assertThat(trackD.getTrack1().hasAccountNumber(), is(true));
    assertThat(trackD.getTrack2(), is(not(nullValue())));
    assertThat(trackD.getTrack2().hasAccountNumber(), is(true));
    assertThat(trackD.getTrack3(), is(not(nullValue())));

    final BankCard cardInfo = trackD.toBankCard();
    assertThat(cardInfo.getAccountNumber().getAccountNumber(), is("6011460477609366"));
    assertThat(cardInfo.getAccountNumber().getCardBrand(), is(CardBrand.Discover));
    assertThat(cardInfo.getName().toString(), is("Sualeh Fatehi"));
    assertThat(cardInfo.getExpirationDate().getExpirationDate(), is(YearMonth.of(2015, 10)));
  }

  @Test
  public void trackE() throws Exception {
    final BankCardMagneticTrack trackE =
        BankCardMagneticTrack.from(
            "%B6035320294113574^FATEHI/SUALEH^491210100316000000?;6035320294113574=491210110000316?");
    debugPrint(trackE);

    assertThat(trackE.getTrack1(), is(not(nullValue())));
    assertThat(trackE.getTrack1().hasAccountNumber(), is(true));
    assertThat(trackE.getTrack2(), is(not(nullValue())));
    assertThat(trackE.getTrack2().hasAccountNumber(), is(true));
    assertThat(trackE.getTrack3(), is(not(nullValue())));

    final BankCard cardInfo = trackE.toBankCard();
    assertThat(cardInfo.getAccountNumber().getAccountNumber(), is("6035320294113574"));
    assertThat(cardInfo.getAccountNumber().getCardBrand(), is(CardBrand.Unknown));
    assertThat(cardInfo.getName().toString(), is("Sualeh Fatehi"));
    assertThat(cardInfo.getExpirationDate().getExpirationDate(), is(YearMonth.of(2049, 12)));
  }

  @Test
  public void trackF() throws Exception {
    final BankCardMagneticTrack trackF =
        BankCardMagneticTrack.from(
            "%B4181887684889366^FATEHI/SUALEH^1502101072560000000000019000000?;4181887684889366=150210100190007256?");
    debugPrint(trackF);

    assertThat(trackF.getTrack1(), is(not(nullValue())));
    assertThat(trackF.getTrack1().hasAccountNumber(), is(true));
    assertThat(trackF.getTrack2(), is(not(nullValue())));
    assertThat(trackF.getTrack2().hasAccountNumber(), is(true));
    assertThat(trackF.getTrack3(), is(not(nullValue())));

    final BankCard cardInfo = trackF.toBankCard();
    assertThat(cardInfo.getAccountNumber().getAccountNumber(), is("4181887684889366"));
    assertThat(cardInfo.getAccountNumber().getCardBrand(), is(CardBrand.Visa));
    assertThat(cardInfo.getName().toString(), is("Sualeh Fatehi"));
    assertThat(cardInfo.getExpirationDate().getExpirationDate(), is(YearMonth.of(2015, 2)));
  }

  @Test
  public void trackG() throws Exception {
    final BankCardMagneticTrack trackG =
        BankCardMagneticTrack.from(
            "%B379580832431161^ /                        ^1508121140165241?;379580832431161=150812114016524100000?+6202408082356005=15046200000010000000000004976?");
    debugPrint(trackG);

    assertThat(trackG.getTrack1(), is(not(nullValue())));
    assertThat(trackG.getTrack1().hasAccountNumber(), is(true));
    assertThat(trackG.getTrack2(), is(not(nullValue())));
    assertThat(trackG.getTrack2().hasAccountNumber(), is(true));
    assertThat(trackG.getTrack3(), is(not(nullValue())));

    final BankCard cardInfo = trackG.toBankCard();
    assertThat(cardInfo.getAccountNumber().getAccountNumber(), is("379580832431161"));
    assertThat(cardInfo.getAccountNumber().getCardBrand(), is(CardBrand.AmericanExpress));
    assertThat(cardInfo.getName().toString(), is(""));
    assertThat(cardInfo.getExpirationDate().getExpirationDate(), is(YearMonth.of(2015, 8)));
  }

  @Test
  public void trackH() throws Exception {
    final BankCardMagneticTrack trackH =
        BankCardMagneticTrack.from(
            "%B455618692574^FATEHI/SUALEH             ^888809010299211?;455618692574=888809010299211?");
    debugPrint(trackH);

    assertThat(trackH.getTrack1(), is(not(nullValue())));
    assertThat(trackH.getTrack1().hasAccountNumber(), is(true));
    assertThat(trackH.getTrack2(), is(not(nullValue())));
    assertThat(trackH.getTrack2().hasAccountNumber(), is(true));
    assertThat(trackH.getTrack3(), is(not(nullValue())));

    final BankCard cardInfo = trackH.toBankCard();
    assertThat(cardInfo.getAccountNumber().getAccountNumber(), is("455618692574"));
    assertThat(cardInfo.getAccountNumber().getCardBrand(), is(CardBrand.Visa));
    assertThat(cardInfo.getName().toString(), is("Sualeh Fatehi"));
    assertThat(cardInfo.getExpirationDate().getExpirationDate(), is(nullValue()));
  }

  @Ignore
  @Test
  public void trackI() throws Exception {
    final BankCardMagneticTrack trackI = BankCardMagneticTrack.from(";636294169881005271827?");
    debugPrint(trackI);
    final BankCard cardInfo = trackI.toBankCard();
    assertThat(cardInfo.getAccountNumber().getAccountNumber(), is(nullValue()));
    assertThat(cardInfo.getAccountNumber().getCardBrand(), is(CardBrand.Unknown));
    assertThat(cardInfo.getName().toString(), is(""));
    assertThat(cardInfo.getExpirationDate().getExpirationDate(), is(nullValue()));
  }

  @Test
  public void trackJ() throws Exception {
    final BankCardMagneticTrack trackJ =
        BankCardMagneticTrack.from(
            "%B7083560000013710910^MICHAELS OPEN VALUE CARD  ^8551?;7083560000013710910=8551?");
    debugPrint(trackJ);

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

  private void debugPrint(final BankCardMagneticTrack track) {
    if (false) {
      System.out.println(track);
    }
  }
}
