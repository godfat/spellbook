
// type ObjectId = Int
// type Env = TreeMap[ObjectId, Unit]
// type Loc = (Int, Int)
// type Path = List[Block]
// type Map = List[Block]

import org.godfat.sb._
import scala.collection.immutable.TreeMap

println(Fire.Large(Water.Small))
println(Footman.name)
println(Footman - Health(10))
println(Footman.state.health - Health(5))
println(River() - Health(50))

println()

val river: Terrain = River() + Energy(10)
println(river.stay_here(Footman))

println()

val from = Block(0, river, Footman)
val to   = Block(1, river, Footman)
val map  = Map(2, 1, TreeMap(0 -> from, 1 -> to))

println(MeleeAttack().activate(map, from, to))

println()

val map2 = Map(5, 5, List.range(0, 26).foldRight(TreeMap[Int, Block]())(
  (i: Int, t: TreeMap[Int, Block]) => t.insert(i, Block(i, river, Footman))))

println(map2.nearby(Block(12, river, Footman), 2).map(_.index))

// val (new_block, updated_blocks) = ability.activate(block, target)

// walk_to :: Path -> Creature -> Path
// walk_to path creature =
//
//   foldr (\terrain (result, creature) ->
//             (new_result, new_creature) where
//              new_terrain, new_creature = terrain.walk_over creature
//              new_result = new_terrain : result
//
//         [] path
