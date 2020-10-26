package com.shotexa
package mutable

import collection.mutable.{Map => MutMap}

class Trie(elements: String*) {
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
            case None => return false
        }
    }

    true
  }

}
