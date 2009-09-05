
package org.godfat.sb

import prelude._

abstract class Ability{
  def activate(map: Map, from: Block, to: Block): (Block, List[Block]) = {
    (consume(from), select(map, to).map(apply(from, _)))
  }

  def consume(from: Block): Block = from
  def  select(map: Map, to: Block): List[Block] = List(to)
  def   apply(from: Block, to: Block): Block = to
}

abstract class AbilitySimple[Cost <: Property, Damage <: Property] extends Ability{
  def   cost(fc: Creature): Cost
  def damage(fc: Creature, tc: Creature): Damage

  override def consume(from: Block): Block = from.creature match{
    case Just(c: Creature) => Block( from.index, from.terrain, Just(c - cost(c)) )
    case _                 => error("no creature for consume")
  }

  override def apply(from: Block, to: Block): Block = (from.creature, to.creature) match{
    case (Just(fc: Creature), Just(tc: Creature)) =>
      Block( to.index, to.terrain, Just(tc - damage(fc, tc)) )

    case _                 => error("missing creature(s)")
  }
}

trait AbilityActive  extends Ability
trait AbilityPassive extends Ability

case class Move()    extends AbilityActive
case class Wait()    extends AbilityActive

case class MeleeAttack() extends AbilitySimple[Vigor, Health] with AbilityActive{
  override def   cost(fc: Creature) = Vigor(10)
  override def damage(fc: Creature, tc: Creature) =
    Health(fc.state.strength.pt - tc.state.constitution.pt)
}

case class RangedAttack() extends AbilityActive

case class Swimming()     extends AbilityPassive
