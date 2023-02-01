# Math_Library
A Java Library for Higher-Level Mathematics from Scratch - Includes Linear Algebra, Multivariable Calculus, etc

Current Scope
  * Linear Algebra
    * Vectors
      * Real and Complex Vectors
      * Operations (add, subtract, inner products, etc)
      * Allows creation of your own vectors by extending Vec<T, S> that can form some vector space V
          * NOTE: If this is done make sure that the vectors obey the axioms of vector spaces
            * T is the type of the element in the Vector
            * S is the type of the scalar associated with its Vector Space
    * Matrices
      * Real and Complex Matrices
      * Operations (add, subtract, matrix multiplication, transpose, etc)
      * Allows creation of matrices by extending Matx<T, S>
          * Same NOTE as creation of Vectors
    * Abstract Algebra
      * Semi-Groups, Monoids, Groups, Rings, Fields
    * Methods
      * Row Reduction
      * Gaussian Elimination
