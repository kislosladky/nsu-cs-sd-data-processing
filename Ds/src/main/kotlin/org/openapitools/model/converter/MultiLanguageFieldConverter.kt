package org.openapitools.model.converter

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.openapitools.model.entity.components.MultiLanguageField
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter(autoApply = false)
class MultiLanguageFieldConverter : AttributeConverter<MultiLanguageField, String> {

    private val mapper = jacksonObjectMapper()

    override fun convertToDatabaseColumn(attribute: MultiLanguageField?): String? {
        if (attribute == null) return null
        return mapper.writeValueAsString(mapOf("ru" to attribute.russian, "en" to attribute.english))
    }

    override fun convertToEntityAttribute(dbData: String?): MultiLanguageField? {
        if (dbData == null) return null
        val map: Map<String, String> = mapper.readValue(dbData)
        return MultiLanguageField(
            russian = map["ru"] ?: "",
            english = map["en"] ?: ""
        )
    }
}
