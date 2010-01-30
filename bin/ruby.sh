#!/bin/sh

scala -cp \
.:jruby-engine.jar:/opt/local/share/java/jruby/lib/jruby.jar \
bin/ruby.scala

jruby -r java src/org/spbk/rb/core-set.rb
