
package org.spbk.prelude
import org.spbk.pure._

case class AttackMelee() extends SkillSimple[Vigor, Health]{
  override def   cost(fc: Creature) = Vigor(10)
  override def damage(fc: Creature, tc: Creature) =
    Health(fc.state.strength.pt - tc.state.constitution.pt)
}
