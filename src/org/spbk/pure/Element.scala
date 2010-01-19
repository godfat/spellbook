
package org.spbk.pure

abstract class Element(val name: String){
  val pt: Int

  def apply(e: Element): Effect = (this, e) match{
    case (e0: Earth, e1: Earth) => absorb(e0.pt, e1.pt)
    case (e0: Water, e1: Water) => absorb(e0.pt, e1.pt)
    case (e0: Fire,  e1:  Fire) => absorb(e0.pt, e1.pt)
    case (e0:  Air,  e1:   Air) => absorb(e0.pt, e1.pt)

    case (e0: Earth, e1: Water) => ampify(e0.pt, e1.pt)
    case (e0: Water, e1:  Fire) => ampify(e0.pt, e1.pt)
    case (e0:  Fire, e1:   Air) => ampify(e0.pt, e1.pt)
    case (e0:   Air, e1: Earth) => ampify(e0.pt, e1.pt)

    case (e0: Earth, e1:   Air) => weaken(e0.pt, e1.pt)
    case (e0: Water, e1: Earth) => weaken(e0.pt, e1.pt)
    case (e0:  Fire, e1: Water) => weaken(e0.pt, e1.pt)
    case (e0:   Air, e1:  Fire) => weaken(e0.pt, e1.pt)

    case (e0: Light, e1: Light) => absorb(e0.pt, e1.pt)
    case (e0: Light,         _) => ampify(e0.pt,  e.pt)
    case (        _, e1: Light) => ampify(   pt, e1.pt)

    case _                      => ampify( 0,  0)
  }

  def absorb(p0: Int, p1: Int) = Absorb(+(p0 + p1))
  def ampify(p0: Int, p1: Int) = Ampify(+(p0 + p1))
  def weaken(p0: Int, p1: Int) = Ampify(-(p0 + p1))
}

case class Earth(pt: Int) extends Element("Earth")
case class Water(pt: Int) extends Element("Water")
case class  Fire(pt: Int) extends Element( "Fire")
case class   Air(pt: Int) extends Element(  "Air")
case class Light(pt: Int) extends Element("Light")

object Earth extends TraitElement[Earth]{ val create = Earth(_) }
object Water extends TraitElement[Water]{ val create = Water(_) }
object  Fire extends TraitElement[ Fire]{ val create =  Fire(_) }
object   Air extends TraitElement[  Air]{ val create =   Air(_) }
object Light extends TraitElement[Light]{ val create = Light(_) }
