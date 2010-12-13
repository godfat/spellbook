#!/bin/sh

scala -deprecation -cp \
build/lib/spellbook-core.jar:scalacheck_2.8.1-1.8.jar \
bin/check.scala
