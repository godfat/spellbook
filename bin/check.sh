#!/bin/sh

scala -deprecation -cp \
build/lib/spellbook-core.jar:scalacheck_2.8.0.Beta1-RC5-1.7-SNAPSHOT.jar \
bin/check.scala
