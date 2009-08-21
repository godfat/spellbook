
package org.godfat.sb

abstract class Terrain(     name: String,
                        elememts: List[Element],
                           state: State)
         extends Unit(name, elememts, state)
{
  def this(name: String, elements: List[Element]) =
      this(name, elements,
             State(Health(999), Mana(0), Energy(0), Vigor(0),
                   Strength(0), Constitution(99), Imagination(0), Will(99),
                   Agility(0)))
}

case class   Road() extends Terrain(  "Road", Nil)
case class Forest() extends Terrain("Forest", List(Earth.Small, Earth.Small))
case class  River() extends Terrain( "River", List(Water.Small, Water.Small))
case class   Lava() extends Terrain(  "Lava", List( Fire.Small,  Fire.Small))
case class Plains() extends Terrain("Plains", List(  Air.Small,   Air.Small))
