/*
 *
 * Magnetic Track Parser
 * https://github.com/sualeh/magnetictrackparser
 * Copyright (c) 2014-2016, Sualeh Fatehi.
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
import static org.apache.commons.lang3.math.NumberUtils.toInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import us.fatehi.magnetictrack.bankcard.BankCardMagneticTrack;

/**
 * Magnetic Track Parser console application.
 */
public class Main
{

  public static void main(final String[] args)
    throws Exception
  {
    Version.main(new String[0]);

    final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    while (true)
    {
      System.out.println("** Press <Ctrl-C> to quit **");
      parseMagneticTrackData(in);
    }

  }

  private static void parseMagneticTrackData(final BufferedReader in)
    throws IOException
  {
    while (true)
    {
      System.out.println("Magnetic Track (followed by a blank line): ");
      final StringBuilder buffer = new StringBuilder();
      while (true)
      {
        final String line = in.readLine();
        final int choice = toInt(line, -1);
        if (choice == 0)
        {
          return;
        }

        if (!isBlank(line))
        {
          buffer.append(line);
        }
        else
        {
          final BankCardMagneticTrack track = BankCardMagneticTrack
            .from(buffer.toString());
          System.out.println(track);
          break;
        }
      }
    }
  }

}
