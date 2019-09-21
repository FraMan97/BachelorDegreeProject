package signedFormula

import formulaWff._

/**
  * Definisce una formula segnata.
  */
sealed trait SignedFormula{

  /**
    * Definisce un metodo che restituisce il tipo di formula della formula segnata.
    * @return Ritorna Alpha se si tratta di un alpha - formula, Beta se si tratta di una beta - formula, altrimenti Var.
    */
  def getType: EnumFormula.Value = this match{
    case SF(T, And(_,_)) |
         SF(F, Or(_,_)) |
         SF(F, Implies(_,_)) |
         SF(F, Not(_)) |
         SF(T, Not(_)) => EnumFormula.Alpha
    case SF(T, Or(_,_)) |
         SF(F, And(_,_)) |
         SF(T, Implies(_,_)) => EnumFormula.Beta
    case SF(_,Var(_)) => EnumFormula.Var
  }

  /**
    * Rappresenta la formula segnata in una stringa.
    * @return Ritorna la rappresentazione di una formula segnata in stringa.
    */
  override def toString: String = this match {
    case SF(T, f) => T + "(" + f + ")"
    case SF(F, f) => F + "(" + f + ")"
  }

}

/**
  * Definisce una formula segnata composta da un segno e da una formula.
  * @param s Definisce il segno della formula segnata.
  * @param f Definisce la formula della formula segnata.
  */
case class SF(s: Sign, f: FormulaWff) extends SignedFormula

