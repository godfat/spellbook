
import javax.script.ScriptContext
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager
import javax.script.ScriptException

import org.spbk.pure._
import org.spbk.prelude._

val m = new ScriptEngineManager
val jruby: ScriptEngine = m.getEngineByName("jruby")

jruby.eval("require 'src/org/spbk/rb/core-set'")

val context: ScriptContext = jruby.getContext
context.setAttribute("input", "20", ScriptContext.ENGINE_SCOPE)

println("20" == jruby.eval("$input", context).toString)
println("85" == jruby.eval("$health", context).toString)
println("10" == jruby.eval("RFootman.state.mana.pt", context).toString)

println( "5" == jruby.eval("(RFootman - Mana.new(5)).state.mana.pt",
                           context).toString)
println(  5  == jruby.eval("(RFootman - Mana.new(5)).state.mana.pt",
                           context).asInstanceOf[Long])

val footman = jruby.eval("RFootman", context).asInstanceOf[Creature]
println((footman - Mana(5)).skills == List(AttackMelee()))
