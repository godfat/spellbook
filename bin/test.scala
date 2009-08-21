
// type ObjectId = Int
// type Env = TreeMap[ObjectId, Unit]
// type Loc = (Int, Int)

import org.godfat.sb.Fire
import org.godfat.sb.Water
import org.godfat.sb.Footman
import org.godfat.sb.Health
import org.godfat.sb.River
import org.godfat.sb.TerrainState

abstract class Buff
case class Enrage() extends Buff

println(Fire.Large(Water.Small))
println(Footman.name)
println(Footman - Health(10))
println(Footman.state.health - Health(5))
println(River() - Health(50))

// abstract class Action
// case class   Move extends Action
// case class Attack extends Action
