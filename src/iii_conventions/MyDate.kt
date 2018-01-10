package iii_conventions

import java.util.*

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {

    override fun compareTo(other: MyDate): Int {
        val c = Calendar.getInstance()
        c.set(year, month, dayOfMonth)
        val o = Calendar.getInstance()
        o.set(other.year, other.month, other.dayOfMonth)
        return c.compareTo(o)
    }

    operator fun plus(interval: TimeInterval) = this.addTimeIntervals(interval, 1)
    operator fun plus(intervals: TimeIntervals) = this.addTimeIntervals(intervals.unit, intervals.times)

}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR;
    operator fun times(times: Int) = TimeIntervals(this, times)
}

data class TimeIntervals(val unit: TimeInterval, val times: Int)

class DateRange(override val start: MyDate, override val endInclusive: MyDate) : ClosedRange<MyDate>, Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> = object : Iterator<MyDate> {
        var current: MyDate = start.addTimeIntervals(TimeInterval.DAY, -1)
        override fun hasNext(): Boolean {
            return current < endInclusive
        }

        override fun next(): MyDate {
            current = current.nextDay()
            return current
        }

    }
}
