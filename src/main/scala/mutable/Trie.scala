package com.shotexa
package mutable

import collection.mutable.{Map => MutMap}

class Trie(elements: String*) {

  class Node(
      var isWord: Boolean = false,
      val children: MutMap[Char, Node] = MutMap.empty
  )

  elements.foreach(add)
  lazy val root = new Node

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

  def substringsOf(word: String): Set[String] = {
    var current = Option(root)
    val set     = Set.newBuilder[Int]

    for ((char, index) <- word.zipWithIndex if current.nonEmpty) {
      if (current.exists(_.isWord)) set += index
      current = current.get.children.get(char)

    }
    if (current.exists(_.isWord)) set += word.length

    set.result().map(word.slice(0, _))

  }

  def stringsStartingWith(prefix: String): Set[String] = {
    var current = Option(root)
    for (char <- prefix if current.nonEmpty)
      current = current.get.children.get(char)

    if (current.isEmpty) Set.empty[String]

    val set = Set.newBuilder[String]

    def walk(node: Node, path: Array[Char]): Unit = {
      if (node.isWord) set += prefix + path.mkString
      for ((k, v) <- node.children) walk(v, path :+ k)
    }

    walk(current.get, Array.empty[Char])

    set.result()
  }

}
