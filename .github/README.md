<!-- markdownlint-disable MD041 -->
[![Quick Build](https://github.com/sualeh/creditcardnumber/workflows/Quick%20Build/badge.svg)](https://github.com/sualeh/magnetictrackparser/actions?query=workflow%3A%22Quick+Build%22)
[![The Central Repository](https://img.shields.io/maven-central/v/us.fatehi/magnetictrackparser.svg)](https://search.maven.org/search?q=g:us.fatehi%20magnetictrackparser*)


# Magnetic Track Parser

*Magnetic Track Parser* is a Java library that can parse magnetic track data from a 
bank issued credit card, such as might be returned from a USB magnetic card stripe 
reader. 

Magnetic Track Parser depends on the [Credit Card Number](https://github.com/sualeh/creditcardnumber) library.

## Resources

> **The goal of this project is to use publicly and freely available documentation 
to create a reliable Java library to provide information about credit cards.**

Some resources consulted are:
* [Magnetic stripe card](http://en.wikipedia.org/wiki/Magnetic_stripe_card) on Wikipedia for information about the format of track data.


## Design Principles

- All classes are immutable and thread-safe
- Secure data follows standards in the 
[Java Cryptography Architecture (JCA) Reference Guide](https://docs.oracle.com/javase/8/docs/technotes/guides/security/crypto/CryptoSpec.html#PBEEx)
- The standard `toString()` function formats data in a readable form
- Internationalization of card numbers is supported
- Validity is enforced by JUnit 5 tests
- Java 8 or newer is required


## Download and Use in Projects

You can [download the jar on the Maven Central Repository](https://search.maven.org/artifact/us.fatehi/magnetictrackparser). 
The [download page](https://search.maven.org/artifact/us.fatehi/magnetictrackparser) 
has instructions on how to use the library in your Maven or Gradle build.


## Examples

### How to Parse Magnetic Track Data

To parse a magnetic track, use code like:
```java
BankCardMagneticTrack track = 
  BankCardMagneticTrack.from("%B5350290149345177^FATEHI/SUALEH^16042010000000000000000000000000000567001000?;5350290149345177=16042010000056700100?");
System.out.println(track);
```
and you will get this output:
```
TRACK 1: %B5350290149345177^FATEHI/SUALEH^16042010000000000000000000000000000567001000?
  Primary Account Number: 5350290149345177
  Expiration Date: 2016-04
  Name: Sualeh Fatehi
  Service Code: 201
  Discretionary Data: 0000000000000000000000000000567001000
TRACK 2: ;5350290149345177=16042010000056700100?
  Primary Account Number: 5350290149345177
  Expiration Date: 2016-04
  Service Code: 201
  Discretionary Data: 0000056700100
TRACK 3:  Not Available.

Bank Card Information: 
  Raw Account Number: 5350290149345177
  Primary Account Number: 5350290149345177
    Major Industry Identifier: 5 - Banking and financial
    Issuer Identification Number: 53502901
    Card Brand: MasterCard
    Last Four Digits: 5177
    Passes Luhn Check? Yes
    Is Primary Account Number Valid? Yes
  Expiration Date: 2016-04
    Is Expired? Yes
  Name: Sualeh Fatehi
  Service Code: 
    2 - Interchange: International interchange. Technology: Integrated circuit card.
    0 - Authorization Processing: Normal.
    1 - Allowed Services: No restrictions. PIN Requirements: None.
```

## Who Uses

- Dmitry Holodov's excellent Android app, [SwipeYours](https://github.com/dimalinux/SwipeYours) uses   _magnetictrackparser_. See details in [SetCardActivity.java](
https://github.com/dimalinux/SwipeYours/blob/master/src/main/java/to/noc/android/swipeyours/SetCardActivity.java#L34-L37). This Android app helps developers understand the new Android Host Card Emulation feature for payments, and is written with open source, in the same spirit as _magnetictrackparser_.
