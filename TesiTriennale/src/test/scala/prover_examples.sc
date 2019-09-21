import prover.Prover
import formulaWff._

val p1 = new Prover(Implies(Implies(Not(Var("A")), Or(Var("B"), Var("C"))), Or(Implies(Not(Var("A")), Var("B")), Implies(Not(Var("A")), Var("C")))))
p1.prove
p1.prove.isClosed

val p2 = new Prover(Implies(Implies(Var("P"), Implies(Var("Q"),Var("R"))), Implies(Implies(Var("P"),Var("Q")), Implies(Var("P"),Var("R")))))
p2.prove
p2.prove.isClosed

val p3 = new Prover(Not(Not(Not(Not(Var("A"))))))
p3.prove
p3.prove.isClosed

val p4 = new Prover(And(Var("A"), Not(Var("A"))))
p4.prove
p4.prove.isClosed

val p5 = new Prover(Or(Var("A"), Not(Var("A"))))
p5.prove
p5.prove.isClosed

val p6 = new Prover(And(And(Var("A"), Var("B")),And(Var("A"), Var("B"))))
p6.prove
p6.prove.isClosed

val p7 = new Prover(Var("X"))
p7.prove
p7.prove.isClosed

val p8 = new Prover(And(Not(Var("A")), Not(Var("B"))))
p8.prove
p8.prove.isClosed

val p9 = new Prover(Implies(Var("P"), Implies(Not(Var("P")), Var("Q"))))
p9.prove
p9.prove.isClosed

val p10 = new Prover(Implies(Implies(Var("P"), Not(Var("P"))), Var("Q")))
p10.prove
p10.prove.isClosed

val p11 = new Prover(Implies(Var("P"), Var("P")))
p11.prove
p11.prove.isClosed

val p12 = new Prover(Not(And(Var("P"), Not(Var("P")))))
p12.prove
p12.prove.isClosed



