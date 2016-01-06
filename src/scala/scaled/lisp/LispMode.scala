//
// Scaled Lisp Mode - a Scaled major mode for editing lisp code
// http://github.com/scaled/lisp-mode/blob/master/LICENSE

package scaled.lisp

import scaled._
import scaled.code.Indenter
import scaled.grammar._
import scaled.code.{CodeConfig, Commenter}

object LispConfig extends Config.Defs {
  import EditorConfig._
  import CodeConfig._
  import GrammarConfig._

  // maps TextMate grammar scopes to Scaled style definitions
  val effacers = List(
    effacer("comment.line", commentStyle),
    effacer("comment.block", docStyle),
    effacer("constant", constantStyle),
    effacer("invalid", warnStyle),
    effacer("keyword", keywordStyle),
    effacer("string", stringStyle),
    effacer("variable", stringStyle),
    effacer("entity.name.function", functionStyle),
    effacer("storage", variableStyle)
  )

  val grammars = resource("lisp.ndf")(Grammar.parseNDFs)
}

@Major(name="lisp",
       tags=Array("code", "project", "lisp"),
       pats=Array(".*\\.lisp"),
       desc="A major mode for editing lisp code.")
class LispMode (env :Env) extends GrammarCodeMode(env) {

  override def dispose () {} // nada for now

  override def configDefs = LispConfig :: super.configDefs
  override def grammars = LispConfig.grammars.get
  override def effacers = LispConfig.effacers

  // override def createIndenter() = new XmlIndenter(buffer, config)
  override val commenter = new Commenter()
}
