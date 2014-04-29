/*
 *
 * Magnetic Stripe Parser
 * https://github.com/sualeh/magnetictrackparser
 * Copyright (c) 2014, Sualeh Fatehi.
 *
 * This library is free software; you can redistribute it and/or modify it under the terms
 * of the GNU Lesser General Public License as published by the Free Software Foundation;
 * either version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * library; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA 02111-1307, USA.
 *
 */
package us.fatehi.test.magnetictrack.bankcard;


import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;
import org.threeten.bp.YearMonth;

import us.fatehi.magnetictrack.bankcard.CardBrand;
import us.fatehi.magnetictrack.bankcard.CardInfo;
import us.fatehi.magnetictrack.bankcard.MagneticTrack;

public class ManyTest
{

  @Test
  public void trackA()
      throws Exception
  {
    final MagneticTrack trackA = new MagneticTrack("%B5350290149345177^FATEHI/SUALEH^16042010000000000000000000000000000567001000?;5350290149345177=16042010000056700100?");
    final CardInfo cardInfo = trackA.getCardInfo();
    System.out.println(trackA);
    assertEquals("5350290149345177", cardInfo.getPrimaryAccountNumber()
                 .getAccountNumber());
    assertEquals(CardBrand.MasterCard, cardInfo.getPrimaryAccountNumber()
                 .getCardBrand());
    assertEquals("Sualeh Fatehi", cardInfo.getName().toString());
    assertEquals(YearMonth.of(2016, 4), cardInfo.getExpirationDate());
  }

  @Test
  public void trackB()
      throws Exception
  {
    final MagneticTrack trackB = new MagneticTrack("%B4181887684889366^FATEHI/SUALEH^1605101097670000000000120000000?;4181887684889366=160510101200009767?");
    final CardInfo cardInfo = trackB.getCardInfo();
    System.out.println(trackB);
    assertEquals("4181887684889366", cardInfo.getPrimaryAccountNumber()
                 .getAccountNumber());
    assertEquals(CardBrand.Visa, cardInfo.getPrimaryAccountNumber()
                 .getCardBrand());
    assertEquals("Sualeh Fatehi", cardInfo.getName().toString());
    assertEquals(YearMonth.of(2016, 5), cardInfo.getExpirationDate());
  }

  @Test
  public void trackC()
      throws Exception
  {
    final MagneticTrack trackC = new MagneticTrack("%B5391285197433215^FATEHI/SUALEH             ^1701101000001540000000154000000?;5391285197433215=17011010000015400000?");
    final CardInfo cardInfo = trackC.getCardInfo();
    System.out.println(trackC);
    assertEquals("5391285197433215", cardInfo.getPrimaryAccountNumber()
                 .getAccountNumber());
    assertEquals(CardBrand.MasterCard, cardInfo.getPrimaryAccountNumber()
                 .getCardBrand());
    assertEquals("Sualeh Fatehi", cardInfo.getName().toString());
    assertEquals(YearMonth.of(2017, 1), cardInfo.getExpirationDate());
  }

  @Test
  public void trackD()
      throws Exception
  {
    final MagneticTrack trackD = new MagneticTrack("%B6011460477609366^FATEHI/SUALEH             ^15101011000606818102?;6011460477609366=15101011000606818102?");
    final CardInfo cardInfo = trackD.getCardInfo();
    System.out.println(trackD);
    assertEquals("6011460477609366", cardInfo.getPrimaryAccountNumber()
                 .getAccountNumber());
    assertEquals(CardBrand.Discover, cardInfo.getPrimaryAccountNumber()
                 .getCardBrand());
    assertEquals("Sualeh Fatehi", cardInfo.getName().toString());
    assertEquals(YearMonth.of(2015, 10), cardInfo.getExpirationDate());
  }

  @Test
  public void trackE()
      throws Exception
  {
    final MagneticTrack trackE = new MagneticTrack("%B6035320294113574^FATEHI/SUALEH^491210100316000000?;6035320294113574=491210110000316?");
    final CardInfo cardInfo = trackE.getCardInfo();
    System.out.println(trackE);
    assertEquals("6035320294113574", cardInfo.getPrimaryAccountNumber()
                 .getAccountNumber());
    assertEquals(CardBrand.unknown, cardInfo.getPrimaryAccountNumber()
                 .getCardBrand());
    assertEquals("Sualeh Fatehi", cardInfo.getName().toString());
    assertEquals(YearMonth.of(2049, 12), cardInfo.getExpirationDate());
  }

  @Test
  public void trackF()
      throws Exception
  {
    final MagneticTrack trackF = new MagneticTrack("%B4181887684889366^FATEHI/SUALEH^1502101072560000000000019000000?;4181887684889366=150210100190007256?");
    final CardInfo cardInfo = trackF.getCardInfo();
    System.out.println(trackF);
    assertEquals("4181887684889366", cardInfo.getPrimaryAccountNumber()
                 .getAccountNumber());
    assertEquals(CardBrand.Visa, cardInfo.getPrimaryAccountNumber()
                 .getCardBrand());
    assertEquals("Sualeh Fatehi", cardInfo.getName().toString());
    assertEquals(YearMonth.of(2015, 2), cardInfo.getExpirationDate());
  }

  @Test
  public void trackG()
      throws Exception
  {
    final MagneticTrack trackG = new MagneticTrack("%B379580832431161^ /                        ^1508121140165241?;379580832431161=150812114016524100000?+6202408082356005=15046200000010000000000004976?");
    final CardInfo cardInfo = trackG.getCardInfo();
    System.out.println(trackG);
    assertEquals("379580832431161", cardInfo.getPrimaryAccountNumber()
                 .getAccountNumber());
    assertEquals(CardBrand.AmericanExpress, cardInfo.getPrimaryAccountNumber()
                 .getCardBrand());
    assertEquals("", cardInfo.getName().toString());
    assertEquals(YearMonth.of(2015, 8), cardInfo.getExpirationDate());
  }

  @Test
  public void trackH()
      throws Exception
  {
    final MagneticTrack trackH = new MagneticTrack("%B455618692574^FATEHI/SUALEH             ^888809010299211?;455618692574=888809010299211?");
    final CardInfo cardInfo = trackH.getCardInfo();
    System.out.println(trackH);
    assertEquals("455618692574", cardInfo.getPrimaryAccountNumber()
                 .getAccountNumber());
    assertEquals(CardBrand.Visa, cardInfo.getPrimaryAccountNumber()
                 .getCardBrand());
    assertEquals("Sualeh Fatehi", cardInfo.getName().toString());
    assertEquals(null, cardInfo.getExpirationDate());
  }

  @Ignore
  @Test
  public void trackI()
      throws Exception
  {
    final MagneticTrack trackI = new MagneticTrack(";636294169881005271827?");
    final CardInfo cardInfo = trackI.getCardInfo();
    System.out.println(trackI);
    assertEquals("636294169881005271827", cardInfo.getPrimaryAccountNumber()
                 .getAccountNumber());
    assertEquals(CardBrand.unknown, cardInfo.getPrimaryAccountNumber()
                 .getCardBrand());
    assertEquals("", cardInfo.getName().toString());
    assertEquals(null, cardInfo.getExpirationDate());
  }

  @Test
  public void trackJ()
      throws Exception
  {
    final MagneticTrack trackJ = new MagneticTrack("%B7083560000013710910^MICHAELS OPEN VALUE CARD  ^8551?;7083560000013710910=8551?");
    final CardInfo cardInfo = trackJ.getCardInfo();
    System.out.println(trackJ);
    assertEquals("7083560000013710910", cardInfo.getPrimaryAccountNumber()
                 .getAccountNumber());
    assertEquals(CardBrand.unknown, cardInfo.getPrimaryAccountNumber()
                 .getCardBrand());
    assertEquals("Michaels Open Value Card", cardInfo.getName().toString());
    assertEquals(null, cardInfo.getExpirationDate());
  }

}
