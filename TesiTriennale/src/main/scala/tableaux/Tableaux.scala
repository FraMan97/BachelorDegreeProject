package tableaux

import formulaWff.EnumFormula
import signedFormula.SF

import scala.annotation.tailrec
import scala.collection.mutable



/**
  * Definisce il tableaux costruito da una formula con segno.
  */
sealed trait Tableaux {

  /**
    * Definisce un metodo che verifica se un tableaux è chiuso. Un tableaux
    * è chiuso quando in tutto le foglie è presente almeno una coppia complementare.
    * @return Ritorna true se il tableaux è chiuso, ovvero la formula su cui è costruito è una tautologia.
    *         Ritorna false se il tableaux non è chiuso, ovvero la formula su cui è contruito non è una tautologia.
    */
  def isClosed: Boolean = {

   def iter(t: Tableaux): Boolean = t match{
     case AlphaNode(_,s,_) => iter(s)
     case BetaNode(_,s1,s2,_) =>
       if (iter(s1) &&  iter(s2)) true else false
     case Leaf(els) => areComplementary(els)
   }

  @tailrec
   def areComplementary(els: List[SF]): Boolean = els match{
     case Nil => false
     case x :: xs =>
       if (xs.exists(p => (x.s != p.s) && (x.f == p.f)))
         true
       else
         areComplementary(xs)
   }

   iter(this)
  }

  /**
    * Rappresenta il tableaux in una stringa.
    * @return Ritorna la rappresentazione del tableaux in una stringa.
    */
  override def toString: String = this match {
    case AlphaNode (nte, s, els) =>
      "( Empty ).( Nte(" + nte + ")"  + (els(EnumFormula.Alpha)
        ++ els(EnumFormula.Beta) ++
        els(EnumFormula.Var)) + " ).( " + s + " )"
    case BetaNode (nte, l, r, els) =>
      "( " + l + " ).( Nte(" + nte + ")" + (els(EnumFormula.Alpha)
        ++ els(EnumFormula.Beta) ++
        els(EnumFormula.Var)) + " ).( " + r + " )"
    case Leaf (els) => els.toString()
  }
}

/**
* Definisce un nodo del tableaux che contiene una alpha - formula che sara espansa
* nel nodo figlio.
* @param nodeToExpand Definisce la formula segnata che, attraverso delle regole (alpha - formule), verrà
*                     espansa.
* @param son Definisce il figlio di questo nodo.
* @param els Definisce le altre formule segnate (da trattare in seguito o già trattate) presenti su questo nodo.
*/
case class AlphaNode(nodeToExpand: SF, son: Tableaux,
                     els: mutable.Map[EnumFormula.Value, List[SF]])
  extends Tableaux

/**
  * Definisce un nodo del tableaux che coniente una beta - formula che sarà espansa nei due nodi figli.
  * @param nodeToExpand Definisce la formula segnata che, attraverso delle regole (beta - formule), verrà
  *                     espansa.
  * @param left Definisce il figlio sinistro di questo nodo.
  * @param right Definisce il figlio destro di questo nodo.
  * @param els Definisce le altre formule segnate (da trattare in seguito o già trattate) presenti su questo nodo.
  */
case class BetaNode(nodeToExpand: SF, left: Tableaux, right: Tableaux,
                    els: mutable.Map[EnumFormula.Value, List[SF]])
  extends Tableaux

/**
  * Definisce una foglia del tableaux.
  * @param els Definisce le formule segnate (variabili proposizionali con segno)
  *            che non possono essere più trattate con le regole delle alpha - formule o beta - formule.
  */
case class Leaf(els: List[SF]) extends Tableaux