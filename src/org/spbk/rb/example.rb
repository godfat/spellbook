
# example definition of a creature, this is a pseudo code, don't run it

# Feature#upkeep :: Context -> Context
# Feature#apply :: Context -> Context
#   Skill#apply :: Context -> Action
# Ability#apply ::  Action -> Action

                            # name
DragonTyrant = Creature.new("Dragon Tyrant",
                            # elements
                            [Fire.innate, Fire.acquired, Fire.acquired],
                            # state
                            State.new(Health.new(100),
                                      Mana.new(10),
                                      Energy.new(50),
                                      Vigor.new(50),
                                      Strength.new(40),
                                      Constitution.new(20),
                                      Imagination.new(10),
                                      Will.new(10),
                                      Agility.new(15)),
                            # skills
                            [MeleeAttack.new],
                            # features
                            [Enrage.new, Flying.new],
                            # abilities
                            [DoubleAttack.new, Trample.new])

class MeleeAttack < Skill
  def apply context
    env, subject, target = context.extract

    return [Reduce.new(subject, Vigor.new(10)),
            Reduce.new(target,
              Health.new(damage(subject.strength, target.constitution)))]
  end

  def damage str, con
    # www.timotheegroleau.com/Flash/experiments/easing_function_generator.htm
    # http://en.wikipedia.org/wiki/Box–Muller_transform
    # http://www.madeinflex.com/img/entries/2007/05/customeasingexplorer.html
    [str, (str*2 - con) * (1 - Math.exp(-0.05 - (str*2 - con)*0.05))].min
  end
end

class Enrage < Feature
  def apply context
    if context.subject.mana < 10
      return context
    else
      return context.replace(:subject =>
        context.subject - Mana.new(10) + Strength.new(5))
    end
  end
end

class Move < Skill
  def apply
  end
end

# how to implement this? flying is a feature to ignore ZoC (Zone of Control)
class Flying < Feature
  def target context
    return context
  end
end

class DoubleAttack < Ability
  def apply action
    attacked = action.effects.find{|effect| effect.kind_of?(AttackedTimes))}
    if attacked.times > 1
      return action
    else
      return action.replace(:effects => effects + [AttackAgain.new]))
      # so when scala is applying effects, if it finds that there's a
      # AttackAgain effect, that is, the wholm process should be processed
      # again, but this time it should insert an AttackedTimes(1) into
      # next effects. If there's already an AttackTimes instance, then
      # instead it should increase the number of AttackTimes.times.
    end
  end
end

class Trample < Ability
  def apply action
    if action.subject.alive?
      return action
    else
      nearbys = action.map.nearby(1, action.subject_index)
      total_damage = action.effects.sum{ |effect|
                       if   effect.kind_of?(Reduce) &&
                            effect.kind.kind_of?(Health)
                         effect.pt
                       else
                         0
                       end
                     }
      remaining_damage = total_damage - action.subject.health
      return action.replace(:effects => effects +
        nearbys.map{|nearby| Reduce.new(nearby.creature,
                                        Health.new(remaining_damage))})
    end
  end
end
