
class Property
  alias_method :+, :'$plus'
  alias_method :-, :'$minus'
end

class Unit
  alias_method :+, :'$plus'
  alias_method :-, :'$minus'
end

class FreeMelee < AttackMelee
  def cost c
    super - Vigor.new(7)
    Vigor.new(1)
  end
  def test
    5
  end
end

Footman = Creature.new("Footman", [Fire.innate].to_list,
                        State.new(Health.new(100),
                                  Mana.new(10),
                                  Energy.new(50),
                                  Vigor.new(50),
                                  Strength.new(40),
                                  Constitution.new(20),
                                  Imagination.new(10),
                                  Will.new(10),
                                  Agility.new(15)),
                          [FreeMelee.new].to_list,
                          List.empty)

p FreeMelee.new.cost(Footman).pt == 1
