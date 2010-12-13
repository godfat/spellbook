
package org.spbk.pure

object StateTerrain extends State(Health(999), Mana(0), Energy(0), Vigor(0),
                                  Strength(0), Constitution(99), Imagination(0), Will(99),
                                  Agility(0))

abstract class Terrain( val     name: String        = "Terrain",
                        val elements: List[Element] = Nil ) extends Unit
{
  type This <: Terrain
  // def walk_over(creature: Creature): Creature
  def put_on(creature: Creature): (This, Creature) =
    (this - state.energy, creature + state.energy)
}

case class Plains(val state: State = StateTerrain) extends
  Terrain("Plains")
{
  type  This = Plains
  val create = Plains(_)
}

case class Forest(val state: State = StateTerrain) extends
  Terrain("Forest", List(Earth.innate))
{
  type  This = Forest
  val create = Forest(_)
}

case class River(val state: State = StateTerrain) extends
  Terrain("River",  List(Water.innate))
{
  type  This = River
  val create = River(_)
}

case class Lava(val state: State = StateTerrain) extends
  Terrain("Lava",   List(Fire.innate))
{
  type  This = Lava
  val create = Lava(_)
}

case class Valley(val state: State = StateTerrain) extends
  Terrain("Valley", List(Air.innate))
{
  type  This = Valley
  val create = Valley(_)
}
