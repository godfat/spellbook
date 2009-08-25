
package org.godfat.sb

import scala.collection.immutable.TreeMap
import scala.collection.immutable.TreeSet

case class Map(val width: Int, val height: Int, val blocks: TreeMap[Int, Block]){
  def nearby(b: Block, step: Int = 1): List[Block] = bfs(List((step, b)))

  def      left (b: Block): Option[Block] = blocks.get(b.index - 1)
  def      right(b: Block): Option[Block] = blocks.get(b.index + 1)
  def   up_left (b: Block): Option[Block] = blocks.get(b.index - width - 1)
  def   up_right(b: Block): Option[Block] = blocks.get(b.index - width)
  def down_left (b: Block): Option[Block] = blocks.get(b.index + width)
  def down_right(b: Block): Option[Block] = blocks.get(b.index + width + 1)

  private def bfs(not_traveled: List[(Int, Block)],
                      traveled: TreeSet[Block] = TreeSet.empty[Block](Ordering.ordered[Block])):
    List[Block] = not_traveled match{
      case Nil               =>
        traveled.toList

      case ((   0, b) :: bs) =>
        bfs(bs,
            insert_check(traveled, b))

      case ((step, b) :: bs) =>
        bfs(bs ++ nearby_one(b).filterNot(traveled(_)).map((step - 1, _)),
            insert_check(traveled, b))
    }

  private def nearby_one(b: Block): List[Block] =
    ( blocks.get(b.index) ::    up_left(b) ::  up_right(b) :: right(b) ::
                             down_right(b) :: down_left(b) ::  left(b) :: Nil ).
      filter(_.isDefined).map(_.get)

  private def insert_check(traveled: TreeSet[Block], b: Block): TreeSet[Block] =
    if(traveled.contains(b)) traveled else traveled.insert(b)
}
