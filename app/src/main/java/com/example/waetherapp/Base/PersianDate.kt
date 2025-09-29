package com.example.waetherapp.Base

import java.util.Calendar
import java.util.GregorianCalendar
import javax.inject.Inject

class WeekDayList @Inject constructor(
    private val calendar: GregorianCalendar
) {

    var listWeekDays = emptyList<String>()
    var strMonth = ""
    var day = 0
    var month = 0
    var year = 0
    var hour = 0
    var min = 0
    var second = 0

    init {
        setDateCalendar()
    }

    private fun setDateCalendar() {

        val ld: Int

        hour = calendar.get(Calendar.HOUR_OF_DAY)
        min = calendar.get(Calendar.MINUTE)
        second = calendar.get(Calendar.SECOND)

        //regionConvertCalendar

        val persianYear = calendar.get(Calendar.YEAR)
        val persianMonth = calendar.get(Calendar.MONTH) + 1
        val persianDate = calendar.get(Calendar.DATE)
        val weekDay = calendar.get(Calendar.DAY_OF_WEEK)

        val buf1 = IntArray(12)
        val buf2 = IntArray(12)

        buf1[0] = 0
        buf1[1] = 31
        buf1[2] = 59
        buf1[3] = 90
        buf1[4] = 120
        buf1[5] = 151
        buf1[6] = 181
        buf1[7] = 212
        buf1[8] = 243
        buf1[9] = 273
        buf1[10] = 304
        buf1[11] = 334
        buf2[0] = 0
        buf2[1] = 31
        buf2[2] = 60
        buf2[3] = 91
        buf2[4] = 121
        buf2[5] = 152
        buf2[6] = 182
        buf2[7] = 213
        buf2[8] = 244
        buf2[9] = 274
        buf2[10] = 305
        buf2[11] = 335

        if (persianYear % 4 != 0) {

            this.day = buf1[persianMonth - 1] + persianDate

            if (this.day > 79) {
                this.day -= 79
                if (this.day <= 186) {
                    when (this.day % 31) {
                        0 -> {
                            month = this.day / 31
                            this.day = 31
                        }

                        else -> {
                            month = this.day / 31 + 1
                            this.day %= 31
                        }
                    }
                    year = persianYear - 621
                }
            } else {
                this.day -= 186
                when (this.day % 30) {
                    0 -> {
                        month = this.day / 30 + 6
                        this.day = 30
                    }

                    else -> {
                        month = this.day / 30 + 10
                        this.day %= 30
                    }
                }
                year = persianYear - 622
            }
        } else {
            this.day = buf2[persianMonth - 1] + persianDate
            ld = if (persianYear >= 1996) {
                79
            } else {
                80
            }
            if (this.day > ld) {
                this.day -= ld
                if (this.day <= 186) {
                    when (this.day % 31) {
                        0 -> {
                            month = this.day / 31
                            this.day = 31
                        }

                        else -> {
                            month = this.day / 31 + 1
                            this.day %= 31
                        }
                    }
                    year = persianYear - 621
                } else {
                    this.day -= 186
                    when (this.day % 30) {
                        0 -> {
                            month = this.day / 30 + 6
                            this.day = 30
                        }

                        else -> {
                            month = this.day / 30 + 7
                            this.day %= 30
                        }
                    }
                    year = persianYear - 621
                }
            } else {
                this.day += 10
                when (this.day % 30) {
                    0 -> {
                        month = this.day / 30 + 9
                        this.day = 30
                    }

                    else -> {
                        month = this.day / 30 + 10
                        this.day %= 30
                    }
                }
                year = persianYear - 622
            }
        }

        //endregion

        when (month) {
            1 -> strMonth = "فروردين"
            2 -> strMonth = "ارديبهشت"
            3 -> strMonth = "خرداد"
            4 -> strMonth = "تير"
            5 -> strMonth = "مرداد"
            6 -> strMonth = "شهريور"
            7 -> strMonth = "مهر"
            8 -> strMonth = "آبان"
            9 -> strMonth = "آذر"
            10 -> strMonth = "دي"
            11 -> strMonth = "بهمن"
            12 -> strMonth = "اسفند"
        }

        when (weekDay) {
            1 -> listWeekDays = listOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday")
            2 -> listWeekDays = listOf( "Monday", "Tuesday", "Wednesday", "Thursday", "Friday")
            3 -> listWeekDays = listOf( "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
            4 -> listWeekDays =  listOf( "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" )
            5 -> listWeekDays = listOf( "Thursday", "Friday", "Saturday", "Sunday", "Monday" )
            6 -> listWeekDays = listOf( "Friday", "Saturday", "Sunday", "Monday", "Tuesday" )
            7 -> listWeekDays = listOf( "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday")
        }

    }

}

