
package org.spbk

object StateUnit extends State(Health(0), Mana(0), Energy(0), Vigor(0),
                               Strength(0), Constitution(0), Imagination(0), Will(0),
                               Agility(0))

abstract class Unit
{
  type This <: Unit
  val create: State => This

  val     name: String
  val elements: List[Element]
  val    state: State

  def +(property: Property): This = create(state + property)
  def -(property: Property): This = create(state - property)

  def affect(that: Unit): (Ampify, Absorb) =
    (for(x <- this.elements; y <- that.elements) yield(x, y)).
      map( (z: (Element, Element)) => z._1(z._2) ).
        foldRight( (Ampify(), Absorb()) )(
          (e: Effect, r: (Ampify, Absorb)) => e match{
            case amp: Ampify => (r._1 + amp, r._2)
            case abs: Absorb => (r._1, r._2 + abs)
          }
        )
}
