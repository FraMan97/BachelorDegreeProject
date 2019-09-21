import builderFormulaWff.BuilderFormulaWff
import prover.Prover

val bf: BuilderFormulaWff = new BuilderFormulaWff("C:\\Users\\franc\\IdeaProjects\\TesiTriennale\\miofile.txt")
val p1 = new Prover(bf.build())
p1.prove
p1.prove.isClosed
val bf1: BuilderFormulaWff = new BuilderFormulaWff("C:\\Users\\franc\\IdeaProjects\\TesiTriennale\\miofile1.txt")
val p2 = new Prover(bf1.build())
p2.prove
p2.prove.isClosed
val bf2: BuilderFormulaWff = new BuilderFormulaWff("C:\\Users\\franc\\IdeaProjects\\TesiTriennale\\miofile2.txt")
val p3 = new Prover(bf2.build())
p3.prove
p3.prove.isClosed
val bf3: BuilderFormulaWff = new BuilderFormulaWff("C:\\Users\\franc\\IdeaProjects\\TesiTriennale\\miofile3.txt")
val p4 = new Prover(bf3.build())
p4.prove
p4.prove.isClosed



