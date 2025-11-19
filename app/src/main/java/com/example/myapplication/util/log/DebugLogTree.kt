package com.example.myapplication.util.log

import timber.log.Timber


class DebugLogTree : Timber.DebugTree() {

    private val name = "App"
    private val formatMsg = "%s: %s"

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        val logFormatMsg = StringBuilder()
            .append(message)
            .toString()

        val msg = String.format(formatMsg, tag, logFormatMsg)

        super.log(priority, name, msg, t)
    }

    override fun createStackElementTag(element: StackTraceElement): String? {
        return "(${element.fileName}:${element.lineNumber})"
    }
}