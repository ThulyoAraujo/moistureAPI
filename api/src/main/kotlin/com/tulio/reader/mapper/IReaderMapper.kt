package com.tulio.reader.mapper

import com.tulio.reader.model.dto.ReaderDTO
import com.tulio.reader.model.entity.Reader
import com.tulio.reader.model.form.ReaderForm
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper
interface IReaderMapper {
    @Mappings(
        Mapping(target = "moisture", ignore = true),
    )
    fun toReader(form: ReaderForm): Reader

    @Mappings()
    fun toReaderDTO(reader: Reader): ReaderDTO
}