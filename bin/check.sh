#!/bin/sh

scala-2.8.0.r20436-b20100111020117/bin/scala -cp \
build/lib/spellbook.jar:ScalaCheck-1.5-Scala2.8.0.r17999-b20090605202829.jar \
bin/check.scala
