
package object prelude_sb{
  type   Maybe[+A] = Option[A]
  type    Just[+A] =   Some[A]
   val    Just     =   Some
   val Nothing     =   None
}
