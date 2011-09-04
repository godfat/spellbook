
$LOAD_PATH.unshift(File.expand_path("#{__FILE__}/..")).uniq!

require 'java'

require '/usr/local/Cellar/scala/2.9.1/libexec/lib/scala-library.jar'
require './build/lib/spellbook-core.jar'

java_import 'scala.collection.immutable.List'

%w[ Creature Fire State Health Mana Energy Vigor Strength Constitution
    Imagination Will Agility Property Unit ].each{ |klass|
      java_import "org.spbk.pure.#{klass}"
    }

# Footman
%w[ AttackMelee ].each{ |klass|
      java_import "org.spbk.prelude.#{klass}"
    }

class Array
  def to_list
    reverse.inject(List.empty){ |list, value|
      list.send(:'$colon$colon', value)
    }
  end
end

require 'spellbook/core/creature/footman'

$health = (Footman - Health.new(15)).state.health.pt

puts 85 == $health if $PROGRAM_NAME == 'src/org/spbk/rb/spellbook.rb'
