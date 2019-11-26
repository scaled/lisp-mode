//
// Scaled Lisp Mode - a Scaled major mode for editing lisp code
// http://github.com/scaled/lisp-mode/blob/master/LICENSE

package scaled.lisp

import scaled._
import scaled.code.Indenter
import scaled.grammar._
import scaled.code.{CodeConfig, Commenter}

@Plugin(tag="textmate-grammar")
class LispGrammarPlugin extends GrammarPlugin {
  import EditorConfig._
  import CodeConfig._

  override def grammars = Map("source.lisp" -> "lisp.ndf")

  override def effacers = List(
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
}

@Major(name="lisp",
       tags=Array("code", "project", "lisp"),
       pats=Array(".*\\.lisp", ".*\\.rkt"),
       desc="A major mode for editing lisp code.")
class LispMode (env :Env) extends GrammarCodeMode(env) {

  override def dispose () :Unit = {} // nada for now

  override def langScope = "source.lisp"

  override def keymap = super.keymap.
    bind("self-insert-command", "'"); // don't auto-pair single quote

  // override def createIndenter() = new XmlIndenter(buffer, config)
  override val commenter = new LispCommenter()
}
