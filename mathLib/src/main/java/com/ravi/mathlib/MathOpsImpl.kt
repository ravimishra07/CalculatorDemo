package com.ravi.mathlib

class MathOpsImpl : MathOps {
    override fun addition(input1: Double, input2: Double): Double {
        return input1.plus(input2)
    }

    override fun subtraction(input1: Double, input2: Double): Double {
        return input1.minus(input2)
    }
}

