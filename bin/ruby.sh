#!/bin/sh

scala -cp \
.:jruby-engine.jar:/usr/local/Cellar/jruby/1.5.6/jruby/lib/jruby.jar \
bin/ruby.scala

jruby -r java src/org/spbk/rb/core-set.rb
