# Riassunto

In questo lavoro di tesi ho realizzato un applicazione, tramite il linguaggio di programmazione funzionale  orientato agli oggetti Scala, per l’implementazione dei Tableaux per la logica proposizionale classica con integrazione di property base testing. 
Nella prima parte del lavoro vengono fornite diverse definizioni al fine di fornire le basi per comprendere i Tableaux.                   
Vengono fornite le nozioni base della logica proposizionale (valutazione delle variabili, formule proposizionali e formule segnate) al fine di introdurre il concetto di Tableaux.
Successivamente viene presentata la definizione di Tableaux e descritta la sua costruzione formale illustrandone alcuni esempi del suo uso.		                                      Viene introdotto il framework di property based testing ScalaCheck e le nozioni di proprietà e di generatore, affiancate da degli esempi d’uso. 
Nella seconda parte, viene illustrata l’implementazione in Scala dell’applicazione e viene fornita una spiegazione per ogni sua componente. 				                           
Viene mostrata l’implementazione delle formule proposizionali, delle formule segnate e della struttura del Tableaux accompagnata dal suo algoritmo di costruzione.	                                                                  Viene illustrata l’implementazione delle proprietà di ScalaCheck definite sugli alberi binari.                                                                                                                                               
L’applicazione realizzata prende in input un file di testo al cui interno viene definita una formula proposizionale. 
Dal file viene estratta la formula per essere in seguito scansionata e rappresentata come albero binario di oggetti. 
L’albero binario viene scorso per costruire ricorsivamente il suo Tableaux, che a sua volta è un albero binario. 
Alla fine del processo vengono controllate le foglie per verificare se la formula proposizionale di partenza è verificata (tautologia).                                                                    
