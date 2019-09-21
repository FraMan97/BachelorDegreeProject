package builderFormulaWff

import java.io.FileReader

import jtabwbx.problems.JTabWbSimpleProblemReader
import jtabwbx.prop.basic.FormulaType
import jtabwbx.prop.formula.{Formula, FormulaFactory}
import jtabwbx.prop.parser.PropositionalFormulaParser
import formulaWff._

/**
  * Definisce un builder che, dato il nome di un file, costruisce la formula letta al suo interno,
  * trasformandola da stringa ad albero binario rappresentante la formula proposizionale.
  * Il formato del file deve essere:
  * //riga irrilevante
  * File     : //nome del file
  * Status   : unprovable/provable
  * //riga irrilevante
  * formula (per esempio:   ~((~A & B) <=> (~C | (D -> A))))
  *
  * @param filename definisce il nome del file
  */
class BuilderFormulaWff(filename: String) {
  private val formula: String = new JTabWbSimpleProblemReader()
    .read(new FileReader(filename)).getConjecture

  private val treeFormula: Formula = {
    if (formula != null)
      new FormulaFactory().buildFrom(new PropositionalFormulaParser()
        .parse(formula))
    else
      null
  }

  /**
    * Metodo che costruisce l'albero binario che rappresenta la formula proposizionale.
    * @return restituisce l'albero binario che rappresenta la formula proposizionale.
    */
  def build(): FormulaWff = {

    def iter(subFormula: Formula): FormulaWff =
      subFormula.getFormulaType match {
      case FormulaType.ATOMIC_WFF =>
        Var(subFormula.toString)
      case FormulaType.AND_WFF =>
        And(iter(subFormula.immediateSubformulas()(0)),
        iter(subFormula.immediateSubformulas()(1)))
      case FormulaType.OR_WFF =>
        Or(iter(subFormula.immediateSubformulas()(0)),
        iter(subFormula.immediateSubformulas()(1)))
      case FormulaType.IMPLIES_WFF =>
        Implies(iter(subFormula.immediateSubformulas()(0)),
        iter(subFormula.immediateSubformulas()(1)))
      case FormulaType.EQ_WFF =>
        And(Implies(iter(subFormula.immediateSubformulas()(0)),
        iter(subFormula.immediateSubformulas()(1))),
        Implies(iter(subFormula.immediateSubformulas()(1)),
          iter(subFormula.immediateSubformulas()(0))))
      case FormulaType.NOT_WFF =>
        Not(iter(subFormula.immediateSubformulas()(0)))
    }

    if (treeFormula == null)
      null
    else
      iter(treeFormula)
  }

}
