import scala.util.parsing.combinator._
import Hodor._

abstract class TheEssenceOfHodor
case class HodorProgram(statementSequence: List[HodorStatement])
case class HodorStatement(state: TheEssenceOfHodor)
case class HodorStatementSeq(states: List[HodorStatement])
case class HodorVarDecl(name: String) extends TheEssenceOfHodor
case class HodorAssign(name: String, hodorVar: HodorVar) extends TheEssenceOfHodor
case class HodorAssignment(name: String, value: HodorVar)
case class HodorCodeBlock(statementSequence: List[TheEssenceOfHodor]) extends TheEssenceOfHodor
case class HodorAdd(left: HodorVar, right: HodorVar) extends TheEssenceOfHodor
case class HodorSubtract(left: HodorVar, right: HodorVar) extends TheEssenceOfHodor
case class HodorMultiply() extends TheEssenceOfHodor
case class HodorDivide() extends TheEssenceOfHodor
case class HodorIf(expr: HodorCodeBlock, thn: HodorCodeBlock, els: HodorCodeBlock) extends TheEssenceOfHodor
case class HodorElse() extends TheEssenceOfHodor
case class HodorCond() extends TheEssenceOfHodor
case class HodorGT(left: HodorVar, right: HodorVar) extends TheEssenceOfHodor
case class HodorPrint(hodorVar: HodorVar) extends TheEssenceOfHodor
case class HodorTrue() extends TheEssenceOfHodor

object HodorParser extends RegexParsers {

	override def skipWhitespace = true

	def parseProgram: Parser[HodorProgram] = "HODOR..." ~> statementSeq <~ "HODOR!" ^^ {  // returns code block change later
		case v => HodorProgram(v)
	}

	def statementSeq = statement*

	def statement: Parser[HodorStatement] = (varDecl | varAssign) ^^ { case v => HodorStatement(v) }


	def varDecl: Parser[HodorVarDecl] = "hodor" ~> ("""([A-Za-z0-9]+)""").r <~ ":)" ^^ {
		case v => HodorVarDecl(v)
	}

	def varAssign: Parser[HodorAssign] = (("""([A-Za-z0-9]+)""").r <~ "Hodor") ~ (expr <~ ":)") ^^ {
		case v ~ e => HodorAssign(v, e)
	}

	def expr: Parser[HodorString] = ("""([A-Za-z0-9]+)""").r ^^ { case v => HodorString(v) }

	/*| assign | codeBlock | ifStat | printStat*/

	//def statement: HodorVarDecl = """hodor""" ^^ { case v => HodorVarDecl(v) }
	//def statement = hodorAssign | hodorVarDecl | hodorCodeBlock | hodorIf | hodorPrint

	//def varName: Parser[String] = ("""([A-Za-z0-9]+)""") ^^ {
	//	case v => v
	//}

	/*def varVal = ("""([A-Za-z0-9]+)""") ^^ {
		case v => v
	}
		
	def hodorVarDecl: Parser[HodorVarDecl] = "hodor" ~> varName <~ ":)" ^^ { 
		case v => HodorVarDecl(v)
	}

	def hodorTrue: Parser[HodorTrue] = "hodorHODORhodor" ^^ { 
		case _ => HodorTrue() 
	}

	def hodorAssign: Parser[HodorAssign] = varName <~ "Hodor" ~> varVal ^^ {
		//case n <~ "Hodor" ~> v => HodorAssign(n, v)
		case _ => HodorAssign("HOdor" , HodorInt(5))
	}
	
	def hodorCodeBlock: Parser[HodorCodeBlock] = "HODOR..." ~> statementSeq <~ "HODOR!" ^^ { (s) => HodorCodeBlock(s) }

	def hodorIf: Parser[HodorIf] = "HODOR?" ~> hodorCodeBlock ~ hodorCodeBlock ~ hodorCodeBlock ^^ { 
		case cond ~ block1 ~ block2 => HodorIf(cond, block1, block2) 
	}*/

	// def varAssign: Parser[HodorAssign] = varName <~ "Hodor" ~> varVal <~ ":)" { }  

	// def print: Parser[HodorPrint] = new Regex("|HODOR|\s+" + varRegex)


	/*def parse (code: String) = parseAll(program, code)


	def program: Parser[List[TheEssenceOfHodor]] = (decl | assignment | codeBlock) ~ program

	def decl: Parser[List[TheEssenceOfHodor]] = "hodor" ~> varName <~ ":)"

	def assignment: Parser[List[TheEssenceOfHodor]] = varName <~ "Hodor" ~> value <~ ":)"

	//def codeBlockParser: Parser[List[TheEssenceOfHodor]] = "HODOR..." ~> codeBlock <~ "HODOR!"
	//def codeBlock: Parser[List[TheEssenceOfHodor]] =
	*/ 
}


