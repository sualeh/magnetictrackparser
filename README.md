# Magnetic Track Parser

*Magnetic Track Parser* is a Java library that can parse magnetic track data from a bank issued credit card, such as might be returned from a USB magnetic card stripe reader. All classes are immutable and thread-safe. The standard `toString()` function formats data in a readable form. Validity is enforced by JUnit tests. Maven is needed for a build. Java 6 or newer is required.

See the article on [Magnetic stripe card] on Wikipedia for information about the format of track data.

[Magnetic stripe card]: http://en.wikipedia.org/wiki/Magnetic_stripe_card

## Download

You can download the [jar on the Maven Central Repository].

## Maven Build

To use *Magnetic Track Parser* in your Maven build, include the following dependency. No repositories references are needed, since the jars are in the Maven Central Repository.
```xml
<dependency>
    <groupId>us.fatehi</groupId>
    <artifactId>magnetictrackparser</artifactId>
    <version>1.2</version>
</dependency>
```


[jar on the Maven Central Repository]: http://search.maven.org/#search%7Cga%7C1%7Ca%3A%22magnetictrackparser%22


## Examples

### How to Get Bank Card Information

To get bank card information, use code like:
```
final PrimaryAccountNumber pan = new PrimaryAccountNumber("371449635398431");
final BankCard card = new BankCard(pan);
System.out.println(card);
```
and you will get this output:
```
Bank Card Information: 
  Primary Account Number: 371449635398431
    MII: 3 - Travel and entertainment and banking/financial
    IIN: 371449
    Card Brand: AmericanExpress
    Passes Luhn Check: true
```

### How to Parse Magnetic Track Data

To parse a magnetic track, use code like:
```
final BankCardMagneticTrack track = 
    BankCardMagneticTrack.from("%B5350290149345177^FATEHI/SUALEH^16042010000000000000000000000000000567001000?;5350290149345177=16042010000056700100?");
System.out.println(track);
```
and you will get this output:
```
Track 1: %B5350290149345177^FATEHI/SUALEH^16042010000000000000000000000000000567001000?
  Primary Account Number: 5350290149345177
    MII: 5 - Banking and financial
    IIN: 535029
    Card Brand: MasterCard
  Expiration Date: April 2016
  Name: Sualeh Fatehi
  Service Code: 
    2 - Interchange: International interchange. Technology: Integrated circuit card.
    0 - Authorization Processing: Normal.
    1 - Allowed Services: No restrictions. PIN Requirements: None.
  Discretionary Data: 0000000000000000000000000000567001000
Track 2: ;5350290149345177=16042010000056700100?
  Primary Account Number: 5350290149345177
    MII: 5 - Banking and financial
    IIN: 535029
    Card Brand: MasterCard
  Expiration Date: April 2016
  Service Code: 
    2 - Interchange: International interchange. Technology: Integrated circuit card.
    0 - Authorization Processing: Normal.
    1 - Allowed Services: No restrictions. PIN Requirements: None.
  Discretionary Data: 0000056700100
No Track 3 Data
```
