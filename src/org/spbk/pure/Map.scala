
package org.spbk.pure

import scala.collection.immutable.TreeMap
import scala.collection.immutable.TreeSet

case class Map(val width: Int, val height: Int, val blocks: TreeMap[Int, Block]){
  def nearby(index: Int, step: Int = 1): List[Block] = blocks.get(index) match{
    case Some(b: Block) => bfs(List((step, b)))
    case None           => error("invalid block index: " + index.toString)
  }

  def      left (b: Block): Option[Block] = blocks.get(      left_index(b.index))
  def      right(b: Block): Option[Block] = blocks.get(     right_index(b.index))
  def   up_left (b: Block): Option[Block] = blocks.get(   up_left_index(b.index))
  def down_left (b: Block): Option[Block] = blocks.get( down_left_index(b.index))
  def   up_right(b: Block): Option[Block] = blocks.get(  up_right_index(b.index))
  def down_right(b: Block): Option[Block] = blocks.get(down_right_index(b.index))

  def       left_index(index: Int): Int = if( index    % width == 0) -1 else index - 1
  def      right_index(index: Int): Int = if((index+1) % width == 0) -1 else index + 1
  def    up_left_index(index: Int): Int = left_index(  up_right_index(index))
  def  down_left_index(index: Int): Int = left_index(down_right_index(index))
  def   up_right_index(index: Int): Int = index - width + (index / width % 2)
  def down_right_index(index: Int): Int = index + width + (index / width % 2)

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
