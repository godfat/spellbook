
require 'scala-2.8.0.r20436-b20100111020117/lib/scala-library.jar'
require 'build/lib/spellbook-core.jar'

java_import 'scala.collection.immutable.List'
%w[ Creature Fire State Health Mana Energy Vigor Strength Constitution
    Imagination Will Agility AttackMelee ].each{ |klass|
      java_import "org.spbk.pure.#{klass}"
    }

def cons list, value
  list.send(:'$colon$colon__method', value)
end

def array2list array
  array.reverse.inject(List.empty){ |r, i|
    cons(r, i)
  }
end

$footman = Creature.new("Footman", array2list([Fire.Innate]),
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

puts $footman.send(:'$minus', Health.new(15)).state.health.pt # 85
