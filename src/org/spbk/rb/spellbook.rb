
$LOAD_PATH.unshift(File.expand_path("#{__FILE__}/..")).uniq!

require 'java'

require '/usr/local/Cellar/scala/2.8.1/libexec/lib/scala-library.jar'
require 'build/lib/spellbook-core.jar'

java_import 'scala.collection.immutable.List'

%w[ Creature Fire State Health Mana Energy Vigor Strength Constitution
    Imagination Will Agility ].each{ |klass|
      java_import "org.spbk.pure.#{klass}"
    }

%w[ Footman AttackMelee ].each{ |klass|
      java_import "org.spbk.prelude.#{klass}"
    }

module Spellbook
  module_function
  def cons list, value
    list.send(:'$colon$colon', value)
  end

  def array2list array
    array.reverse.inject(List.empty){ |r, i|
      cons(r, i)
    }
  end
end

Spbk = Spellbook

require 'spellbook/core/creature/footman'

$health = (RFootman - Health.new(15)).state.health.pt

puts 85 == $health if $PROGRAM_NAME == 'src/org/spbk/rb/core-set.rb'
