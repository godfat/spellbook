
import javax.script.ScriptContext
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager
import javax.script.ScriptException

val m = new ScriptEngineManager
val jruby: ScriptEngine = m.getEngineByName("jruby")

jruby.eval("require 'src/org/spbk/rb/core-set'")

val context: ScriptContext = jruby.getContext
context.setAttribute("input", "20", ScriptContext.ENGINE_SCOPE)

println("20" == jruby.eval("$input", context).toString)
println("85" == jruby.eval("$health", context).toString)
