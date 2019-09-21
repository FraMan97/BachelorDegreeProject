package formulaWff

import scala.annotation.tailrec


/**
  * Definisce una formula composta da connettivi logici e variabili proposizionali.
  */
sealed trait FormulaWff {

  /**
    * Rappresenta la formula in una stringa.
    *
    * @return Ritorna la rappresentazione della formula in stringa.
    */
  override def toString: String = this match {
    case Not(e1) => "¬(" + e1 + ")"
    case And(e1, e2) => "(" + e1 + " & " + e2 + ")"
    case Or(e1, e2) => "(" + e1 + " | " + e2 + ")"
    case Implies(e1, e2) => "(" + e1 + " -> " + e2 + ")"
    case Var(e1) => e1
  }

  /**
    * Fornisce il numero di nodi che appaiono nell'albero binario che rappresenta la formula proposizionale.
    * Conta tutte le variabili e connettivi.
    */

  def size: Int = {

    @tailrec
    def iter(l: List[FormulaWff], count: Int): Int =
      l match {
        case Nil => count
        case Var(_) :: ls => iter(ls, count + 1)
        case Not(e1) :: ls => iter(e1 :: ls, count + 1)
        case And(e1, e2) :: ls => iter(e1 :: e2 :: ls, count + 1)
        case Or(e1, e2) :: ls => iter(e1 :: e2 :: ls, count + 1)
        case Implies(e1, e2) :: ls => iter(e1 :: e2:: ls, count + 1)

      }
    iter(List(this), 0)
  }

  /**
    * Fornisce l'altezza dell'albero binario che rappresenta la formula proposizionle.
    * La radice ha altezza zero.
    */
  def depth: Int = {

    def iter(f: FormulaWff): Int = f match {
      case Var(_) => 0
      case Not(e1) => 1 + iter(e1)
      case And(e1, e2)  => 1 + Math.max(iter(e1), iter(e2))
      case Or(e1, e2) => 1 + Math.max(iter(e1), iter(e2))
      case Implies(e1, e2) => 1 + Math.max(iter(e1), iter(e2))
    }

    iter(this)
  }

  /**
    * Fornisce il numero di foglie dell'albero che rappresenta la formula proposizionale.
    * Ogni variabile proposizionale è una foglia.
    */
  def leaves: Int = {

    @tailrec
    def iter(l: List[FormulaWff], count: Int): Int =
      l match {
        case Nil => count
        case Var(_) :: ls => iter(ls, count + 1)
        case Not(e1) :: ls => iter(e1 :: ls, count)
        case And(e1, e2) :: ls => iter(e1 :: e2 :: ls, count)
        case Or(e1, e2) :: ls => iter(e1 :: e2 :: ls, count)
        case Implies(e1, e2) :: ls => iter(e1 :: e2 :: ls, count)

      }
    iter(List(this), 0)
  }

}

/**
  * Definisce il connettivo logico And (∧)
  *
  * @param f1 Definisce la prima sotto formula.
  * @param f2 Definisce la seconda sotto formula.
  */
case class And(f1: FormulaWff, f2: FormulaWff) extends FormulaWff

/**
  * Definisce il connettivo logico Or (∨).
  *
  * @param f1 Definisce la prima sotto formula.
  * @param f2 Definisce la seconda sotto formula.
  */
case class Or(f1: FormulaWff, f2: FormulaWff) extends FormulaWff

/**
  * Definisce il connettivo logico Implies (⇒).
  *
  * @param f1 Definisce la prima sotto formula.
  * @param f2 Definisce la prima sotto formula.
  */
case class Implies(f1: FormulaWff, f2: FormulaWff) extends FormulaWff

/**
  * Definisce il connettivo logico Not (¬).
  *
  * @param f1 Definisce la sotto formula.
  */
case class Not(f1: FormulaWff) extends FormulaWff

/**
  * Definisce la variabile proposizionale.
  *
  * @param s Definisce la stringa che rappresenta la variabile proposizionale.
  */
case class Var(s: String) extends FormulaWff
