
package org.godfat.sb

object TerrainState extends State(Health(999), Mana(0), Energy(0), Vigor(0),
                                  Strength(0), Constitution(99), Imagination(0), Will(99),
                                  Agility(0))

abstract class Terrain(     name: String,
                        elememts: List[Element],
                           state: State)
         extends Unit(name, elememts, state)
{
  def this(name: String, elements: List[Element]) =
      this(name, elements, TerrainState)
}

case class Road(state: State) extends
  Terrain("Road", Nil, state)
{
  type  This = Road
  val create = Road(_)
  def this() = this(TerrainState)
}

case class Forest(state: State) extends
  Terrain("Forest", List(Earth.Small, Earth.Small))
{
  type  This = Forest
  val create = Forest(_)
  def this() = this(TerrainState)
}

case class River(state: State) extends
  Terrain("River",  List(Water.Small, Water.Small))
{
  type  This = River
  val create = River(_)
  def this() = this(TerrainState)
}

case class Lava(state: State) extends
  Terrain("Lava",   List( Fire.Small,  Fire.Small))
{
  type  This = Lava
  val create = Lava(_)
  def this() = this(TerrainState)
}

case class Plains(state: State) extends
  Terrain("Plains", List(  Air.Small,   Air.Small))
{
  type  This = Plains
  val create = Plains(_)
  def this() = this(TerrainState)
}
