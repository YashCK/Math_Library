# Math_Library
> A Java Library for Higher-Level Mathematics from Scratch - Includes Linear Algebra, Multivariable Calculus, etc.

This Library is designed to be a comprehenseive mathematical library for Java, designed for scientific computing and other mathematically-derived projects. Built with flexibility in mind, modules are easy to use and also have the flexibility to perform computations over abstract algebraic sturctues (Groups, Rings, Fields). 

This project combines advanced numerical algorithms with abstract algebra concepts to solve a wide range of mathematical problems. With a focus on readability and modularity, my code allows users to easily incorporate their own data and algorithms.

## Installation

OS X & Linux:

```sh
TODO
```

Windows:

```sh
TODO
```

## Usage example

An example of how the LinAlg class can be used to support different matrices and types.

<img width="766" alt="Screen Shot 2023-03-26 at 3 59 29 PM" src="https://user-images.githubusercontent.com/43621900/227810184-c7c851b9-289f-4632-b5e5-4e9ffa047aab.png">

## Current Scope

* **Linear Algebra**
    * Vectors
      * Generalizes notion of F^n to create Vectors of any type: (E.g. Vector\<Real>, Vector\<Complex>, Vector\<Duck> ...)
      * Allows creation of your own abstract vectors by extending Vec<T, S> that can form some vector space V
        * T is the type of the element in the Vector [Must be part of a field]
        * S is the type of the scalar associated with its Vector Space [Must be part of a field]
      * Operations (add, subtract, inner products, etc)
    * Matrices
      * Generalizes Matrices whose rows/columns are vectors of F^n: (E.g. Matrix\<Real>, Matrix\<Complex>, Matrix\<Duck> ...)
      * Allows creation of your own abstract matricies by extending Matx<T, S>
        * T is the type of the element in the Matrix [Must be part of a field]
        * S is the type of the scalar associated with its Space [Must be part of a field]
      * Operations (add, subtract, matrix multiplication, interchange cols/rols, etc)
      * Can be instantiated with either a row representation or column representation of vectors
    * Methods
      * Utility Methods (identity(n), zeros(n, m), diagonal(n, element), transpose(matrix)
      * General
        * Row reduction, Gaussian Elimination, Gauss Jordan, LU Solve
        * Determinant, Inverses (TBD)
        * Condition Numbers (TBD)
        * Space (TBD)
          * Column Space,  Null Space, Span, Linear Independence
          * Change of Basis
        * Eigenstuff (TBD)
          * Eigenvalues, Eigenvectors, Diagonalization
        * Orthonormality (TBD)
          * Projections, Gram-Schmidt, Least Squares
          * Norm  
      * Factorizations and Decompositions (TBD)
        * LU, RQ, QR, QZ factorizations 
        * SVD
        * Schur, Polar, Cholesky, Jordan decompositions
      * Algorithms
        * Cramer's Rule
        * PCA
        * Many more TBD 
* **Abstract Algebra**
    * Set
    * Additive and Multiplicative Group
    * Ring
    * Field
      * Pre-defined fields include Real, Complex, and Integers Modulo p
* **Multivar**
    * TBD
* **Differential Equations**
    * TBD
* **Linear Programming and Optimization**
    * TBD

## Release History

Your Name – [@YourTwitter](https://twitter.com/dbader_org) – YourEmail@example.com

Distributed under the XYZ license. See ``LICENSE`` for more information.

[https://github.com/yourname/github-link](https://github.com/dbader/)

## Liscence

TBD
