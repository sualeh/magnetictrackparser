[![Build Status](https://travis-ci.org/sualeh/magnetictrackparser.svg?branch=master)](https://travis-ci.org/sualeh/magnetictrackparser)
[![Maven Central](https://img.shields.io/maven-central/v/us.fatehi/magnetictrackparser.svg)](http://search.maven.org/#search%7Cga%7C1%7Cg%3Aus.fatehi%20magnetictrackparser)

# Magnetic Track Parser

*Magnetic Track Parser* is a Java library that can parse magnetic track data from a 
bank issued credit card, such as might be returned from a USB magnetic card stripe 
reader. 

> **The goal of this project is to use publicly and freely available documentation 
to create a reliable Java library to provide information about magnetic tracks and 
credit card numbers.**

All classes are immutable and thread-safe. The standard `toString()` function 
formats data in a readable form. Validity is enforced by JUnit tests. 

Magnetic Track Parser depends on the [Credit Card Number](https://github.com/sualeh/credit_card_number) library.

Java 6 or newer is required. This library deliberately supports Java 6, to make it 
usable in Android apps.

Some resources consulted are:
* [Magnetic stripe card](http://en.wikipedia.org/wiki/Magnetic_stripe_card) on Wikipedia for information about the format of track data.

## Download

You can download the [jar on the Maven Central Repository](http://search.maven.org/#search%7Cga%7C1%7Ca%3A%22magnetictrackparser%22).

## Maven Build

To use *Magnetic Track Parser* in your Maven build, include the following 
dependency. No repositories references are needed, since the jars are in the Maven 
Central Repository.
```xml
<dependency>
    <groupId>us.fatehi</groupId>
    <artifactId>magnetictrackparser</artifactId>
    <version>1.10.01</version>
</dependency>
```

## Examples

### How to Get Bank Card Information

To get bank card information, use code like:
```java
final PrimaryAccountNumber pan = new  AccountNumber("371449635398431");
final BankCard card = new BankCard(pan);
System.out.println(card);
```
and you will get this output:
```
Bank Card Information: 
  Primary Account Number: 371449635398431
  Primary Account Number (Secure): AmericanExpress-8431
    Major Industry Identifier: 3 - Travel and entertainment and banking/financial
    Issuer Identification Number: 371449
    Card Brand: AmericanExpress
    Last Four Digits: 8431
    Passes Luhn Check? Yes
    Is Primary Account Number Valid? Yes
```

### How to Parse Magnetic Track Data

To parse a magnetic track, use code like:
```java
final BankCardMagneticTrack track = 
    BankCardMagneticTrack.from("%B5350290149345177^FATEHI/SUALEH^16042010000000000000000000000000000567001000?;5350290149345177=16042010000056700100?");
System.out.println(track);
```
and you will get this output:
```
TRACK 1: %B5350290149345177^FATEHI/SUALEH^16042010000000000000000000000000000567001000?
  Primary Account Number: 5350290149345177
  Expiration Date: April 2016
  Name: Sualeh Fatehi
  Service Code: 201
  Discretionary Data: 0000000000000000000000000000567001000
TRACK 2: ;5350290149345177=16042010000056700100?
  Primary Account Number: 5350290149345177
  Expiration Date: April 2016
  Service Code: 201
  Discretionary Data: 0000056700100
TRACK 3:  Not Available.

Bank Card Information: 
  Primary Account Number: 5350290149345177
  Primary Account Number (Secure): MasterCard-5177
    Major Industry Identifier: 5 - Banking and financial
    Issuer Identification Number: 535029
    Card Brand: MasterCard
    Last Four Digits: 5177
    Passes Luhn Check? Yes
    Is Primary Account Number Valid? Yes
  Expiration Date: April 2016
    Is Expired: No
  Name: Sualeh Fatehi
  Service Code: 
    2 - Interchange: International interchange. Technology: Integrated circuit card.
    0 - Authorization Processing: Normal.
    1 - Allowed Services: No restrictions. PIN Requirements: None.
```

## Who Uses

- Dmitry Holodov's excellent Android app, [SwipeYours](https://play.google.com/store/apps/details?id=to.noc.android.swipeyours) uses   _magnetictrackparser_. See details in [SetCardActivity.java](
https://github.com/dimalinux/SwipeYours/blob/master/src/main/java/to/noc/android/swipeyours/SetCardActivity.java#L34-L71). This Android app helps developers understand the new Android Host Card Emulation feature for payments, and is written with open source, in the same spirit as _magnetictrackparser_.
