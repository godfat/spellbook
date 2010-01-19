#!/bin/sh

scala-2.8.0.r20436-b20100111020117/bin/scala -deprecation -cp \
build/lib/spellbook-core.jar:scalacheck_2.8.0.Beta1-RC5-1.7-SNAPSHOT.jar \
bin/check.scala
