/*
 *
 * Magnetic Track Parser
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
package us.fatehi.magnetictrack;


import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.Scanner;

import us.fatehi.magnetictrack.bankcard.BankCard;
import us.fatehi.magnetictrack.bankcard.PrimaryAccountNumber;

public class Main
{

  public static void main(final String[] args)
  {
    final Scanner in = new Scanner(System.in);

    System.out.println("1. Parse magnetic track data");
    System.out.println("2. Get bank card information from card number");
    System.out.print("Choice: ");
    int choice = 0;
    while (choice != 1 && choice != 2)
    {
      choice = in.nextInt();
      switch (choice)
      {
        case 1:
          parseMagneticTrackData(in);
          break;
        case 2:
          getBankCardInformation(in);
          break;
        default:
          break;
      }
      choice = 0;
    }
  }

  private static void getBankCardInformation(final Scanner in)
  {
    String line = "";
    do
    {
      System.out.print("Bank Card Number: ");
      line = in.next();
      if (!isBlank(line))
      {
        final PrimaryAccountNumber pan = new PrimaryAccountNumber(line);
        final BankCard card = new BankCard(pan);
        System.out.println(card);
      }
      else
      {
        return;
      }
    } while (!isBlank(line));
  }

  private static void parseMagneticTrackData(final Scanner in)
  {
    final StringBuilder buffer = new StringBuilder();
    String line = "";
    do
    {
      line = in.nextLine();
      if (!isBlank(line))
      {
        buffer.setLength(0);

      }
      else
      {
        buffer.append(line);
      }
    } while (!isBlank(line));
  }

}
