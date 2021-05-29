# Eclipse tm4e.core - TextMate grammar support for the JVM

[![Build Status](https://github.com/Sciss/tm4e-core/workflows/Scala%20CI/badge.svg?branch=main)](https://github.com/Sciss/tm4e-core/actions?query=workflow%3A%22Scala+CI%22)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/de.sciss/tm4e-core/badge.svg)](https://maven-badges.herokuapp.com/maven-central/de.sciss/tm4e-core)

## statement

Fork of https://github.com/eclipse/tm4e that only contains the core project. Uses sbt for building.

TM4E brings Java API to tokenize textual documents according to TextMate grammars, for example to do syntax highlighting according to this tokenization.
The core module is a Java port of [vscode-textmate](https://github.com/Microsoft/vscode-textmate) written in TypeScript. This Java API can be used with any 
Java UI Toolkit (Swing, Eclipse, etc). See [Core](https://github.com/eclipse/tm4e/wiki/Core) section for more information.

This project is released under the Eclipse Public License 1.0.

## requirements / installation

This project builds with [sbt](https://www.scala-sbt.org/). It requires Java 8 or newer.

To link to the library:

    libraryDependencies += "de.sciss" % "tm4e-core" % v

The current version `v` is `"0.1.0"`
