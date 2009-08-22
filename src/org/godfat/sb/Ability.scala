
package org.godfat.sb

abstract class Ability
case class Move()         extends Ability
case class Wait()         extends Ability
case class MeleeAttack()  extends Ability
case class RangedAttack() extends Ability
