# Math_Library
A Java Library for Higher-Level Mathematics from Scratch - Includes Linear Algebra, Multivariable Calculus, etc

Current Scope
  * Linear Algebra
    * Vectors
      * Real and Complex Vectors
      * Operations (add, subtract, inner products, etc)
      * Allows creation of your own vectors by implementing Vec<T, S, K> that can form some vector space V
          * NOTE: If this is done make sure that the vectors obey the axioms of vector spaces
            * The default scalar field are the Real Numbers (ℝ) but you can create a new Field and create new Vectors that utilize those Fields
    * Matrices
      * Real and Complex Matrices
      * Operations (add, subtract, matrix multiplication, transpose, etc)
      * Allows creation of matrices using your own Vectors<T> as either the columns or rows of the matrix
          * NOTE: If this is done make sure that the vectors obey the axioms of vector spaces
    * Abstract Algebra
      * Semi-Groups, Monoids, Groups, Rings, Fields
    * Methods
      * Row Reduction
      * Gaussian Elimination
