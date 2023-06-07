package com.tulio.reader.service.implementation

import com.tulio.reader.mapper.IReaderMapper
import com.tulio.reader.model.dto.ExtendedDTO
import com.tulio.reader.model.dto.ReaderDTO
import com.tulio.reader.model.entity.Reader
import com.tulio.reader.model.form.ReaderForm
import com.tulio.reader.repository.IReaderRepository
import com.tulio.reader.service.IReaderService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDate.now
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.Date
import java.util.stream.Collectors

@Service
class ReaderService(
    private val readerRepository: IReaderRepository,
    private val readerMapper: IReaderMapper,
    private val notFoundReaderException: String = "Nada encontrado em nosso banco de dados.",

    ) : IReaderService {

    override fun findAllWithFilters(page: Int, size: Int): ExtendedDTO {
        try {
            val pageable: Pageable = PageRequest.of(page, size)
            val reader: Page<Reader> = readerRepository.findAll(pageable)
            val readerDTO = reader.stream().map { r -> readerMapper.toReaderDTO(r) }.collect(Collectors.toList())
            val totalElements = reader.totalElements
            val totalPages = reader.totalPages
            return ExtendedDTO(totalElements, totalPages, readerDTO)
        } catch (e: Exception) {
            throw Exception(notFoundReaderException)
        }
    }

    override fun createReader(readerForm: ReaderForm): ReaderDTO {
        println(readerForm.moisture)

        var moistureFormated = Reader.MoistureLevel.SECO
        when {
            readerForm.moisture!! >= 2000 -> moistureFormated = Reader.MoistureLevel.ENCHARCADO
            readerForm.moisture!! in 1000..1999 -> moistureFormated = Reader.MoistureLevel.UMIDO
        }

        val reader = readerMapper.toReader(readerForm)

        if (reader.date.equals(null)) {
            reader.date = LocalDate.now().toString()
        }

        if (reader.time.equals(null)) {
            reader.time = LocalTime.now().toString()
        }

        reader.moisture = moistureFormated
        val readerSaved = readerRepository.save(reader)

        return readerMapper.toReaderDTO(readerSaved)
    }
}