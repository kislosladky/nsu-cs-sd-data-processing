package org.openapitools.model.converter

import org.openapitools.model.entity.components.MultiLanguageField

fun MultiLanguageField.inLanguage(lang: String) : String {
    return when (lang) {
        "ru" -> this.russian
        "en" -> this.english
        else -> throw IllegalArgumentException("Language \"" + lang + "\" is not supported")
    }
}