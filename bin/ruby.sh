#!/bin/sh

scala -cp \
build/lib/spellbook-core.jar:\
/usr/local/Cellar/jruby/1.6.0/jruby/lib/jruby.jar \
bin/ruby.scala

jruby --1.9 src/org/spbk/rb/spellbook.rb
