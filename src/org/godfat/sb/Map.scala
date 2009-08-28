
package org.godfat.sb

import scala.collection.immutable.TreeMap
import scala.collection.immutable.TreeSet

case class Map(val width: Int, val height: Int, val blocks: TreeMap[Int, Block]){
  def nearby(index: Int, step: Int = 1): List[Block] = blocks.get(index) match{
    case Some(b: Block) => bfs(List((step, b)))
    case None           => error("invalid block index: " + index.toString)
  }

  def      left (b: Block): Option[Block] = blocks.get(b.index - 1)
  def      right(b: Block): Option[Block] = blocks.get(b.index + 1)
  def   up_left (b: Block): Option[Block] = blocks.get(b.index - width - 1)
  def   up_right(b: Block): Option[Block] = blocks.get(b.index - width)
  def down_left (b: Block): Option[Block] = blocks.get(b.index + width)
  def down_right(b: Block): Option[Block] = blocks.get(b.index + width + 1)

  private def bfs(not_traveled: List[(Int, Block)],
                      traveled: TreeSet[Block] =
                    TreeSet.empty[Block](Ordering.ordered[Block])): List[Block] =
    not_traveled match{
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

object Map{
  def create(width: Int, height: Int, f: Int => Block): Map =
    Map(width, height, List.range(0, width * height + 1).foldRight(TreeMap[Int, Block]())(
      (i: Int, t: TreeMap[Int, Block]) => t.insert(i, f(i))))
}
