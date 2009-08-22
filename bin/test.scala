
// type ObjectId = Int
// type Env = TreeMap[ObjectId, Unit]
// type Loc = (Int, Int)

import org.godfat.sb._

println(Fire.Large(Water.Small))
println(Footman.name)
println(Footman - Health(10))
println(Footman.state.health - Health(5))
println(River() - Health(50))

println()

val river: Terrain = River() + Energy(10)
println(river.stay_here(Footman))
