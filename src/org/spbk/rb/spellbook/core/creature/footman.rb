
Footman = Creature.new("Footman", Spbk.array2list([Fire.innate]),
                        State.new(Health.new(100),
                                  Mana.new(10),
                                  Energy.new(50),
                                  Vigor.new(50),
                                  Strength.new(40),
                                  Constitution.new(20),
                                  Imagination.new(10),
                                  Will.new(10),
                                  Agility.new(15)),
                          Spbk.array2list([AttackMelee.new]),
                          List.empty)

class << Footman
  alias_method :-, :'$minus'
end
