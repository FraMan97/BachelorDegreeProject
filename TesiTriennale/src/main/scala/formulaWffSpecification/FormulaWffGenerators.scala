package formulaWffSpecification

import formulaWff._
import org.scalacheck.Gen
import org.scalacheck.Gen.oneOf

/**
  * Definisce i generatori usati nei test delle proprieta sulle formule.
  */
object FormulaWffGenerators {

  /**
    * Definisce il generatore del nodo and
    */
  private lazy val genAnd = for {
    left <- myGenFormula
    right <- myGenFormula

  } yield And(left, right)

  /**
    * Definisce il generatore del nodo or
    */
  private lazy val genOr = for {
    left <- myGenFormula
    right <- myGenFormula

  } yield Or(left, right)

  /**
    * Definisce il generatore del nodo implies
    */
  private lazy val genImplies = for {
    left <- myGenFormula
    right <- myGenFormula

  } yield Implies(left, right)

  /**
    * Definisce il generatore del nodo not
    */
  private val genNot = for {
    son <- myGenFormula
  } yield Not(son)

  /**
    * Definisce il generatore del nodo var
    */
  private lazy val genVar = oneOf(Var("A"),Var("B"))
  /**
    * Definisce il generatore che genera l'albero binario che rappresenta una formula proposizionale
    */
  def myGenFormula: Gen[FormulaWff] =
    Gen.frequency(
      4 -> genVar,
      1 -> Gen.lzy(genAnd),
      1 -> Gen.lzy(genOr),
      1 -> Gen.lzy(genImplies),
      1 -> Gen.lzy(genNot)
    )
}
