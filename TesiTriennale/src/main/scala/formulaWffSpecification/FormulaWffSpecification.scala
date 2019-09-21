package formulaWffSpecification

import formulaWff._
import org.scalacheck.Prop.forAll
import org.scalacheck.{Properties, Test}

/**
  * Definisce i test sulle proprietà degli alberi binari che rappresentano le formule proposizionali.
  */
object FormulaWffSpecification extends Properties("FormulaWff") {

  property("size(t) <= 2^(depth(t) + 1) - 1") = forAll(FormulaWffGenerators.myGenFormula) {
    f: FormulaWff => f.size <= Math.pow(2, f.depth + 1) - 1
  }

  property("size(t) == size(t1) + size(t2) + 1") = forAll(FormulaWffGenerators.myGenFormula) {
    f: FormulaWff =>
    f match {
      case And(f1, f2) => f.size == f1.size + f2.size + 1
      case Or(f1, f2) => f.size == f1.size + f2.size + 1
      case Implies(f1, f2) => f.size == f1.size + f2.size + 1
      case _ => true
    }

  }

    property("leaves(t) <= 2^(depth(t))") = forAll(FormulaWffGenerators.myGenFormula) {
    f: FormulaWff => f.leaves <= Math.pow(2, f.depth)
  }

  property("leaves(t) == leaves(t1) + leaves(t2)") = forAll(FormulaWffGenerators.myGenFormula) {
    f: FormulaWff =>
    f match {
      case And(f1, f2) => f.leaves == f1.leaves + f2.leaves
      case Or(f1, f2) => f.leaves == f1.leaves + f2.leaves
      case Implies(f1, f2) => f.leaves == f1.leaves + f2.leaves
      case _ => true
    }
  }

  property("depth(t) <= size(t) - 1") = forAll(FormulaWffGenerators.myGenFormula) {
    f: FormulaWff => f.depth <= f.size - 1
  }

  property("depth(t) >= log(2 * size(t))") = forAll(FormulaWffGenerators.myGenFormula) {
    f: FormulaWff => f.depth >= Math.log10(2 * f.size).toInt
  }

  property("depth(t) = max(depth(t1), depth(t2)) + 1") = forAll(FormulaWffGenerators.myGenFormula) {
    f: FormulaWff => f match {
      case And(f1, f2) => f.depth == Math.max(f1.depth, f2.depth) + 1
      case Or(f1, f2) => f.depth == Math.max(f1.depth, f2.depth) + 1
      case Implies(f1, f2) => f.depth == Math.max(f1.depth, f2.depth) + 1
      case _ => true
    }
  }

  val checkProp: Seq[(String, Test.Result)] = Test.checkProperties(Test.Parameters.default, this)


}
