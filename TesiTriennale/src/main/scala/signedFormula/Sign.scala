package signedFormula

/**
  * Definisce il segno di una formula segnata.
  */
sealed trait Sign{

  /**
    * Rappresenta il segno in una stringa.
    * @return Ritorna la rappresentazione del segno in stringa.
    */
  override def toString: String = this match{
    case T => "T"
    case F => "F"
  }
}

/**
  * Definisce il segno T di una formula segnata.
  */
case object T extends Sign

/**
  * Definisce il segno F di una formula segnata.
  */
case object F extends Sign