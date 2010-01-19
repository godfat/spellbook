
import org.scalacheck.Prop.forAll
import org.scalacheck._

import org.spbk.pure._

object CheckElement{
  val gen = Gen.oneOf(Earth.Innate, Earth.Acquired,
                      Water.Innate, Water.Acquired,
                       Fire.Innate,  Fire.Acquired,
                        Air.Innate,   Air.Acquired,
                      Light.Innate, Light.Acquired)

  implicit def arbEle: Arbitrary[Element] = Arbitrary(gen)

  def check =
    forAll{ (a: Element, b: Element) =>
      a(b) == (
        if(a.name == b.name){
          Absorb(a.pt + b.pt)
        }
        else{
          (a, b) match{
            case (e0: Earth, e1: Water) => Ampify(a.pt + b.pt)
            case (e0: Water, e1:  Fire) => Ampify(a.pt + b.pt)
            case (e0:  Fire, e1:   Air) => Ampify(a.pt + b.pt)
            case (e0:   Air, e1: Earth) => Ampify(a.pt + b.pt)

            case (e0: Earth, e1:   Air) => Ampify(- a.pt - b.pt)
            case (e0: Water, e1: Earth) => Ampify(- a.pt - b.pt)
            case (e0:  Fire, e1: Water) => Ampify(- a.pt - b.pt)
            case (e0:   Air, e1:  Fire) => Ampify(- a.pt - b.pt)

            case (e0: Light,         _) => Ampify(a.pt + b.pt)
            case (        _, e1: Light) => Ampify(a.pt + b.pt)

            case                      _ => Ampify(0)
          }
        }
      )
    }.check
}

CheckElement.check
