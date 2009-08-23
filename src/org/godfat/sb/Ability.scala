
package org.godfat.sb

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
  override def consume(from: Block): Block =
    Block( from.index, from.terrain, from.creature - Vigor(10) )

  override def apply(from: Block, to: Block) = {
    val damage: Int = from.creature.state.strength.pt - to.creature.state.constitution.pt
    Block( to.index, to.terrain, to.creature - Health(damage) )
  }
}

case class RangedAttack() extends AbilityActive

case class Swimming()     extends AbilityPassive
