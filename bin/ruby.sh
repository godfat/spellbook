#!/bin/sh

scala -cp \
build/lib/spellbook-core.jar:\
/usr/local/Cellar/jruby/1.5.6/jruby/lib/jruby.jar:\
jruby-engine.jar \
bin/ruby.scala

# jruby --1.9 src/org/spbk/rb/spellbook.rb
