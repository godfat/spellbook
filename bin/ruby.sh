#!/bin/sh

scala-2.8.0.r20436-b20100111020117/bin/scala -cp \
.:jruby-engine.jar:/Users/godfat/.rvm/jruby-1.4.0/lib/jruby.jar \
bin/ruby.scala

jruby -r java src/org/spbk/rb/core-set.rb
