
package org.godfat.sb

import prelude_sb._

abstract class Ability{
  def activate(map: Map, from: Block, to: Block): (Block, List[Block]) = {
    (consume(from), select(map, to).map(apply(from, _)))
  }

  def consume(from: Block): Block = from
  def  select(map: Map, to: Block): List[Block] = List(to)
  def   apply(from: Block, to: Block): Block = to
}
abstract class AbilityActive  extends Ability
abstract class AbilityPassive extends Ability

case class Move()         extends AbilityActive
case class Wait()         extends AbilityActive

case class MeleeAttack()  extends AbilityActive{
  override def consume(from: Block): Block = from.creature match{
    case Just(c: Creature) => Block( from.index, from.terrain, Just(c - Vigor(10)) )
    case _                 => error("no creature for consume")
  }

  override def apply(from: Block, to: Block): Block = (from.creature, to.creature) match{
    case (Just(fc: Creature), Just(tc: Creature)) => {
      val damage: Int = fc.state.strength.pt - tc.state.constitution.pt
      Block( to.index, to.terrain, Just(tc - Health(damage)) )
    }
    case _                 => error("missing creature(s) for melee attack")
  }
}

case class RangedAttack() extends AbilityActive

case class Swimming()     extends AbilityPassive
