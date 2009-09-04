
package org.godfat.sb

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

case class Road(val state: State = StateTerrain) extends Terrain("Road")
{
  type  This = Road
  val create = Road(_)
}

case class Forest(val state: State = StateTerrain) extends
  Terrain("Forest", List(Earth.Small, Earth.Small))
{
  type  This = Forest
  val create = Forest(_)
}

case class River(val state: State = StateTerrain) extends
  Terrain("River",  List(Water.Small, Water.Small))
{
  type  This = River
  val create = River(_)
}

case class Lava(val state: State = StateTerrain) extends
  Terrain("Lava",   List( Fire.Small,  Fire.Small))
{
  type  This = Lava
  val create = Lava(_)
}

case class Plains(val state: State = StateTerrain) extends
  Terrain("Plains", List(  Air.Small,   Air.Small))
{
  type  This = Plains
  val create = Plains(_)
}
