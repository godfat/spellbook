#!/bin/sh

scala -deprecation -cp \
build/lib/spellbook-core.jar:scalacheck_2.9.0-1.9.jar \
bin/check.scala
