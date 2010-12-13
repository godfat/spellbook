
[Spellbook](http://spbk.org)
======================================

This is an experiment to the models of Spellbook,<br/>
a game I designed years ago, but never implemented.<br/>
It was based on Vantage Master&trade; from Falcom and<br/>
Magic: the Gathering&trade; from Wizards of the Coast.

I would import a few documents written before,<br/>
after translating them from Chinese to English,<br/>
or should i just import them and write docs in Chinese?

Dependency
======================================

* scala 2.8+
* jruby 1.5+
* ant
* scalacheck

Installing on Mac
======================================

* install scala

  `brew install scala`

* install jruby

  `brew install jruby`

* install ant

  `brew install ant`

* download scalacheck

  `wget http://scalacheck.googlecode.com/files/scalacheck_2.8.1-1.8.jar`

Building Spellbook
======================================

* spellbook-core

  `ant`

Running tests
======================================

* scalacheck

  `./bin/check.sh`

* plain old println test

  `./bin/test.sh`

* ruby to scala and scala to ruby test

  `./bin/ruby.sh`
