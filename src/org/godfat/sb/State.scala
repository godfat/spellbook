
package org.godfat.sb

case class State( health: Health, mana: Mana, energy: Energy, vigor: Vigor,
                  strength: Strength, constitution: Constitution,
                  imagination: Imagination, will: Will,
                  agility: Agility ) extends
                 (Health, Mana, Energy, Vigor,
                  Strength, Constitution, Imagination, Will,
                  Agility)(
                      health, mana, energy, vigor,
                      strength, constitution, imagination, will,
                      agility)
{
  def +(property: Property): State = property match{
    case h:       Health => State(_1 + h, _2, _3, _4, _5, _6, _7, _8, _9)
    case m:         Mana => State(_1, _2 + m, _3, _4, _5, _6, _7, _8, _9)
    case e:       Energy => State(_1, _2, _3 + e, _4, _5, _6, _7, _8, _9)
    case v:        Vigor => State(_1, _2, _3, _4 + v, _5, _6, _7, _8, _9)
    case s:     Strength => State(_1, _2, _3, _4, _5 + s, _6, _7, _8, _9)
    case c: Constitution => State(_1, _2, _3, _4, _5, _6 + c, _7, _8, _9)
    case i:  Imagination => State(_1, _2, _3, _4, _5, _6, _7 + i, _8, _9)
    case w:         Will => State(_1, _2, _3, _4, _5, _6, _7, _8 + w, _9)
    case a:      Agility => State(_1, _2, _3, _4, _5, _6, _7, _8, _9 + a)
  }

  def -(property: Property): State = property match{
    case h:       Health => State(_1 - h, _2, _3, _4, _5, _6, _7, _8, _9)
    case m:         Mana => State(_1, _2 - m, _3, _4, _5, _6, _7, _8, _9)
    case e:       Energy => State(_1, _2, _3 - e, _4, _5, _6, _7, _8, _9)
    case v:        Vigor => State(_1, _2, _3, _4 - v, _5, _6, _7, _8, _9)
    case s:     Strength => State(_1, _2, _3, _4, _5 - s, _6, _7, _8, _9)
    case c: Constitution => State(_1, _2, _3, _4, _5, _6 - c, _7, _8, _9)
    case i:  Imagination => State(_1, _2, _3, _4, _5, _6, _7 - i, _8, _9)
    case w:         Will => State(_1, _2, _3, _4, _5, _6, _7, _8 - w, _9)
    case a:      Agility => State(_1, _2, _3, _4, _5, _6, _7, _8, _9 - a)
  }
}
