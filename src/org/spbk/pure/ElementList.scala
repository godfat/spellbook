// 
// package org.spbk.pure
// 
// import scala.collection.immutable.TreeMap
// import scala.collection.immutable.LinearSeq
// 
// case class ElementList(list: List[Element]) extends LinearSeq[Element]{
//   def compact: List[Element] = list.foldRight(TreeMap.empty: TreeMap[String, Element])(
//     (element: Element, map: TreeMap[String, Element]) =>
//       if(map.contains(element.name))
//         map.update(element.name, element + map(element.name))
//       else
//         map.update(element.name, element)
//   ).values.toList
// }
