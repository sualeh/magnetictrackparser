# Magnetic Track Parser

Magnetic Track Parser is a Java 7 library that can parse magnetic tracks from a bank issued card. All classes are immutable and thread-safe. The standard `toString()` function formats data in a readable form. Validity is enforced by JUnit tests. Maven is needed for a build.

## Download

You can download the [jar on the Maven Central Repository].

## Maven Build

To use *Magnetic Track Parser* in your Maven build, include the following dependency. No repositories references are needed, since the jars are in the Maven Central Repository.
```xml
<dependency>
    <groupId>us.fatehi</groupId>
    <artifactId>magnetictrackparser</artifactId>
    <version>1.0</version>
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