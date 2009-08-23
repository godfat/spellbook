
package org.godfat.sb

import scala.collection.immutable.TreeMap

case class Map(val width: Int, val height: Int, val blocks: TreeMap[Int, Block]){
  def near_by(b: Block, n: Int = 1): List[Block] = {
    // TODO: implement a algo to get n > 1 result
    ( blocks.get(b.index) :: up_left(b) :: up_right(b) :: right(b) ::
                      down_right(b) :: down_left(b) :: left(b) :: Nil ).
        filter(_.isDefined).map(_.get)
  }

  def      left (b: Block): Option[Block] = blocks.get(b.index - 1)
  def      right(b: Block): Option[Block] = blocks.get(b.index + 1)
  def   up_left (b: Block): Option[Block] = blocks.get(b.index - width - 1)
  def   up_right(b: Block): Option[Block] = blocks.get(b.index - width)
  def down_left (b: Block): Option[Block] = blocks.get(b.index + width)
  def down_right(b: Block): Option[Block] = blocks.get(b.index + width + 1)
}
