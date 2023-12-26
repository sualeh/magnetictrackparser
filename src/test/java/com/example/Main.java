/*
 *
 * Magnetic Track Parser
 * https://github.com/sualeh/magnetictrackparser
 * Copyright (c) 2014-2024, Sualeh Fatehi.
 *
 */
package com.example;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.math.NumberUtils.toInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import us.fatehi.magnetictrack.BankCardMagneticTrack;

/** Magnetic Track Parser console application. */
public class Main {

  public static void main(final String[] args) throws Exception {

    final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    while (true) {
      System.out.println("** Press <Ctrl-C> to quit **");
      parseMagneticTrackData(in);
    }
  }

  private static void parseMagneticTrackData(final BufferedReader in) throws IOException {
    while (true) {
      System.out.println("Magnetic Track (followed by a blank line): ");
      final StringBuilder buffer = new StringBuilder();
      while (true) {
        final String line = in.readLine();
        final int choice = toInt(line, -1);
        if (choice == 0) {
          return;
        }

        if (!isBlank(line)) {
          buffer.append(line);
        } else {
          final BankCardMagneticTrack track = BankCardMagneticTrack.from(buffer.toString());
          System.out.println(track);
          break;
        }
      }
    }
  }
}
