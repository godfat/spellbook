
require '/usr/local/Cellar/scala/2.8.1/libexec/lib/scala-library.jar'
require 'build/lib/spellbook-core.jar'

require 'java'
java_import 'scala.collection.immutable.List'

%w[ Creature Fire State Health Mana Energy Vigor Strength Constitution
    Imagination Will Agility ].each{ |klass|
      java_import "org.spbk.pure.#{klass}"
    }

%w[ Footman AttackMelee ].each{ |klass|
      java_import "org.spbk.prelude.#{klass}"
    }

def cons list, value
  list.send(:'$colon$colon', value)
end

def array2list array
  array.reverse.inject(List.empty){ |r, i|
    cons(r, i)
  }
end

RFootman = Creature.new("RFootman", array2list([Fire.innate]),
                        State.new(Health.new(100),
                                  Mana.new(10),
                                  Energy.new(50),
                                  Vigor.new(50),
                                  Strength.new(40),
                                  Constitution.new(20),
                                  Imagination.new(10),
                                  Will.new(10),
                                  Agility.new(15)),
                          array2list([AttackMelee.new]),
                          List.empty)

class << RFootman
  alias_method :-, :'$minus'
end

$health = (RFootman - Health.new(15)).state.health.pt

puts 85 == $health if $PROGRAM_NAME == 'src/org/spbk/rb/core-set.rb'
