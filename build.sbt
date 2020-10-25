ThisBuild / organization := "com.shotexa"
ThisBuild / scalaVersion := "2.13.3"
ThisBuild / name := "trie"
ThisBuild / scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  "-language:_",
  "-unchecked",
  "-Xfatal-warnings"
)
Global / onChangedBuildSource := ReloadOnSourceChanges