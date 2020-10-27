package com.shotexa
package mutable

import collection.mutable.{Map => MutMap}

class Trie(elements: String*) {

  elements.foreach(add)

  val root = new Node

  class Node(
      var isWord: Boolean = false,
      val children: MutMap[Char, Node] = MutMap.empty
  )

  def add(word: String): Trie = {
    var current = root
    
    for (char <- word)
      current = current.children.getOrElseUpdate(char, new Node)
    current.isWord = true

    this
  }

  def contains(word: String): Boolean = {
    var current = root

    for (char <- word) {
      current.children.get(char) match {
        case Some(node) => current = node
        case None       => return false
      }
    }

    current.isWord
  }

  def prefixesMatchingString(word: String): Set[String] = {
    var current = Option(root)
    val set     = Set.newBuilder[Int]

    for ((char, index) <- word.zipWithIndex if current.nonEmpty) {
      if (current.exists(_.isWord)) set += index
      current = current.get.children.get(char)

    }

    set.result().map(word.slice(0, _))

  }

}
