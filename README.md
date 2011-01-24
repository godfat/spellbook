
# [Spellbook](http://spbk.org)

This is an experiment to the models of Spellbook,<br/>
a game I designed years ago, but never implemented.<br/>
It was based on VM series[[0]][VM][[1]][VMJ] from [Falcom][] and<br/>
[Magic: The Gathering&trade;][MTG] from [Wizards of the Coast][WotC].

I would import a few documents written before,<br/>
after translating them from Chinese to English,<br/>
or should i just import them and write docs in Chinese?

[falcom]:  http://en.wikipedia.org/wiki/Nihon_Falcom
[WotC]:    http://en.wikipedia.org/wiki/Wizards_of_the_Coast

# Design Reference

* [Vantage Master][VM]
* [VM Japan][VMJ]
* [Magic: The Gathering][MTG]
* [The Battle for Wesnoth][Wesnoth]

[VM]:      http://en.wikipedia.org/wiki/Vantage_Master
[VMJ]:     http://ja.wikipedia.org/wiki/VM_JAPAN
[MTG]:     http://en.wikipedia.org/wiki/Magic:_The_Gathering
[Wesnoth]: http://en.wikipedia.org/wiki/The_Battle_for_Wesnoth

# Runtime/Development Dependency

* [Scala][] 2.8+ [&#8690;][d-scala]
* [JRuby][] 1.5+ [&#8690;][d-jruby]
* [Apache Ant][ant] 1.8+ [&#8690;][d-ant]
* [ScalaCheck][scheck] 1.8+ [&#8690;][d-scheck]

[scala]:   http://en.wikipedia.org/wiki/Scala_(programming_language)
[jruby]:   http://en.wikipedia.org/wiki/JRuby
[ant]:     http://en.wikipedia.org/wiki/Apache_Ant
[scheck]:  http://en.wikipedia.org/wiki/QuickCheck#cite_note-9

[d-scala]: http://www.scala-lang.org/downloads
[d-jruby]: http://www.jruby.org/download
[d-ant]:   http://ant.apache.org/bindownload.cgi
[d-scheck]:http://code.google.com/p/scalacheck/downloads/list

# Installing on Windows

# Installing on Mac

* install scala

  `brew install scala`

* install jruby

  `brew install jruby`

* install ant

  `brew install ant`

* download scalacheck

  `wget http://scalacheck.googlecode.com/files/scalacheck_2.8.1-1.8.jar`

# Installing on Arch (Linux)

# Building Spellbook

* spellbook-core

  `ant`

# Running tests

* scalacheck

  `./bin/check.sh`

* plain old println test

  `./bin/test.sh`

* ruby to scala and scala to ruby test

  `./bin/ruby.sh`
