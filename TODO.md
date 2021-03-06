
# spellbook TODO list

## reference

- Vantage Master (Japan) (Windows)
- Magic: The Gathering (TCG)
- Estiah (Web)
- Infinity Wars (Windows)
- War of Omens (Flash)
- Dungeon Clash (Mobile)

## flow

0. upkeep phase
1. player select skill
2. use targeting features to filter context (restore context afterwards)
3. player select target
4. use action feature to filter context
5. use player selected skill to create action (effects)
6. use ability to update action (effects)
7. apply the action (effects)
8. end of turn

# distribution

- core (jar) = ext0 (gem)
- ext1
- ext2
- ext3

# libraries

-    [Game Engine](http://lwjgl.org/)
- [2D Game Engine](http://slick.cokeandcode.com/)
-            [GUI](http://www.fenggui.org/)

# others

* rename walk_over to ?
* implement River, Lava, etc. specific terrain effect on put_on.
* implement Power class, wrap a single output for Ability.
* implement AbilityActiveSimple, to ease implementing
  simple ability like MeleeAttack, RangedAttack, etc.
  let you write something like...

      case class MeleeAttack() extends AbilityActiveSimple(
        Strength, Constitution, Health, Range(1), AoE(0)
      )

* implement elements effect.
* implement maximum property (e.g. Health) via CreatureMap lookup
* locale and translation

* Core-Set:
  - Earth:
    - Creature:
      - Rat (E)     Walking, Melee
      - Rabbit (Ew) Walking, Melee
      - Cow (Eea)   Walking, Melee
    - Sorcery:
      - Clay (E)
      - Earthquake (Ee)
      - Meteor (Eef)

  - Water:
    - Creature:
      - Jellyfish (W) Walking, Swimming, Melee
      - Octopus (Wf)  Walking, Swimming, Melee
      - Lobster (Www) Walking, Swimming, Melee
    - Sorcery:
      - Raining (W)
      - ?
      - Hailstorm (Wwa)

  - Fire:
    - Creature:
      - Bug (F)     Walking, Melee
      - Fox (Ff)    Walking, Melee
      - Camel (Faa) Walking, Melee
    - Sorcery:
      - Heating (F)
      - Burn (Ff)
      - ?

  - Air:
    - Creature:
      - Sparrow (A)     Walking, Flying, Melee
      - Bat (Ae)        Walking, Flying, Melee
      - Dragonfly (Aaw) Walking, Flying, Melee
    - Sorcery:
      - Breeze (A)
      - ?
      - Thunderstorm (Aww)

  - Light:
    - Creature:
      - ?
      - ?
      - ?
    - Sorcery:
      - Moonlight (L)
      - Rainbows (Lw)
      - ?

  - None:
    - Creature:
      - ?
      - ?
      - ?
    - Sorcery:
      - ?
      - ?
      - ?

* Core-Ext:
  - Earth:
    - Creature:
      - Slime (E)
      - Yeti (Ew)
      - Golem (Eee)
    - Sorcery:
      - ?
      - ?
      - ?

  - Water:
    - Creature:
      - ?
      - ?
      - ?
    - Sorcery:
      - Aquaball
      - Ice Spikes
      - ?

  - Fire:
    - Creature:
      - ?
      - ?
      - Dragon Whelp (Ffa)
    - Sorcery:
      - ?
      - Fireball (Ff)
      - ?

  - Air:
    - Creature:
      - ?
      - ?
      - ?
    - Sorcery:
      - ?
      - Lightning Strike (Aa)
      - ?

  - Light:
    - Creature:
      - ?
      - ?
      - ?
    - Sorcery:
      - ?
      - Crepuscular Rays (Ll)
      - ?

  - None:
    - Creature:
      - Wisp
      - ?
      - ?
    - Sorcery:
      - ?
      - ?
      - ?
