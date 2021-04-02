/*
 *
 * Magnetic Track Parser
 * https://github.com/sualeh/magnetictrackparser
 * Copyright (c) 2014-2021, Sualeh Fatehi.
 *
 */
package com.example;

import us.fatehi.creditcardnumber.AccountNumber;
import us.fatehi.creditcardnumber.AccountNumbers;
import us.fatehi.creditcardnumber.BankCard;

public class SampleCode1 {

  public static void main(final String[] args) {
    final AccountNumber pan = AccountNumbers.accountNumber("371449635398431");
    final BankCard card = new BankCard(pan);
    System.out.println(card);
  }
}
