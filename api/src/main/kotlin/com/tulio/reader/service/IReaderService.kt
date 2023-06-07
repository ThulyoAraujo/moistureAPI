package com.tulio.reader.service

import com.tulio.reader.model.dto.ExtendedDTO
import com.tulio.reader.model.dto.ReaderDTO
import com.tulio.reader.model.form.ReaderForm

interface IReaderService {
    fun createReader(readerForm: ReaderForm): ReaderDTO

    fun findAllWithFilters(page: Int, size: Int): ExtendedDTO
}