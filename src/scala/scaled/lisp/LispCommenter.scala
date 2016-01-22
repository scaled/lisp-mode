//
// Scaled Scala Mode - a Scaled major mode for editing Scala code
// http://github.com/scaled/scala-mode/blob/master/LICENSE

package scaled.lisp

import scaled._
import scaled.code.Commenter

/** Extends [[Commenter]] with some Lisp smarts. */
class LispCommenter extends Commenter {
  import scaled.code.CodeConfig._

  override def linePrefix  = ";"
  override def blockOpen = ";;"
  override def blockClose = ";;"
  override def blockPrefix = ";;"
  override def docPrefix   = ";;"

  // look for longer prefix first, then shorter
  override def commentDelimLen (line :LineV, col :Int) :Int = {
    if (line.matches(blockPrefixM, col)) blockPrefixM.matchLength
    else if (line.matches(linePrefixM, col)) linePrefixM.matchLength
    else 0
  }
}
