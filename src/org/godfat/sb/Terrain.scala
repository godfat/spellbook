
package org.godfat.sb

object StateTerrain extends State(Health(999), Mana(0), Energy(0), Vigor(0),
                                  Strength(0), Constitution(99), Imagination(0), Will(99),
                                  Agility(0))

abstract class Terrain( override val     name: String        = "Terrain",
                        override val elements: List[Element] = Nil,
                        override val    state: State         = StateTerrain )
         extends Unit(name, elememts, state)
{
  // def walk_over(creature: Creature): Creature
  def stay_here(creature: Creature): (Creature, This) =
    (creature + state.energy, this - state.energy)
}

case class Road(override val state: State = StateTerrain) extends
  Terrain("Road", Nil, state)
{
  type  This = Road
  val create = Road(_)
}

case class Forest(override val state: State = StateTerrain) extends
  Terrain("Forest", List(Earth.Small, Earth.Small))
{
  type  This = Forest
  val create = Forest(_)
}

case class River(override val state: State = StateTerrain) extends
  Terrain("River",  List(Water.Small, Water.Small))
{
  type  This = River
  val create = River(_)
}

case class Lava(override val state: State = StateTerrain) extends
  Terrain("Lava",   List( Fire.Small,  Fire.Small))
{
  type  This = Lava
  val create = Lava(_)
}

case class Plains(override val state: State = StateTerrain) extends
  Terrain("Plains", List(  Air.Small,   Air.Small))
{
  type  This = Plains
  val create = Plains(_)
}
