
package org.spbk.pure

import org.spbk.prelude._

abstract class Skill{
  def activate(map: Map, from: Block, to: Block): (Block, List[Block]) =
    (consume(from), select(map, to).map(apply(from, _)))

  // def apply(context: Context, sub: Creature, obj: Block): Action

  def consume(from: Block): Block = from
  def  select(map: Map, to: Block): List[Block] = List(to)
  def   apply(from: Block, to: Block): Block = to
}

abstract class SkillSimple[Cost <: Property, Damage <: Property] extends Skill{
  def   cost(fc: Creature): Cost
  def damage(fc: Creature, tc: Creature): Damage

  override def consume(from: Block): Block = from.creature match{
    case Just(c: Creature) => Block( from.index, from.terrain, Just(c - cost(c)) )
    case _                 => sys.error("no creature for consume")
  }

  override def apply(from: Block, to: Block): Block = (from.creature, to.creature) match{
    case (Just(fc: Creature), Just(tc: Creature)) =>
      Block( to.index, to.terrain, Just(tc - damage(fc, tc)) )

    case _                 => sys.error("missing creature(s)")
  }
}
