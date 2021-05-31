package com.rsschool.android2021.utils

fun String.isItInt(): Boolean = this.length <= 10 && this.toLong() <= Int.MAX_VALUE
