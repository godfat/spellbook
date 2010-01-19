
package org.spbk

package object prelude{
  type   Maybe[+A] = Option[A]
  type    Just[+A] =   Some[A]
   val    Just     =   Some
   val Nothing     =   None
}
