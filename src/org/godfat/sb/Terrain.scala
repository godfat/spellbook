
package org.godfat.sb

object StateTerrain extends State(Health(999), Mana(0), Energy(0), Vigor(0),
                                  Strength(0), Constitution(99), Imagination(0), Will(99),
                                  Agility(0))

abstract class Terrain(     name: String        = "Terrain",
                        elememts: List[Element] = Nil,
                           state: State         = StateTerrain )
         extends Unit(name, elememts, state)

case class Road(state: State = StateTerrain) extends
  Terrain("Road", Nil, state)
{
  type  This = Road
  val create = Road(_)
}

case class Forest(state: State = StateTerrain) extends
  Terrain("Forest", List(Earth.Small, Earth.Small))
{
  type  This = Forest
  val create = Forest(_)
}

case class River(state: State = StateTerrain) extends
  Terrain("River",  List(Water.Small, Water.Small))
{
  type  This = River
  val create = River(_)
}

case class Lava(state: State = StateTerrain) extends
  Terrain("Lava",   List( Fire.Small,  Fire.Small))
{
  type  This = Lava
  val create = Lava(_)
}

case class Plains(state: State = StateTerrain) extends
  Terrain("Plains", List(  Air.Small,   Air.Small))
{
  type  This = Plains
  val create = Plains(_)
}
