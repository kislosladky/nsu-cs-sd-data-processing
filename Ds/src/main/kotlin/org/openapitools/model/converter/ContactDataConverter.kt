package org.openapitools.model.converter

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.openapitools.model.entity.components.ContactData
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter(autoApply = true)
class ContactDataConverter : AttributeConverter<ContactData, String> {
    private val objectMapper = jacksonObjectMapper()

    override fun convertToDatabaseColumn(attribute: ContactData?): String? {
        return attribute?.let { objectMapper.writeValueAsString(it) }
    }

    override fun convertToEntityAttribute(dbData: String?): ContactData? {
        return dbData?.let { objectMapper.readValue(it) }
    }
}
