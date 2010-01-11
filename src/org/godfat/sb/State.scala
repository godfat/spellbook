
package org.godfat.sb

case class State( health: Health, mana: Mana, energy: Energy, vigor: Vigor,
                  strength: Strength, constitution: Constitution,
                  imagination: Imagination, will: Will,
                  agility: Agility )
{
  def +(property: Property): State = property match{
    case h:       Health => create(      health =       health + h)
    case m:         Mana => create(        mana =         mana + m)
    case e:       Energy => create(      energy =       energy + e)
    case v:        Vigor => create(       vigor =        vigor + v)
    case s:     Strength => create(    strength =     strength + s)
    case c: Constitution => create(constitution = constitution + c)
    case i:  Imagination => create( imagination =  imagination + i)
    case w:         Will => create(        will =         will + w)
    case a:      Agility => create(     agility =      agility + a)
  }

  def -(property: Property): State = property match{
    case h:       Health => create(      health =       health - h)
    case m:         Mana => create(        mana =         mana - m)
    case e:       Energy => create(      energy =       energy - e)
    case v:        Vigor => create(       vigor =        vigor - v)
    case s:     Strength => create(    strength =     strength - s)
    case c: Constitution => create(constitution = constitution - c)
    case i:  Imagination => create( imagination =  imagination - i)
    case w:         Will => create(        will =         will - w)
    case a:      Agility => create(     agility =      agility - a)
  }

  def create(health: Health = health, mana: Mana = mana, energy: Energy = energy, vigor: Vigor = vigor,
             strength: Strength = strength, constitution: Constitution = constitution,
             imagination: Imagination = imagination, will: Will = will,
             agility: Agility = agility) =
      State(health, mana, energy, vigor,
            strength, constitution, imagination, will,
            agility)
}
