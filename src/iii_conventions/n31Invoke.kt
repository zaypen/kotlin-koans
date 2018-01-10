package iii_conventions

import util.TODO


class Invokable(private var count: Int = 0) {
    fun getNumberOfInvocations() = count
    operator fun invoke(): Invokable = apply { count +=1 }
}

fun todoTask31(): Nothing = TODO(
    """
        Task 31.
        Change the class 'Invokable' to count the number of invocations:
        for 'invokable()()()()' it should be 4.
    """,
    references = { invokable: Invokable -> })

fun task31(invokable: Invokable): Int {
//    todoTask31()
    return invokable()()()().getNumberOfInvocations()
}
