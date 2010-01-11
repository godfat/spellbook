
// type ObjectId = Int
// type Env = TreeMap[ObjectId, Unit]
// type Loc = (Int, Int)
// type Path = List[Block]
// type Map = List[Block]

import org.godfat.sb._
import org.godfat.sb.prelude._

import scala.collection.immutable.TreeMap

println(Fire.Innate(Water.Innate)) // Ampify(-200)
println(Footman.name) // Footman
println((Footman - Health(10)).state.health) // Health(90)
println(Footman.state.health - Health(5)) // Health(95)
println((River() - Health(50)).state.health) // Health(949)

val b = Block(0, River() + Energy(10), Nothing).put_on(Footman)
println(b.terrain.state.energy, b.creature.get.state.energy) // (Energy(0),Energy(60))

val from = Block(0, River(), Just(Footman))
val to   = Block(1, River(), Just(Footman))
val map  = Map(2, 1, TreeMap(0 -> from, 1 -> to))

val (bb, lb) = AttackMelee().activate(map, from, to)
println((bb.creature.get.state.vigor, lb.head.creature.get.state.health)) // (Vigor(40),Health(80))

val map2 = Map.create(5, 5, Block(_, River(), Nothing))

println(map2.nearby(12, 2).map(_.index))
// List(1, 2, 3, 5, 6, 7, 8, 10, 11, 12, 13, 14, 15, 16, 17, 18, 21, 22, 23)
//  00     (01)    (02)    (03)     04
//     (05)    (06)    (07)    (08)     09
// (10)    (11)    (12)    (13)    (14)
//     (15)    (16)    (17)    (18)     19
//  20     (21)    (22)    (23)     24

println(map2.nearby(13, 2).map(_.index))
// List(2, 3, 4, 6, 7, 8, 9, 11, 12, 13, 14, 16, 17, 18, 19, 22, 23, 24)
//  00      01     (02)    (03)    (04)
//      05     (06)    (07)    (08)    (09)
//  10     (11)    (12)    (13)    (14)
//      15     (16)    (17)    (18)    (19)
//  20      21     (22)    (23)    (24)

println(map2.nearby(1, 2).map(_.index))
// List(0, 1, 2, 3, 5, 6, 7, 10, 11, 12)
// (00)    (01)    (02)    (03)     04
//     (05)    (06)    (07)     08      09
// (10)    (11)    (12)     13      14
//      15      16      17      18      19
//  20      21      22      23      24

println(CreatureMap("Footman") == Footman) // true

println(Absorb(10) + Absorb(-10)) // Absorb(0)
println(Footman.affect(Footman)) // (Ampify(0),Absorb(200))
println(Footman.affect(River())) // (Ampify(-200),Absorb(0))

// walk_to :: Path -> Creature -> Path
// walk_to path creature =
//
//   foldr (\terrain (result, creature) ->
//             (new_result, new_creature) where
//              new_terrain, new_creature = terrain.walk_over creature
//              new_result = new_terrain : result
//
//         [] path
