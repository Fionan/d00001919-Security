# Sectool #

Built with IntelliJ Community edition

- Java 21/17
- Maven build

Built as a JAR and run `java -jar Sectool.jar`. This program uses my MenuMaker CLI library. It will run the CLI menu and then call main methods for the chosen algorithms.

**NOTE:** These are essentially scripts with little to no error handling. Double-check your inputs!


These are built to the spec of work done within class; they are NOT suitable for anything else:

## Build without IntelliJ
- prerequisites :
  - Java 17
  - Maven
  - git

```
git clone https://github.com/Fionan/d00001919-Security.git \'
cd d00001919-Security
mvn package
java -cp target/Sectool-1.0-SNAPSHOT.jar eu.lemondreams.security.Main
```


## Feistel
- Expects (8) bits like 11011101
- Expects (4) rounds
- Expects Key like (6) 110101

## RSA-Full
- Accepts ints only

## RSA-DH
- Expects ints only

## RSA-enc
- Expects E or D (encrypt/Decrypt)
- n (modulus) as INT
- d or e as INT

## Vigenere
- Can deal with letters.

**TO-DO**
- rot13
  - For implementation of this you can go to: [rot13.com](https://rot13.com/)
