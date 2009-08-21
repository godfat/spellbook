
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
    case h:       Health => create(      health = _1 + h)
    case m:         Mana => create(        mana = _2 + m)
    case e:       Energy => create(      energy = _3 + e)
    case v:        Vigor => create(       vigor = _4 + v)
    case s:     Strength => create(    strength = _5 + s)
    case c: Constitution => create(constitution = _6 + c)
    case i:  Imagination => create( imagination = _7 + i)
    case w:         Will => create(        will = _8 + w)
    case a:      Agility => create(     agility = _9 + a)
  }

  def -(property: Property): State = property match{
    case h:       Health => create(      health = _1 - h)
    case m:         Mana => create(        mana = _2 - m)
    case e:       Energy => create(      energy = _3 - e)
    case v:        Vigor => create(       vigor = _4 - v)
    case s:     Strength => create(    strength = _5 - s)
    case c: Constitution => create(constitution = _6 - c)
    case i:  Imagination => create( imagination = _7 - i)
    case w:         Will => create(        will = _8 - w)
    case a:      Agility => create(     agility = _9 - a)
  }

  def create(health: Health = _1, mana: Mana = _2, energy: Energy = _3, vigor: Vigor = _4,
             strength: Strength = _5, constitution: Constitution = _6,
             imagination: Imagination = _7, will: Will = _8,
             agility: Agility = _9) =
      State(health, mana, energy, vigor,
            strength, constitution, imagination, will,
            agility)
}
