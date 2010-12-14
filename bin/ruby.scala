
import org.spbk.pure._
import org.spbk.prelude._

val jruby = new org.jruby.embed.ScriptingContainer
// wait for jruby 1.6
// jruby.setCompatVersion(org.jruby.CompatVersion.RUBY1_9);

jruby.runScriptlet("require 'src/org/spbk/rb/spellbook'")
jruby.runScriptlet("$input = 20")

println("20" == jruby.runScriptlet("$input").toString)
println("85" == jruby.runScriptlet("$health").toString)
println("10" == jruby.runScriptlet("Footman.state.mana.pt").toString)

println( "5" == jruby.runScriptlet("(Footman - Mana.new(5)).state.mana.pt").toString)
println(  5  == jruby.runScriptlet("(Footman - Mana.new(5)).state.mana.pt").asInstanceOf[Long])

val footman = jruby.runScriptlet("Footman").asInstanceOf[Creature]
println((footman - Mana(5)).skills == List(AttackMelee()))
