/*
 *
 * Magnetic Track Parser
 * https://github.com/sualeh/magnetictrackparser
 * Copyright (c) 2014-2024, Sualeh Fatehi.
 *
 */
package com.example;

import us.fatehi.magnetictrack.BankCardMagneticTrack;

public class SampleCode1 {

  public static void main(final String[] args) {
    final BankCardMagneticTrack track =
        BankCardMagneticTrack.from(
            "%B5350290149345177^FATEHI/SUALEH^16042010000000000000000000000000000567001000?;5350290149345177=16042010000056700100?");
    System.out.println(track);
  }
}
